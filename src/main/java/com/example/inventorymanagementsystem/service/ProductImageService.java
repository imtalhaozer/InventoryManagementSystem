package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.ProductImageDao;
import com.example.inventorymanagementsystem.util.DbCon;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductImageService {

    private ProductImageDao productImageDao;

    public ProductImageService() throws SQLException, ClassNotFoundException{
        Connection con = DbCon.getConnection();
        this.productImageDao = new ProductImageDao(con);
    }

    public void addProductImage(Long productId, File file){
        productImageDao.addProductImage(productId, file);
    }

    public List<String> getUrlListByProductId(Long productId){
        return productImageDao.getUrlListByProductId(productId);
    }
}
