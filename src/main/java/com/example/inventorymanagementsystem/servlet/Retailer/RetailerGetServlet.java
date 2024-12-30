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

@WebServlet("/private/retailer/get")
public class RetailerGetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RetailerService retailerService;

    @Override
    public void init() throws ServletException {
        try {
            this.retailerService = new RetailerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RetailerResponseDto retailer = (RetailerResponseDto) request.getSession().getAttribute("retailer");
        try {
            retailerService.getRetailerIdByEmail(request.getParameter(retailer.getEmail()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
