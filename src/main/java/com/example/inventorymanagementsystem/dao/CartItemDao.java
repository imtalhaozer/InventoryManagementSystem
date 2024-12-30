package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.models.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartItemDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public CartItemDao(Connection con) {
        this.con = con;
    }

    public void saveCartItem(String cartId, int productId, int quantity) {
        try {
            query = "insert into CartItem(cartId, productId, quantity) values(?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, cartId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
