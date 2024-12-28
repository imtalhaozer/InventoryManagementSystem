package com.example.inventorymanagementsystem.servlet.Retailer;

import com.example.inventorymanagementsystem.dto.request.retailer.RetailerGetRequestDto;
import com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto;
import com.example.inventorymanagementsystem.service.RetailerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/retailer/get")
public class RetailerGetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RetailerService retailerService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        try {
            this.retailerService = new RetailerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            RetailerGetRequestDto requestDto = objectMapper.readValue(request.getReader(), RetailerGetRequestDto.class);

            if (requestDto.getEmail()== null || requestDto.getEmail().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(response.getWriter(), "Error: Email is required.");
                return;
            }

            RetailerResponseDto retailer = retailerService.getRetailerByEmail(requestDto.getEmail());

            if (retailer == null || retailer.getEmail() == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                objectMapper.writeValue(response.getWriter(), "Error: Retailer not found.");
                return;
            }

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            objectMapper.writeValue(response.getWriter(), retailer);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            objectMapper.writeValue(response.getWriter(), "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
