package com.example.inventorymanagementsystem.models;

public class BillItem {
    private Long id;
    private Long billId;
    public Long productId;
    private String productName;
    private double price;
    private int quantity;
    private BillItemStatusEnum confirm;

    public BillItem(Long id, Long billId, Long productId, String productName, double price, int quantity, BillItemStatusEnum confirm) {
        this.id = id;
        this.billId = billId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.confirm = confirm;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BillItemStatusEnum getConfirm() {
        return confirm;
    }

    public void setConfirm(BillItemStatusEnum confirm) {
        this.confirm = confirm;
    }
}
