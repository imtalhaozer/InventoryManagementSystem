package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.models.CartItem;
import com.example.inventorymanagementsystem.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public int getCartItemQuantity(String cartId, int productId) {
        try {
            query = "select quantity from CartItem where cartId=? and productId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, cartId);
            pst.setInt(2, productId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean checkItemForQuantity(String cartId, int productId, int quantity) {
        try {
            query = "select * from CartItem where cartId=? and productId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, cartId);
            pst.setInt(2, productId);
            rs = pst.executeQuery();
            if (rs.next()) {
                int qt = rs.getInt("quantity");
                query = "update CartItem set quantity=? where cartId=? and productId=?";
                pst = this.con.prepareStatement(query);
                pst.setInt(1, quantity+qt);
                pst.setString(2, cartId);
                pst.setInt(3, productId);
                pst.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CartItemResponseDto> getCartItemsByCartId(String cartId) {
        List<CartItemResponseDto> cartItems = new ArrayList<>();
        String query =
                "SELECT ci.id AS cartItemId, ci.cartId, ci.productId, ci.quantity, " +
                        "p.id AS productId, p.supplierId, p.name, p.stockQuantity, p.price, p.discount " +
                        "FROM CartItem ci " +
                        "JOIN Product p ON ci.productId = p.id " +
                        "WHERE ci.cartId = ?";

        try {
            pst = this.con.prepareStatement(query);
            pst.setString(1, cartId);
            rs = pst.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getLong("productId"),
                        UUID.fromString(rs.getString("supplierId")),
                        rs.getString("name"),
                        rs.getInt("stockQuantity"),
                        rs.getDouble("price"),
                        rs.getDouble("discount")
                );
                CartItemResponseDto cartItem = new CartItemResponseDto(
                        rs.getLong("cartItemId"),
                        rs.getString("cartId"),
                        product,
                        rs.getInt("quantity")
                );

                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartItems;
    }

    public void deleteCartItem(String cartId, Long productId) {
        try {
            query = "delete from CartItem where cartId=? and productId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, cartId);
            pst.setLong(2, productId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
