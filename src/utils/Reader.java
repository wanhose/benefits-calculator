package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Category;
import models.Product;

public class Reader {

    /**
     * Reader class properties.
     */
    private File csvFile;
    private File jsonFile;

    /**
     * Reader class constructor.
     * @param csvFile
     * @param jsonFile
     */
    public Reader(File csvFile, File jsonFile) {
        this.csvFile = csvFile;
        this.jsonFile = jsonFile;
    }

    /**
     * This method returns parsed products list from CSV file.
     * @return products
     */
    public List<Product> getProducts() {
        BufferedReader reader = null;
        List<Category> categories = this.getCategories();
        List<Product> products = new ArrayList<>();
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(this.csvFile));
            line = reader.readLine();

            List<Integer> positions = this.getPositions(line);

            while ((line = reader.readLine()) != null) {
                String[] product = line.split(";");
                String id = product[positions.get(0)];
                Category category = categories.stream().filter(categ -> categ.getName().equals(product[positions.get(1)])).findAny().orElse(categories.size() > 0 ? new Category(product[positions.get(1)], categories.get(categories.size() - 1).getBonuses()) : null);
                Double cost = ParseUtils.parseDouble(ParseUtils.parsePriceString(product[positions.get(2)]));
                int quantity = ParseUtils.parseInt(ParseUtils.parseQuantityString(product[positions.get(3)]));

                products.add(new Product(id, category, cost, quantity));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        } catch (IOException ioe) {
            System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
                }
            }
        }

        return products;
    }

    /**
     * This method decode JSON file and it returns parsed categories list.
     * @return categories
     */
    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        JsonElement element = null;

        try {
            element = JsonParser.parseReader(new FileReader(this.jsonFile));

            JsonObject root = element.getAsJsonObject();
            JsonObject nodes = root.getAsJsonObject("categories");

            for (Map.Entry<String, JsonElement> node : nodes.entrySet()) {
                categories.add(new Category(node.getKey(), this.getBonuses(node.getValue().getAsString())));
            }            
        } catch (FileNotFoundException fnfe) {
            System.out.println("\nERROR. A critical error has ocurred. Please contact administrator for more information.\n");
        }

        return categories;        
    }

    /**
     * This method decode bonuses string and it returns an HashMap with a structure like...
     * "%" => 12.0
     * "€" => 1.0
     * @param bonusesEncoded
     * @return bonuses
     */
    private HashMap<String, Double> getBonuses(String bonusesEncoded) {
        HashMap<String, Double> bonusesDecoded = new HashMap<String, Double>();

        String[] bonuses = bonusesEncoded.split("(?<=%)|(?<=\\€)");
        String key = "";
        Double value = 0.0;

        for (String bonus : bonuses) {
            key = bonus.substring(bonus.length() - 1);
            value = Double.parseDouble(bonus.substring(0, bonus.length() - 1));

            bonusesDecoded.put(key, value);
        }

        return bonusesDecoded;
    }

    /**
     * This method returns columns position list.
     * @param columnLine
     * @return positions
     */
    private List<Integer> getPositions(String columnLine) {
        List<Integer> columnOrderList = new ArrayList<>();
        String[] columns = columnLine.split(";");

        for (int i = 0; i < columns.length; i++) {
            columnOrderList.add(i, getPosition(columns[i]));
        }

        return columnOrderList;
    }

    /**
     * This method returns column position by column name.
     * @param column
     * @return position
     */
    private Integer getPosition(String column) {
        int position = 0;

        switch (column) {
            case "PRODUCT":
                position = 0;
                break;
            case "CATEGORY":
                position = 1;
                break;
            case "COST":
                position = 2;
                break;
            case "QUANTITY":
                position = 3;
                break;
        }

        return position;
    }
}