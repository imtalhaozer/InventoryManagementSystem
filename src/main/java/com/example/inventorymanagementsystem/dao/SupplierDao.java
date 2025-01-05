package com.example.inventorymanagementsystem.dao;


import com.example.inventorymanagementsystem.dto.request.supplier.SupplierCreateDto;
import com.example.inventorymanagementsystem.dto.response.supplier.SupplierResponseDto;
import com.example.inventorymanagementsystem.util.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public SupplierDao(Connection con) {
        this.con = con;
    }

    public void addSupplier(SupplierCreateDto supplierCreateDto) {
        String hashedPassword = PasswordUtils.hashPassword(supplierCreateDto.getPassword());
        try {
            query = "insert into Supplier(id, name, email, password, photo) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, java.util.UUID.randomUUID().toString());
            pst.setString(2, supplierCreateDto.getName());
            pst.setString(3, supplierCreateDto.getEmail());
            pst.setString(4, hashedPassword);
            pst.setString(5, supplierCreateDto.getPhoto());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isSupplierExist(String email) {
        boolean isExist = false;
        try {
            query = "select * from Supplier where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;}

    public SupplierResponseDto LoginSupplier(String email, String password) {
        SupplierResponseDto supplier = new SupplierResponseDto();
        try {
            query = "select * from Supplier where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (PasswordUtils.checkPassword(password, hashedPassword)) {
                    supplier.setName(rs.getString("name"));
                    supplier.setEmail(rs.getString("email"));
                    supplier.setPhoto(rs.getString("photo"));
                }
                else {
                    supplier = null;  //burada hata fırlatılacak sonra ekle
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }
}
