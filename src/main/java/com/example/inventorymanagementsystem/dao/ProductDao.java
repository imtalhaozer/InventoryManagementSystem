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
                product.getSupplierId().fromString(rs.getString("supplierId"));
                product.setName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                product.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addProduct(UUID supplierId, String name, int stockQuantity, double price, double discount, String description) {
        try {

            query = "insert into Product(supplierId, name, stockQuantity, price, discount, description) values(?,?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, supplierId.toString());
            pst.setString(2, name);
            pst.setInt(3, stockQuantity);
            pst.setDouble(4, price);
            pst.setDouble(5, discount);
            pst.setString(6, description);
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
                product.getSupplierId().fromString(rs.getString("supplierId"));
                product.setName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductResponseDto> getProductsBySupplierId(UUID supplierId) {
        List<ProductResponseDto> products = new ArrayList<ProductResponseDto>();
        try {
            query = "select * from Product where supplierId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, supplierId.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                ProductResponseDto product = new ProductResponseDto();
                product.setId(rs.getLong("id"));
                product.getSupplierId().fromString(rs.getString("supplierId"));
                product.setName(rs.getString("name"));
                product.setStockQuantity(rs.getInt("stockQuantity"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getDouble("discount"));
                product.setDescription(rs.getString("description"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void decreaseStockQuantity(Long id, int quantity) {
        try {
            query = "update Product set stockQuantity = stockQuantity - ? where id = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, quantity);
            pst.setLong(2, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Long> getProductIdListBySupplierId(UUID supplierId) {
        List<Long> productIds = new ArrayList<Long>();
        try {
            query = "select id from Product where supplierId=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, supplierId.toString());
            rs = pst.executeQuery();
            while (rs.next()) {
                productIds.add(rs.getLong("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productIds;
    }

    public void updateProduct(Long id, String name, int stockQuantity, double price, double discount){
        try {
            query = "update Product set name=?, stockQuantity=?, price=?, discount=? where id=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, stockQuantity);
            pst.setDouble(3, price);
            pst.setDouble(4, discount);
            pst.setLong(5, id);

            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(Long id){
        try {
            query = "delete from Product where id=?";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getIdByName(String name){
        Long id = null;
        try {
            query = "select id from Product where name=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
