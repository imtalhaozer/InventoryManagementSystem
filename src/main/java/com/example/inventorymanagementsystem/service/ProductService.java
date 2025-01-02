package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.ProductDao;
import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private final ProductDao productDao;

    public ProductService() throws SQLException, ClassNotFoundException {
        Connection con = DbCon.getConnection();
        this.productDao = new ProductDao(con);
    }

    public boolean addProduct(UUID supplierId, String name, int stockQuantity, double price, double discount) {
        if (productDao.isProductExists(name)) {
            return false;
        }
        productDao.addProduct(supplierId, name, stockQuantity, price, discount);
        return true;
    }

    public ProductResponseDto getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<ProductResponseDto> getProductsBySupplierId(UUID supplierId) {
        return productDao.getProductsBySupplierId(supplierId);
    }

    public void updateProduct(Long id,String name, int stockQuantity, double price, double discount) {
        productDao.updateProduct(id,name, stockQuantity, price, discount);
    }

    public void removeProduct(Long id) {
        productDao.removeProduct(id);
    }

}
