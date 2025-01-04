package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.ProductDao;
import com.example.inventorymanagementsystem.dao.ProductImageDao;
import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProductService {
    private final ProductDao productDao;
    private final ProductImageDao productImageDao;

    public ProductService() throws SQLException, ClassNotFoundException {
        Connection con = DbCon.getConnection();
        this.productDao = new ProductDao(con);
        this.productImageDao = new ProductImageDao(con);
    }

    public boolean addProduct(UUID supplierId, String name, int stockQuantity, double price, double discount, String description) {
        if (productDao.isProductExists(name)) {
            return false;
        }
        productDao.addProduct(supplierId, name, stockQuantity, price, discount, description);
        return true;
    }

    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto product = productDao.getProductById(id);
        product.setImageUrlList(productImageDao.getUrlListByProductId(id));
        return product;
    }

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> products = productDao.getAllProducts();
        for (ProductResponseDto product : products) {
            product.setImageUrlList(productImageDao.getUrlListByProductId(product.getId()));
        }
        return products;
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

    public Long getIdByName(String name) {
        return productDao.getIdByName(name);
    }

}
