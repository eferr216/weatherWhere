package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A class to represent a clothing item.
 */
@Entity(name="Item")
@Table(name="item")
public class Item {
    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_category")
    private String itemCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "item_id")
    private int id;

    //@OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //private Set<Item> items = new HashSet<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ItemNote> itemNotes = new HashSet<>();

    /**
     * Instantiates a new Item.
     */
    public Item() {
    }

    /**
     * Instantiates a new Item.
     *
     * @param id
     * @param itemName
     * @param itemDescription
     * @param itemCategory
     */
    public Item(int id, String itemName, String itemDescription, String itemCategory) {

        this.id = id;

        this.itemName = itemName;

        this.itemDescription = itemDescription;

        this.itemCategory = itemCategory;
    }

    /**
     * The setter method for the id instance variable.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * The getter method for the id instance variable.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * The setter method for the itemName instance variable.
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * The getter method for the itemName instance variable.
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * The setter method for the itemDescription instance variable.
     * @param itemDescription the item description
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * The getter method for the itemDescription instance variable.
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * The setter method for the itemCategory instance variable.
     * @param itemCategory the item category
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * The getter method for the itemCategory instance variable.
     * @return the itemCategory
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * This method gets the items
     * @return the items
     */
    /*public Set<Item> getItems() {
        return items;
    }*/

    /**
     * This method sets the items
     * @param items items Set
     */
    /*public void setItems(Set<Item> items) {
        this.items = items;
    }*/

    /**
     * Gets the item notes.
     * @return item notes
     */
    public Set<ItemNote> getItemNotes() {
        return itemNotes;
    }

    /**
     * Sets the item notes.
     * @param itemNotes item notes
     */
    public void setItemNotes(Set<ItemNote> itemNotes) {
        this.itemNotes = itemNotes;
    }

    /**
     * Adds an item note to the itemNotes Set
     * @param itemNote
     */
    public void addItemNote(ItemNote itemNote) {
        itemNotes.add(itemNote);
        itemNote.setItem(this);
    }

    /**
     * Remove item note
     * @param itemNote an item note
     */
    public void removeItemNote(ItemNote itemNote) {
        itemNotes.remove(itemNote);
        itemNote.setItem(null);
    }

    /**
     * The toString method.
     * @return a string representation of this class's object.
     */
    @Override
    public String toString() {
        return "Item{"+
                "itemName='" + itemName + '\'' +
                ", itemDescription'" + itemDescription + '\'' +
                ", itemCategory='" + itemCategory + '\'' + '}';
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
        Item item = (Item) o;
        return id == item.id && Objects.equals(itemName, item.itemName) && Objects.equals(itemDescription, item.itemDescription) && Objects.equals(itemCategory, item.itemCategory);
    }


    /**
     * The hashCode method.
     * @return a hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemDescription, itemCategory, id);
    }
}
