package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.BillItemDao;
import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillItemService {
    private BillItemDao billItemDao;

    public BillItemService() throws SQLException, ClassNotFoundException {
        Connection con = DbCon.getConnection();
        this.billItemDao = new BillItemDao(con);
    }

    public void addBillItem(String billId, List<CartItemResponseDto> cartItems) {
        billItemDao.addBillItem(billId,cartItems);
    }
}
