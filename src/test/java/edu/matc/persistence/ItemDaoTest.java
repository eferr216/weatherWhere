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

    ItemDao itemDao;
    GenericDao genericDao;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {

        itemDao = new ItemDao();
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
     * Gets items by category success.
     */
    @Test
    void getItemsByCategorySuccess() {
        List<Item> items = itemDao.getItemsByCategory("G");
        assertEquals(2, items.size());
    }

    /**
     * Verifies an item is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        Item retrievedItem = itemDao.getById(5);
        assertNotNull(retrievedItem);
        assertEquals("Snowpants", retrievedItem.getItemName());
    }

    /**
     * Tests the update method.
     */
    @Test
    void saveOrUpdateSuccess() {
       String newItemName = "Default Item Name";
       Item itemToUpdate = itemDao.getById(3);
       itemToUpdate.setItemName(newItemName);
       itemDao.saveOrUpdate(itemToUpdate);
       Item retrievedItem = itemDao.getById(3);
       assertEquals(newItemName, retrievedItem.getItemName());
    }

    /**
     * Tests the insert method.
     */
    @Test
    void insertSuccess() {

        Item newItem = new Item(45,"Yellow boots", "Yellow boots made for snowy climates", "Footwear");
        int id = genericDao.insert(newItem);
        assertEquals(45, id);
        Item insertedItem = (Item) genericDao.getById(id);
        assertEquals("Yellow boots", insertedItem.getItemName());
    }

    //@Test
    /*void insertWithItemNoteSuccess() {

        Item newItem = new Item(6, "Wool scarf", "A red wool scarf made in Alaska.", "Headwear");

        String noteText = "This scarf is a little itchy at times.";
        ItemNote itemNote = new ItemNote(noteText, newItem);

        newItem.addItemNote(itemNote);

        int id = itemDao.insert(newItem);
        assertNotEquals(0,id);
        Item insertedItem = itemDao.getById(id);
        assertEquals("Wool scarf", insertedItem.getItemName());

    }*/

    /**
     * Tests the delete method.
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(itemDao.getById(5));
        assertNull(genericDao.getById(5));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Item> items = itemDao.getByPropertyEqual("itemCategory", "Pants");
        assertEquals(1, items.size());
        assertEquals(5, items.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Item> items = itemDao.getByPropertyLike("itemCategory", "Pants");
        assertEquals(1, items.size());
    }
}