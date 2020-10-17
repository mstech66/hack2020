package com.epam.model;

public class Product {
    private final String category;
    private final String subcategory;
    private final String product;
    private final int quantity;


    public Product(String category, String subcategory, String product, int quantity) {
        this.category = category;
        this.subcategory = subcategory;
        this.product = product;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
