package models;

public class Product {
    
    /**
     * Product class properties.
     */
    private String id;
    private Category category;
    private Double cost;
    private int quantity;
    
    /**
     * Product class constructor.
     * @param id
     * @param category
     * @param cost
     * @param quantity
     */
    public Product(String id, Category category, Double cost, int quantity) {
        this.id = id;
        this.category = category;
        this.cost = cost;
        this.quantity = quantity;
    }

    /**
     * This methods returns id property.
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * This method applies new value to id property.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This methods returns category property.
     * @return category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * This method applies new value to category property.
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * This methods returns cost property.
     * @return cost
     */
    public Double getCost() {
        return this.cost;
    }

    /**
     * This method applies new value to cost property.
     * @param cost
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * This methods returns quantity property.
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * This method applies new value to quantity property.
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}