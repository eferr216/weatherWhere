package edu.matc.entity;

/**
 * A class to represent a clothing item
 */
public class Item {
    private int id;
    private String itemName;
    private String itemDescription;
    private String itemCategory;

    /**
     * Instantiates a new User.
     */
    public Item() {
    }

    /**
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
