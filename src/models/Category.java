package models;

import java.util.HashMap;

public class Category {

    /**
     * Category class properties.
     */
    private String name;
    private HashMap<String,Double> bonuses;

    /**
     * Category class constructor.
     * @param name
     * @param bonuses
     */
    public Category(String name, HashMap<String,Double> bonuses) {
        this.name = name;
        this.bonuses = bonuses;
    }

    /**
     * This methods returns name property.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method applies new value to name property.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This methods returns bonuses property.
     * @return bonuses
     */
    public HashMap<String,Double> getBonuses() {
        return this.bonuses;
    }

    /**
     * This method applies new value to bonuses property.
     * @param bonuses
     */
    public void setBonuses(HashMap<String,Double> bonuses) {
        this.bonuses = bonuses;
    }
}