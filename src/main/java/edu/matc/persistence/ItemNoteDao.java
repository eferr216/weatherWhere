package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * This class represents an item note.
 */
public class ItemNoteDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * This method gets all of the item notes.
     * @return all item notes
     */
    public List<ItemNote> getAllItemNotes() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ItemNote> query = builder.createQuery(ItemNote.class);
        Root<ItemNote> root = query.from(ItemNote.class);
        List<ItemNote> itemNotes = session.createQuery(query).getResultList();
        session.close();
        return itemNotes;
    }

    /**
     * Get an item note by id.
     *
     * @param id
     * @return an item note
     */
    public ItemNote getById(int id) {
        Session session = sessionFactory.openSession();
        ItemNote itemNote = session.get(ItemNote.class, id);
        session.close();
        return itemNote;
    }

    /**
     * Update an item note.
     * @param itemNote an item note
     */
    public void saveOrUpdate(ItemNote itemNote) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(itemNote);
        transaction.commit();
        session.close();
    }

    /**
     * This method inserts a new Item Note into the database.
     *
     * @param itemNote an item note
     * @return the id of the inserted item
     */
    public int insert(ItemNote itemNote) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(itemNote);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete an item note
     * @param itemNote Item Note to be deleted
     */
    public void delete(ItemNote itemNote) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(itemNote);
        transaction.commit();
        session.close();
    }

    /**
     * Get item note by property (exact match)
     * sample usage: getByPropertyEqual("itemNoteCategory", "Pants")
     */
    public List<ItemNote> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for item note with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ItemNote> query = builder.createQuery( ItemNote.class );
        Root<ItemNote> root = query.from( ItemNote.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<ItemNote> itemNotes = session.createQuery( query ).getResultList();

        session.close();
        return itemNotes;
    }

    /**
     * Get item note by property (like)
     * sample usage: getPropertyLike("itemNoteName", "Grey sweater")
     */
    public List<ItemNote> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for item note with {} = {}", propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ItemNote> query = builder.createQuery( ItemNote.class );
        Root<ItemNote> root = query.from( ItemNote.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<ItemNote> itemNotes = session.createQuery( query ).getResultList();
        session.close();
        return itemNotes;
    }
}
