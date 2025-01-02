package com.example.inventorymanagementsystem.dto.response;

import com.example.inventorymanagementsystem.models.Product;

public class BillItemResponseDto {

    private Long id;
    private String billId;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private boolean confirm;

    public BillItemResponseDto(Long id, String billId, Long productId, String productName, double price, int quantity, boolean confirm) {
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

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
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

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
