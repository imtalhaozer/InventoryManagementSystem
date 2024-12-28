package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto;
import com.example.inventorymanagementsystem.util.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReatilerDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public ReatilerDao(Connection con) {
        this.con = con;
    }

    public void addRetailer(String name, String email, String password, String photo) {
        String hashedPassword = PasswordUtils.hashPassword(password);
        try {

            query = "insert into Retailer(id, name, email, password, photo) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, java.util.UUID.randomUUID().toString());
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, hashedPassword);
            pst.setString(5, photo);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isRetailerMailExist(String email) {
        boolean isExist = false;
        try {
            query = "select * from Retailer where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return isExist;
    }

    public RetailerResponseDto getRetailerByMail(String email) {
        RetailerResponseDto retailer = new RetailerResponseDto();
        try {
            query = "select * from Retailer where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                retailer.setName(rs.getString("name"));
                retailer.setEmail(rs.getString("email"));
                retailer.setPhoto(rs.getString("photo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailer;
    }

    public RetailerResponseDto LoginRetailer(String email, String password) {
        RetailerResponseDto retailer = new RetailerResponseDto();
        try {
            query = "select * from Retailer where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (PasswordUtils.checkPassword(password, hashedPassword)) {
                    retailer.setName(rs.getString("name"));
                    retailer.setEmail(rs.getString("email"));
                    retailer.setPhoto(rs.getString("photo"));
                }
                else {
                    retailer = null;  //burada hata fırlatılacak sonra ekle
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailer;
    }
}
