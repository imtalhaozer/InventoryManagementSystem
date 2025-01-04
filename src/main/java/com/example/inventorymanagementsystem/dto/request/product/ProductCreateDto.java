package com.example.inventorymanagementsystem.dto.request.product;

import java.util.UUID;

public class ProductCreateDto {
    private UUID supplierId;
    private String name;
    private int stockQuantity;
    private double price;
    private double discount;
    private String description;

    public ProductCreateDto() {
    }

    public ProductCreateDto(UUID supplierId, String name, int stockQuantity, double price, double discount, String description) {
        this.supplierId = supplierId;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.description = description;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
