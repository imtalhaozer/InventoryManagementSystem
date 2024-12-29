package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.util.PasswordUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public UserDao(Connection con) {
        this.con = con;
    }

    public UserResponseDto loginUser(String email, String password) {
        UserResponseDto user = null;
        try {
            query = "SELECT * FROM Retailer WHERE email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (PasswordUtils.checkPassword(password, hashedPassword)) {
                    user = new UserResponseDto();
                    user.setRole("Retailer");
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoto(rs.getString("photo"));
                    return user;
                }
            }

            query = "SELECT * FROM Supplier WHERE email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                if (PasswordUtils.checkPassword(password, hashedPassword)) {
                    user = new UserResponseDto();
                    user.setRole("Supplier");
                    user.setId(UUID.fromString(rs.getString("id")));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoto(rs.getString("photo"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
