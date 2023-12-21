package edu.matc.persistence;

import edu.matc.entity.Item;
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
 * This is the ItemDao class.
 */
public class ItemDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * This method gets all of the items.
     * @return all items
     */
    public List<Item> getAllItems() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        List<Item> items = session.createQuery(query).getResultList();
        session.close();
        return items;
    }

    /**
     * This method gets an item by itemCategory.
     * @return an item
     */
    public List<Item> getItemsByCategory(String itemCategory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        Expression<String> propertyPath = root.get("itemCategory");
        query.where(builder.like(propertyPath, "%" + itemCategory + "%"));
        List<Item> items = session.createQuery(query).getResultList();
        session.close();
        return items;
    }

    /**
     * Get an item by id.
     *
     * @param id
     * @return an item
     */
    public Item getById(int id) {
        Session session = sessionFactory.openSession();
        Item item = session.get(Item.class, id);
        session.close();
        return item;
    }

    /**
     * Update an item.
     * @param item the item
     */
    public void saveOrUpdate(Item item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(item);
        transaction.commit();
        session.close();
    }

    /**
     * This method inserts a new Item into the database.
     *
     * @param item the item
     * @return the id of the inserted item
     */
    public int insert(Item item) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(item);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete an item
     * @param item Item to be deleted
     */
    public void delete(Item item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(item);
        transaction.commit();
        session.close();
    }

    /**
     * Get item by property (exact match)
     * sample usage: getByPropertyEqual("itemCategory", "Pants")
     */
    public List<Item> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for item with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery( Item.class );
        Root<Item> root = query.from( Item.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Item> items = session.createQuery( query ).getResultList();

        session.close();
        return items;
    }

    /**
     * Get item by property (like)
     * sample usage: getPropertyLike("itemName", "Grey sweater")
     */
    public List<Item> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for item with {} = {}", propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery( Item.class );
        Root<Item> root = query.from( Item.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Item> items = session.createQuery( query ).getResultList();
        session.close();
        return items;
    }

}
