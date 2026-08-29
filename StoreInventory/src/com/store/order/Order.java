package com.store.order;

public class Order {

    private String productName;
    private int orderedQuantity;
    private double unitPrice;
    private double discountRate;
    private double finalTotal;

    public Order(String productName, int orderedQuantity, double unitPrice,
                 double discountRate, double finalTotal) {
        this.productName = productName;
        this.orderedQuantity = orderedQuantity;
        this.unitPrice = unitPrice;
        this.discountRate = discountRate;
        this.finalTotal = finalTotal;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    @Override
    public String toString() {
        return "===== Order Summary ====="
                + "\nProduct Name: " + productName
                + "\nOrdered Quantity: " + orderedQuantity
                + "\nUnit Price: $" + String.format("%.2f", unitPrice)
                + "\nApplied Discount Rate: " + String.format("%.0f", discountRate * 100) + "%"
                + "\nFinal Total: $" + String.format("%.2f", finalTotal);
    }
}
