package edu.matc.persistence;

import edu.matc.entity.ItemNote;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import java.util.List;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic DAO
     * @param type the entity type, for example, Item
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Get all entities
     * @return all entities
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Gets an entity by id
     * @param id entity id to search by
     * @return an entity
     * @param <T>
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Update an item note.
     * @param itemNote an item note
     */
    public void saveOrUpdate(ItemNote itemNote) {
        Session session = getSession();
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
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(itemNote);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Deletes an entity
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get item note by property (exact match)
     * sample usage: getByPropertyEqual("itemNoteCategory", "Pants")
     */
    public List<ItemNote> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

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
        Session session = getSession();

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

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}
