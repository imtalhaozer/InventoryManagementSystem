package com.example.inventorymanagementsystem.models;

import java.util.List;
import java.util.UUID;

public class Bill {
    private Long id;
    private UUID retailerId;
    private List<BillItem> billItems;
    private double totalAmount;
}
