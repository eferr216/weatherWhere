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
    private int id;

    /**
     * Instantiates a new User.
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCategory() {
        return itemCategory;
    }

}
