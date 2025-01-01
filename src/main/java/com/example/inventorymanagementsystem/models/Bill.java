package com.example.inventorymanagementsystem.models;

import java.util.List;
import java.util.UUID;

public class Bill {
    private String id;
    private UUID retailerId;
    private List<BillItem> billItems;

    public Bill(String id, UUID retailerId, List<BillItem> billItems) {
        this.id = id;
        this.retailerId = retailerId;
        this.billItems = billItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(UUID retailerId) {
        this.retailerId = retailerId;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
}
