package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Category;

public class Gift {
    private String productName;
    private double price;
    private Category category;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int quantity;
    /**
     * getter for productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * setter for prductName
     * @param productName
     * the argument used to set the productName with it
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }
    /**
     * getter for price
     */
    public double getPrice() {
        return price;
    }
    /**
     * setter for price
     * @param price
     * the argument used to set the price with it
     */
    public void setPrice(final double price) {
        this.price = price;
    }
    /**
     * getter for category
     */
    public Category getCategory() {
        return category;
    }
    /**
     * setter for category
     * @param category
     * the argument used to set the category with it
     */
    public void setCategory(final Category category) {
        this.category = category;
    }
    /**
     * getter for quantity
     */
    @JsonIgnore
    public int getQuantity() {
        return quantity;
    }
    /**
     * setter for quantity
     * @param quantity
     * the argument used to set the quantity with it
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
