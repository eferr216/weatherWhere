package edu.matc.persistence;

import edu.matc.entity.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     *
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

    public Item getItemsByCategory() {
        Item item = new Item();
        return item;
    }

}
