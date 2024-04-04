package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemNoteDaoTest {
    GenericDao genericDao;
    GenericDao itemDao;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(ItemNote.class);
        itemDao = new GenericDao(Item.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets all item notes success.
     */
    @Test
    void getAllItemNotesSuccess() {
        List<ItemNote> itemNotes = genericDao.getAll();
        assertEquals(5, itemNotes.size());
    }



    /**
     * Verifies an item note is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        ItemNote retrievedItemNote = (ItemNote) genericDao.getById(4);
        assertNotNull(retrievedItemNote);
    }

    /**
     * Tests the update method.
     */
    @Test
    void saveOrUpdateSuccess() {
        String newItemNoteText = "Default Item Note Text";
        ItemNote itemNoteToUpdate = (ItemNote) genericDao.getById(3);
        itemNoteToUpdate.setNoteText(newItemNoteText);
        genericDao.saveOrUpdate(itemNoteToUpdate);
        ItemNote retrievedItemNote = (ItemNote) genericDao.getById(3);
        assertEquals(retrievedItemNote, itemNoteToUpdate);
    }

    /**
     * Tests the insert method.
     */
    @Test
    void insertSuccess() {

        Item selectedItem = (Item) itemDao.getById(3);

        String noteText = "Some text";

        ItemNote newItemNote = new ItemNote(noteText, selectedItem);
        selectedItem.addItemNote(newItemNote);

        int id = genericDao.insert(newItemNote);

        //ItemDao itemDao = new ItemDao();
        //Item item = itemDao.getById(1);
        //ItemNote newItemNote = new ItemNote("This is a new note for test purposes", item);
        //item.addItemNote(newItemNote);

        //int id = genericDao.insert(newItemNote);

        assertNotEquals(0, id);
        ItemNote insertedItemNote = (ItemNote) genericDao.getById(id);
        assertEquals(insertedItemNote, newItemNote);
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
     * Tests the getByPropertyEqual method.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<ItemNote> itemNotes = genericDao.getByPropertyEqual("noteText", "So comfy");
        assertEquals(1, itemNotes.size());
    }

    /**
     * Tests the getPropertyLike method.
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<ItemNote> itemNotes = genericDao.getByPropertyLike("noteText", "Kind of itchy.");
        assertEquals(1, itemNotes.size());
    }

}
