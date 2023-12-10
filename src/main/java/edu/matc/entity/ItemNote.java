package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "ItemNote{" +
                "id=" + id +
                ", noteText='" + noteText + '\'' +
                ", item=" + item +
                '}';
    }

}
