# Store Inventory & Order Management System

## Overview

This project is a modular Java application for managing store inventory and processing customer orders.

The application demonstrates object-oriented programming, encapsulation, collection handling, validation, business rules, and file persistence.

## Features

- Product management
- Product price and stock validation
- Add and remove products
- Case-insensitive product search
- Category-based product search
- Inventory listing
- Order processing
- Stock deduction after successful orders
- Tiered volume discounts
- Order summary generation
- Inventory save and restore using file persistence
- Exception handling for invalid operations

## Discount Rules

| Subtotal | Discount |
|----------|----------|
| $500 or more | 15% |
| $200 - $499.99 | 10% |
| $100 - $199.99 | 5% |
| Below $100 | 0% |

## Project Structure

```text
src
├── com.store.app
│   └── Main.java
├── com.store.inventory
│   └── StoreInventory.java
├── com.store.model
│   └── Product.java
├── com.store.order
│   └── Order.java
└── com.store.persistence
    └── InventoryFileManager.java