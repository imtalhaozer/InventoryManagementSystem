package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.BillDao;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillService {

    private BillDao billDao;

    public BillService() throws SQLException, ClassNotFoundException{
        Connection con = DbCon.getConnection();
        this.billDao = new BillDao(con);
    }

    public void addBill(String cartId, String userId){
        billDao.addBill(cartId, userId);
    }

    public List<String> getBillIdByRetailerId(String retailerId){
        return billDao.getBillIdByRetailerId(retailerId);
    }
}
