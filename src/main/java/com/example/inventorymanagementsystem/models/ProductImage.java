package com.example.inventorymanagementsystem.models;

public class ProductImage {
    private Long id;
    private Long productId;
    private String imageUrl;

    public ProductImage(Long id, Long productId, String imageUrl) {
        this.id = id;
        this.productId = productId;
        this.imageUrl = imageUrl;
    }
}
