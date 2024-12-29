package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.SupplierDao;
import com.example.inventorymanagementsystem.dto.response.supplier.SupplierResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierService {
    private final SupplierDao supplierDao;

    public SupplierService() throws SQLException, ClassNotFoundException  {
        Connection con = DbCon.getConnection();
        this.supplierDao = new SupplierDao(con);
    }

    public boolean registerSupplier(String name, String email, String password, String photo) {
        if (supplierDao.isSupplierExist(email)) {
            return false;
        }
        supplierDao.addSupplier(name, email, password, photo);
        return true;
    }



    public SupplierResponseDto LoginSupplier(String email, String password) {
        return supplierDao.LoginSupplier(email, password);
    }
}
