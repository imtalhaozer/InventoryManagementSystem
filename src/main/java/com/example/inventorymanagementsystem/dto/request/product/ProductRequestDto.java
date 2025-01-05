package com.example.inventorymanagementsystem.dto.request.product;

import javax.validation.constraints.*;
import java.util.UUID;

public class ProductRequestDto {

    @NotNull(message = "Supplier ID cannot be null")
    private UUID supplierId;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @PositiveOrZero(message = "Stock quantity must be zero or positive")
    private int stockQuantity;

    @Positive(message = "Price must be positive")
    private double price;

    @PositiveOrZero(message = "Discount must be zero or positive")
    @Max(value = 100, message = "Discount cannot exceed 100%")
    private double discount;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    public ProductRequestDto() {
    }

    public ProductRequestDto(UUID supplierId, String name, int stockQuantity, double price, double discount, String description) {
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
