package com.example.inventorymanagementsystem.models;

import java.util.UUID;

public class Product {
    private Long id;
    private UUID supplierId;
    private String name;
    private int stockQuantity;
    private double price;
    private double discount;
}
