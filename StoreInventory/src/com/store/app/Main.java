package com.store.app;

import java.util.List;

import com.store.inventory.StoreInventory;
import com.store.model.Product;
import com.store.order.Order;
import com.store.persistence.InventoryFileManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== STORE INVENTORY SYSTEM =====");

        StoreInventory inventory = new StoreInventory();

        // ------------------------------------------------
        // 1. Add products
        // ------------------------------------------------

        Product laptop = new Product(
            "Laptop",
            "Electronics",
            999.99,
            5
        );

        Product mouse = new Product(
            "Mouse",
            "Electronics",
            25.99,
            10
        );

        Product chair = new Product(
            "Office Chair",
            "Furniture",
            150.00,
            8
        );

        inventory.addProduct(laptop);
        inventory.addProduct(mouse);
        inventory.addProduct(chair);

        System.out.println("\nProducts added successfully.");

        // ------------------------------------------------
        // 2. Display inventory
        // ------------------------------------------------

        System.out.println();
        inventory.listInventory();

        // ------------------------------------------------
        // 3. Search by category
        // ------------------------------------------------

        System.out.println("\n===== SEARCH BY CATEGORY =====");

        List<Product> electronics =
            inventory.searchByCategory("electronics");

        for (Product product : electronics) {
            System.out.println(product);
        }

        // ------------------------------------------------
        // 4. Validation - Invalid price
        // ------------------------------------------------

        System.out.println("\n===== VALIDATION TESTS =====");

        try {

            Product invalidPriceProduct = new Product(
                "Invalid Product",
                "Test",
                -50,
                5
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Invalid price handled: " + e.getMessage()
            );
        }

        // ------------------------------------------------
        // 5. Validation - Invalid stock
        // ------------------------------------------------

        try {

            Product invalidStockProduct = new Product(
                "Invalid Stock Product",
                "Test",
                50,
                -5
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Invalid stock handled: " + e.getMessage()
            );
        }

        // ------------------------------------------------
        // 6. Create discounted order
        // ------------------------------------------------

        System.out.println("\n===== CREATE ORDER =====");

        try {

            Order order = inventory.createOrder(
                "Laptop",
                1
            );

            System.out.println(order);

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Order failed: " + e.getMessage()
            );
        }

        // ------------------------------------------------
        // 7. Test insufficient stock
        // ------------------------------------------------

        System.out.println("\n===== STOCK VALIDATION =====");

        try {

            inventory.createOrder(
                "Laptop",
                10
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                "Stock validation handled: " + e.getMessage()
            );
        }

        // ------------------------------------------------
        // 8. Display inventory after order
        // ------------------------------------------------

        System.out.println("\n===== INVENTORY AFTER ORDER =====");

        inventory.listInventory();

        // ------------------------------------------------
        // 9. Save inventory to file
        // ------------------------------------------------

        String filePath = "inventory.txt";

        InventoryFileManager.saveInventoryToFile(
            inventory,
            filePath
        );

        System.out.println(
            "\nInventory saved to: " + filePath
        );

        // ------------------------------------------------
        // 10. Load inventory from file
        // ------------------------------------------------

        StoreInventory restoredInventory =
            InventoryFileManager.loadInventoryFromFile(
                filePath
            );

        System.out.println(
            "\n===== RESTORED INVENTORY ====="
        );

        restoredInventory.listInventory();

        // ------------------------------------------------
        // 11. Remove product
        // ------------------------------------------------

        System.out.println("\n===== REMOVE PRODUCT =====");

        boolean removed =
            restoredInventory.removeProduct("Mouse");

        if (removed) {
            System.out.println("Mouse removed successfully.");
        } else {
            System.out.println("Mouse was not found.");
        }

        System.out.println("\n===== FINAL INVENTORY =====");

        restoredInventory.listInventory();

        System.out.println(
            "\n===== PROGRAM COMPLETED ====="
        );
    }
}
