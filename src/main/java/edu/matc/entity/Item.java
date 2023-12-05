package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a clothing item
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
     * @param itemDescription
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
     * @param itemCategory
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

}
