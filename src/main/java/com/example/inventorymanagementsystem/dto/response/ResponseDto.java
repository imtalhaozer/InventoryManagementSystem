package com.example.inventorymanagementsystem.dto.response;

public class ResponseDto {
    private boolean success;
    private String message;

    public ResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
