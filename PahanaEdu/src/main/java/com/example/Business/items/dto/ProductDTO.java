package com.example.Business.items.dto;

public class ProductDTO {
    private String name;
    private String description;
    private String category;
    private int quantity;
    private double price;

    public ProductDTO(String name, String description, String category, int quantity, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
