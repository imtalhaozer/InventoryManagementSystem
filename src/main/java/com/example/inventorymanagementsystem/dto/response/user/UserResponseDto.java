package com.example.inventorymanagementsystem.dto.response.user;

import java.util.UUID;

public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String photo;
    private String role;

    public UserResponseDto() {
    }

    public UserResponseDto(UUID id, String name, String email, String photo, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
