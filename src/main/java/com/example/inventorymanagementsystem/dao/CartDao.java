package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartDao {

    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public CartDao(Connection con) {
        this.con = con;
    }

    public void addCart(String userId) {
        try {
            query = "insert into Cart(id,retailerId) values(?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, userId);
            pst.setString(2, userId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getCartId(String retailerId) {
        Long cartId = null;
        try {
            query = "select id from Cart where retailerId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, retailerId);
            rs = pst.executeQuery();
            if (rs.next()) {
                cartId = rs.getLong("id");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartId;
    }
}
