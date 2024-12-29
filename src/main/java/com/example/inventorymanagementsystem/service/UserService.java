package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.UserDao;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final UserDao userDao;

    public UserService() throws SQLException, ClassNotFoundException  {
        Connection con = DbCon.getConnection();
        this.userDao = new UserDao(con);
    }

    public UserResponseDto Login(String email, String password) {
        return userDao.loginUser(email, password);
    }
}
