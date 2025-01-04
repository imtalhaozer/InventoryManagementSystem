package com.example.inventorymanagementsystem.dao;

import com.cloudinary.utils.ObjectUtils;
import com.example.inventorymanagementsystem.util.CloudinaryConfig;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductImageDao {

    private Connection con;

    private String query;

    private PreparedStatement pst;

    private ResultSet rs;

    public ProductImageDao(Connection con) {
        this.con = con;
    }

    public void addProductImage(Long productId, File file) {
        try {
            Map<String, Object> uploadResult =
                    CloudinaryConfig.getCloudinary().uploader().upload(file, ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("secure_url");

            query = "insert into ProductImage(productId, imageUrl) values(?,?)";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, productId);
            pst.setString(2, imageUrl);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getUrlListByProductId(Long productId){
        try {
            query = "select imageUrl from ProductImage where productId=?";
            pst = this.con.prepareStatement(query);
            pst.setLong(1, productId);
            rs = pst.executeQuery();
            List<String> imageUrlList = new ArrayList<>();
            while(rs.next()){
                imageUrlList.add(rs.getString("imageUrl"));
            }
            return imageUrlList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}