package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.CartItemDao;
import com.example.inventorymanagementsystem.models.CartItem;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;

public class CartItemService {
    private CartItemDao cartItemDao;

    public CartItemService() throws SQLException, ClassNotFoundException{
        Connection con = DbCon.getConnection();
        this.cartItemDao = new CartItemDao(con);
    }

    public void addCartItem(String cartId, int productId, int quantity){
        cartItemDao.saveCartItem(cartId, productId, quantity);
    }

}
