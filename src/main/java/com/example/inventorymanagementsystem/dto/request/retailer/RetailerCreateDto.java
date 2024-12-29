package com.example.inventorymanagementsystem.dto.request.retailer;

public class RetailerCreateDto {
    private String name;
    private String email;
    private String password;
    private String photo;

    public RetailerCreateDto(String name, String email, String password, String photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
