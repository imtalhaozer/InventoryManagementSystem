package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.dto.response.BillItemResponseDto;
import com.example.inventorymanagementsystem.models.BillItemStatusEnum;
import com.example.inventorymanagementsystem.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                query = "insert into BillItem (billId, productId, productName, price, quantity,confirm) values (?, ?, ?, ?,?,?)";
                pst = con.prepareStatement(query);
                pst.setString(1, billId);
                pst.setLong(2, cartItem.getProduct().getId());
                pst.setString(3, cartItem.getProduct().getName());
                pst.setDouble(4, cartItem.getProduct().getPrice()*cartItem.getQuantity()*(1-cartItem.getProduct().getDiscount()));
                pst.setInt(5, cartItem.getQuantity());
                pst.setString(6, BillItemStatusEnum.UNCERTAIN.toString());
                pst.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteBillItem(Long id){
        try {
            query = "delete from BillItem where id = ?";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean confirmBillItem(Long id, BillItemStatusEnum status){
        try {
            query = "update BillItem set confirm = ? where id = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, status.toString());
            pst.setLong(2, id);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BillItemResponseDto getBillItemById(Long id){
        try {
            query = "select * from BillItem where id = ?";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new BillItemResponseDto(
                        rs.getLong("id"),
                        rs.getString("billId"),
                        rs.getLong("productId"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        BillItemStatusEnum.valueOf(rs.getString("confirm"))
                );
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<BillItemResponseDto> getBillItemsByProductId(List<Long> productIds) {
        List<BillItemResponseDto> billItems = new ArrayList<>();
        try {
            for (Long productId : productIds) {
                query = "select * from BillItem where productId = ?";
                pst = this.con.prepareStatement(query);
                pst.setLong(1, productId);
                rs = pst.executeQuery();
                while (rs.next()) {
                    billItems.add(new BillItemResponseDto(
                            rs.getLong("id"),
                            rs.getString("billId"),
                            rs.getLong("productId"),
                            rs.getString("productName"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            BillItemStatusEnum.valueOf(rs.getString("confirm"))
                    ));
                }
            }
            return billItems;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<BillItemResponseDto> getBillItemsByBillId(String billId) {
        List<BillItemResponseDto> billItems = new ArrayList<>();
        try {
            query = "select * from BillItem where billId = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, billId);
            rs = pst.executeQuery();
            while (rs.next()) {
                billItems.add(new BillItemResponseDto(
                        rs.getLong("id"),
                        rs.getString("billId"),
                        rs.getLong("productId"),
                        rs.getString("productName"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        BillItemStatusEnum.valueOf(rs.getString("confirm"))
                ));
            }
            return billItems;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
