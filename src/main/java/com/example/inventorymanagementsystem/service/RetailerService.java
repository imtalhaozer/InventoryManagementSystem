package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.ReatilerDao;
import com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;

public class RetailerService {
    private final ReatilerDao reatilerDao;

    public RetailerService() throws SQLException, ClassNotFoundException  {
        Connection con = DbCon.getConnection();
        this.reatilerDao = new ReatilerDao(con);
    }

    public boolean registerRetailer(String name, String email, String password, String photo) {
        if (reatilerDao.isRetailerMailExist(email)) {
            return false;
        }
        reatilerDao.addRetailer(name, email, password, photo);
        return true;
    }

    public RetailerResponseDto getRetailerByEmail(String email) {

        return reatilerDao.getRetailerByMail(email);
    }

    public RetailerResponseDto LoginRetailer(String email, String password) {
        return reatilerDao.LoginRetailer(email, password);
    }
}
