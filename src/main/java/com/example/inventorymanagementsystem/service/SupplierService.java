package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.SupplierDao;
import com.example.inventorymanagementsystem.dto.request.supplier.SupplierCreateDto;
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

    public boolean registerSupplier(SupplierCreateDto supplierCreateDto) {
        if (supplierDao.isSupplierExist(supplierCreateDto.getEmail())) {
            return false;
        }
        supplierDao.addSupplier(supplierCreateDto);
        return true;
    }



    public SupplierResponseDto LoginSupplier(String email, String password) {
        return supplierDao.LoginSupplier(email, password);
    }
}
