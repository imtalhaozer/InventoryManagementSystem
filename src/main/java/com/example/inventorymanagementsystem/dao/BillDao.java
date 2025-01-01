package com.example.inventorymanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public BillDao(Connection con) {
        this.con = con;
    }

    public void addBill(String billId, String userId) {
        try {
            query = "insert into Bill (id, retailerId) values (?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, billId);
            pst.setString(2, userId);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
