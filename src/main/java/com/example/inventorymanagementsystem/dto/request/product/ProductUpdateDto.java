package com.example.inventorymanagementsystem.dto.request.product;

import javax.validation.constraints.*;
import java.util.UUID;

public class ProductUpdateDto {
    @NotNull(message = "Supplier ID cannot be null")
    Long id;

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

    public ProductUpdateDto() {
    }

    public ProductUpdateDto(Long id, String name, int stockQuantity, double price, double discount) {
        this.id = id;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
