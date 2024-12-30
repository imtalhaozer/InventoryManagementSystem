package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.CartDao;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class CartService {
    private CartDao cartDao;

    public CartService() throws SQLException, ClassNotFoundException {
        Connection con = DbCon.getConnection();
        this.cartDao = new CartDao(con);
    }

    public void addCart(String userId) {
        cartDao.addCart(userId);
    }

    public Long getCartId(String retailerId) {
        return cartDao.getCartId(retailerId);
    }

}
