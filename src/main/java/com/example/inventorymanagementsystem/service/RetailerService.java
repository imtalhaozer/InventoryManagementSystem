package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.ReatilerDao;
import com.example.inventorymanagementsystem.dto.request.retailer.RetailerCreateDto;
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

    public boolean registerRetailer(RetailerCreateDto retailerCreateDto) {
        if (reatilerDao.isRetailerMailExist(retailerCreateDto.getEmail())) {
            return false;
        }
        reatilerDao.addRetailer(retailerCreateDto);
        return true;
    }

    public String getRetailerIdByEmail(String email) throws SQLException {

        return reatilerDao.getRetailerIdByMail(email);
    }

    public RetailerResponseDto LoginRetailer(String email, String password) {
        return reatilerDao.LoginRetailer(email, password);
    }
}
