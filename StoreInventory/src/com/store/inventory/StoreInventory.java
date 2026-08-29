package com.store.inventory;

import java.util.ArrayList;
import java.util.List;

import com.store.model.Product;
import com.store.order.Order;

public class StoreInventory {

    private List<Product> products;

    public StoreInventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(String productName) {
        return products.removeIf(
            product -> product.getName().equalsIgnoreCase(productName)
        );
    }

    public List<Product> searchByCategory(String category) {
        List<Product> matchingProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public void listInventory() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("===== Store Inventory =====");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Order createOrder(String productName, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Order quantity must be greater than 0."
            );
        }

        Product selectedProduct = null;

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct == null) {
            throw new IllegalArgumentException(
                "Product not found: " + productName
            );
        }

        if (selectedProduct.getStockQuantity() < quantity) {
            throw new IllegalArgumentException(
                "Insufficient stock for product: " + productName
            );
        }

        double unitPrice = selectedProduct.getPrice();

        double subtotal = unitPrice * quantity;

        double discountRate;

        if (subtotal >= 500) {
            discountRate = 0.15;
        } else if (subtotal >= 200) {
            discountRate = 0.10;
        } else if (subtotal >= 100) {
            discountRate = 0.05;
        } else {
            discountRate = 0.0;
        }

        double discountAmount = subtotal * discountRate;

        double finalTotal = subtotal - discountAmount;

        int remainingStock =
            selectedProduct.getStockQuantity() - quantity;

        selectedProduct.setStockQuantity(remainingStock);

        return new Order(
            selectedProduct.getName(),
            quantity,
            unitPrice,
            discountRate,
            finalTotal
        );
    }
}
