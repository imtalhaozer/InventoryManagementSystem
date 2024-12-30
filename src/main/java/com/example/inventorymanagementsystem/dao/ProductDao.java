package com.example.inventorymanagementsystem.dao;

import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class ProductDao {
    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public ProductResponseDto getProductById(Long id){
        ProductResponseDto product = new ProductResponseDto();
        try {
            query = "select * from Product where id=?";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(UUID supplierId, String name, int stockQuantity, double price, double discount) {
        try {

            query = "insert into Product(supplierId, name, stockQuantity, price, discount) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, supplierId.toString());
            pst.setString(2, name);
            pst.setInt(3, stockQuantity);
            pst.setDouble(4, price);
            pst.setDouble(5, discount);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isProductExists(String name) {
        try {
            query = "select * from Product where name=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> products = new ArrayList<ProductResponseDto>();
        try {
            query = "select * from Product";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductResponseDto product = new ProductResponseDto();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
