package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Item dao test.
 */
class ItemDaoTest {
    GenericDao genericDao;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Item.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets all items success.
     */
    @Test
    void getAllItemsSuccess() {
        List<Item> items = genericDao.getAll();
        assertEquals(5, items.size());
    }

    /**
     * Verifies an item is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Item retrievedItem = (Item) genericDao.getById(1);
        assertNotNull(retrievedItem);
        assertEquals("Red gloves", retrievedItem.getItemName());
    }

    /**
     * Tests the update method.
     */
    @Test
    void saveOrUpdateSuccess() {
       String newItemName = "Default Item Name";
       Item itemToUpdate = (Item) genericDao.getById(3);
       itemToUpdate.setItemName(newItemName);
       genericDao.saveOrUpdate(itemToUpdate);
       Item retrievedItem = (Item) genericDao.getById(3);
       assertEquals(retrievedItem, itemToUpdate);
    }

    /**
     * Tests the insert method.
     */
    @Test
    void insertSuccess() {

        Item newItem = new Item(6,"Yellow boots", "Yellow boots made for snowy climates", "Footwear");
        int id = genericDao.insert(newItem);
        Item insertedItem = (Item) genericDao.getById(id);
        assertEquals(insertedItem, newItem);
    }

    @Test
    void insertWithItemNoteSuccess() {

        Item newItem = new Item(7, "Wool scarf", "A red wool scarf made in Alaska.", "Headwear");

        String noteText = "This scarf is a little itchy at times.";
        ItemNote itemNote = new ItemNote(noteText, newItem);

        newItem.addItemNote(itemNote);

        int id = genericDao.insert(newItem);
        assertNotEquals(0,id);
        Item insertedItem = (Item) genericDao.getById(id);
        assertEquals(insertedItem, newItem);

    }

    /**
     * Tests the delete method.
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(5));
        assertNull(genericDao.getById(5));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Item> items = genericDao.getByPropertyEqual("itemCategory", "Pants");
        assertEquals(1, items.size());
        assertEquals(5, items.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Item> items = genericDao.getByPropertyLike("itemCategory", "Sweaters");
        assertEquals(1, items.size());
    }
}