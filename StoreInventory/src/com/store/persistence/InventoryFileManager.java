package com.store.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.store.inventory.StoreInventory;
import com.store.model.Product;

public class InventoryFileManager {

    public static void saveInventoryToFile(
            StoreInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath))) {

            for (Product product : inventory.getProducts()) {

                writer.write("name=" + product.getName());
                writer.newLine();

                writer.write("category=" + product.getCategory());
                writer.newLine();

                writer.write("price=" + product.getPrice());
                writer.newLine();

                writer.write("stockQuantity=" + product.getStockQuantity());
                writer.newLine();

                writer.write("---");
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(
                "Unable to save inventory to file.", e
            );
        }
    }

    public static StoreInventory loadInventoryFromFile(
            String filePath) {

        StoreInventory inventory = new StoreInventory();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(filePath))) {

            String line;

            String name = null;
            String category = null;
            double price = 0;
            int stockQuantity = 0;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("name=")) {
                    name = line.substring(5);

                } else if (line.startsWith("category=")) {
                    category = line.substring(9);

                } else if (line.startsWith("price=")) {
                    price = Double.parseDouble(line.substring(6));

                } else if (line.startsWith("stockQuantity=")) {
                    stockQuantity =
                        Integer.parseInt(line.substring(14));

                } else if (line.equals("---")) {

                    Product product = new Product(
                        name,
                        category,
                        price,
                        stockQuantity
                    );

                    inventory.addProduct(product);

                    name = null;
                    category = null;
                    price = 0;
                    stockQuantity = 0;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(
                "Unable to load inventory from file.", e
            );
        }

        return inventory;
    }
}
