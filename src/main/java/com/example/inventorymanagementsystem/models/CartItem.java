package com.example.inventorymanagementsystem.models;

public class CartItem {
    private Long id;
    private String cartId;
    private Product product;
    private int quantity;

    public CartItem(Long id, String cartId, Product product, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
