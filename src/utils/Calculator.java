package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Product;

public class Calculator {

    /**
     * Calculator class properties.
     */
    private HashMap<String, Double> results;
    private List<Product> products;

    /**
     * Calculator class constructor.
     * @param products
     */
    public Calculator(List<Product> products) {
        this.results = new HashMap<String, Double>();
        this.products = products;
    }

    /**
     * This method creates result list and show it .
     */
    public void calculate() {
        String category = "";
        Double benefit = 0.0;

        for (Product product : this.products) {
            category = product.getCategory().getName();
            benefit = this.getBenefit(product);

            if (this.results.containsKey(category)) {
                this.results.put(category, this.results.get(category) + benefit);
            } else {
                this.results.put(category, benefit);
            }
        }

        System.out.println("\nRESULTS:\n");

        for (Map.Entry<String, Double> entry : this.results.entrySet()) {
            System.out.println("* " + entry.getKey().toUpperCase() + ": " + entry.getValue() + "€");
        }
    }

    /**
     * This method calculate specified product benefit.
     * @param product
     * @return benefit
     */
    private Double getBenefit(Product product) {
        HashMap<String, Double> benefits = product.getCategory().getBonuses();
        Double benefit = 0.0;
        Double cost = product.getCost();
        int quantity = product.getQuantity();

        for (Map.Entry<String, Double> entry : benefits.entrySet()) {
            if (entry.getKey().equals("€")) {
                benefit = benefit + (entry.getValue() * quantity);
            } else if (entry.getKey().equals("%")) {
                benefit = benefit + ((cost * entry.getValue() / 100) * quantity);
            }
        }

        return benefit;
    }
}