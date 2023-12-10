package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.test.util.Database;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemNoteDaoTest {
    ItemNoteDao itemNoteDao;

    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {

        itemNoteDao = new ItemNoteDao();

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Gets all item notes success.
     */
    @Test
    void getAllItemNotesSuccess() {
        List<ItemNote> itemNotes = itemNoteDao.getAllItemNotes();
        assertEquals(5, itemNotes.size());
    }



    /**
     * Verifies an item note is returned correctly based on id search.
     */
    @Test
    void getByIdSuccess() {
        ItemNote retrievedItemNote = itemNoteDao.getById(5);
        assertNotNull(retrievedItemNote);
        //assertEquals("Snowpants", retrievedItemNote.getItemNoteName());
    }

    /**
     * Tests the update method.
     */
    @Test
    void saveOrUpdateSuccess() {
        String newItemNoteText = "Default Item Note Text";
        ItemNote itemNoteToUpdate = itemNoteDao.getById(3);
        itemNoteToUpdate.setNoteText(newItemNoteText);
        itemNoteDao.saveOrUpdate(itemNoteToUpdate);
        ItemNote retrievedItemNote = itemNoteDao.getById(3);
        assertEquals(newItemNoteText, retrievedItemNote.getNoteText());
    }

    /**
     * Tests the insert method.
     */
    @Test
    void insertSuccess() {

        ItemDao itemDao = new ItemDao();
        Item item = itemDao.getById(1);
        ItemNote newItemNote = new ItemNote("This is a new note for test purposes", item);
        item.addItemNote(newItemNote);

        int id = itemNoteDao.insert(newItemNote);

        assertNotEquals(0, id);
        ItemNote insertedItemNote = itemNoteDao.getById(id);
        assertEquals("This is a new note for test purposes", insertedItemNote.getNoteText());
        assertNotNull(insertedItemNote.getItem());
        //assertEquals("", insertedItemNote.getItem().getItemName());
    }

    /**
     * Tests the delete method.
     */
    @Test
    void deleteSuccess() {
        itemNoteDao.delete(itemNoteDao.getById(6));
        assertNull(itemNoteDao.getById(6));
    }

    /**
     * Tests the getByPropertyEqual method.
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<ItemNote> itemNotes = itemNoteDao.getByPropertyEqual("noteText", "Wear these a lot");
        assertEquals(1, itemNotes.size());
    }

    /**
     * Tests the getPropertyLike method.
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<ItemNote> itemNotes = itemNoteDao.getByPropertyLike("noteText", "w");
        assertEquals(2, itemNotes.size());
    }

}
