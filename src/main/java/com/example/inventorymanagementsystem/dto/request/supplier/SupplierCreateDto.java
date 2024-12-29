package com.example.inventorymanagementsystem.dto.request.supplier;

public class SupplierCreateDto {
    private String name;
    private String email;
    private String password;
    private String photo;

    public SupplierCreateDto(String name, String email,String password, String photo) {
        this.name = name;
        this.password = password;
        this.email = email;
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
