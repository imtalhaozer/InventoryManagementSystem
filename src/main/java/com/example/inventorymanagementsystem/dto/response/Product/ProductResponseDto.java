package com.example.inventorymanagementsystem.dto.response.Product;

import com.example.inventorymanagementsystem.models.IsDeleted;

import java.util.List;
import java.util.UUID;

public class ProductResponseDto {
    private Long id;
    private UUID supplierId;
    private String name;
    private int stockQuantity;
    private double price;
    private double discount;
    private String description;
    private List<String> imageUrlList;

    private IsDeleted isDeleted;




    public ProductResponseDto() {
    }

    public ProductResponseDto(Long id, UUID supplierId, String name, int stockQuantity, double price, double discount, String description, List<String> imageUrlList, IsDeleted isDeleted) {
        this.id = id;
        this.supplierId = supplierId;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.imageUrlList = imageUrlList;
        this.isDeleted = isDeleted;
    }

    public ProductResponseDto(Long id, UUID supplierId, String name, int stockQuantity, double price, double discount, String description, List<String> imageUrlList) {
        this.id = id;
        this.supplierId = supplierId;
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.imageUrlList = imageUrlList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public IsDeleted getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(IsDeleted isDeleted) {
        this.isDeleted = isDeleted;
    }
}
