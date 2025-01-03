package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.CartItemDao;
import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.models.CartItem;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartItemService {
    private CartItemDao cartItemDao;

    public CartItemService() throws SQLException, ClassNotFoundException{
        Connection con = DbCon.getConnection();
        this.cartItemDao = new CartItemDao(con);
    }

    public boolean checkItemForQuantity(String cartId, int productId, int quantity){
        return cartItemDao.checkItemForQuantity(cartId, productId, quantity);
    }

    public int getCartItemQuantity(String cartId, int productId){
        return cartItemDao.getCartItemQuantity(cartId, productId);
    }
    public void addCartItem(String cartId, int productId, int quantity){
            cartItemDao.saveCartItem(cartId, productId, quantity);
    }

    public List<CartItemResponseDto> getCartItems(String cartId){
        return cartItemDao.getCartItemsByCartId(cartId);
    }
    public void deleteCartItem(String cartId, Long productId){
        cartItemDao.deleteCartItem(cartId, productId);
    }

}
