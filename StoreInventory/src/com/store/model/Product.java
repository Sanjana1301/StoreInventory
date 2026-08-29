package com.store.model;

public class Product {
	
	 private String name;
	    private String category;
	    private double price;
	    private int stockQuantity;

	    public Product(String name, String category, double price, int stockQuantity) {
	        this.name = name;
	        this.category = category;
	        setPrice(price);
	        setStockQuantity(stockQuantity);
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        if (price <= 0) {
	            throw new IllegalArgumentException("Price must be greater than 0.");
	        }
	        this.price = price;
	    }

	    public int getStockQuantity() {
	        return stockQuantity;
	    }

	    public void setStockQuantity(int stockQuantity) {
	        if (stockQuantity < 0) {
	            throw new IllegalArgumentException("Stock quantity cannot be negative.");
	        }
	        this.stockQuantity = stockQuantity;
	    }

	    @Override
	    public String toString() {
	        return name + " (" + category + ") - $" + price + " | Stock: " + stockQuantity;
	    }

}
