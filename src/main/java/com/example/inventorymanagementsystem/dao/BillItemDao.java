package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BillItemDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public BillItemDao(Connection con) {
        this.con = con;
    }


    public void addBillItem(String billId, List<CartItemResponseDto> cartItems) {
        try {
            for (CartItemResponseDto cartItem : cartItems) {
                query = "insert into BillItem (billId, productId, quantity) values (?, ?, ?)";
                pst = con.prepareStatement(query);
                pst.setString(1, billId);
                pst.setLong(2, cartItem.getProduct().getId());
                pst.setInt(3, cartItem.getQuantity());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
