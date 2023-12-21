package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class represents an item note.
 */
@Entity(name = "ItemNote")
@Table(name = "item_note")
public class ItemNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="note_id")
    private int id;

    @Column(name = "note_text")
    private String noteText;

    @ManyToOne
    @JoinColumn(name="item_id", foreignKey = @ForeignKey(name = "ITEM_ID_FK"))
    private Item item;

    /**
     * Instantiates a new ItemNote
     */
    public ItemNote() {
    }

    /**
     * Instantiates a new ItemNote
     * @param noteText the note text
     * @param item the item
     */
    public ItemNote(String noteText, Item item) {
        this.item = item;
        this.noteText = noteText;
    }

    /**
     * Gets id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the note text
     * @return the note text
     */
    public String getNoteText() {
        return noteText;
    }

    /**
     * Sets the note text
     * @param noteText the note text
     */
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    /**
     * Gets item.
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets item.
     * @param item the item
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * The toString method.
     * @return a string representation of this class's object.
     */
    @Override
    public String toString() {
        return "ItemNote{" +
                "id=" + id +
                ", noteText='" + noteText + '\'' +
                ", item=" + item +
                '}';
    }

    /**
     * This method compares two objects for equality.
     * @param o an Object
     * @return a boolean indicating whether both objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemNote itemNote = (ItemNote) o;
        return id == itemNote.id && Objects.equals(noteText, itemNote.noteText);
    }

    /**
     * The hashCode method.
     * @return a hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, noteText);
    }
}
