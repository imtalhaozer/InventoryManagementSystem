package com.example.inventorymanagementsystem.models;

public class BillItem {
    private Long id;
    private Long billId;
    public Long productId;
    private int quantity;

    public BillItem(Long id, Long billId, Long productId, int quantity) {
        this.id = id;
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
