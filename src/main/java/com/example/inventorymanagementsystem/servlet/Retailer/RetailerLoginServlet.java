package com.example.inventorymanagementsystem.servlet.Retailer;

import com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto;
import com.example.inventorymanagementsystem.service.RetailerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/retailer-login")
public class RetailerLoginServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out=response.getWriter()){
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            try {
                RetailerResponseDto retailer= retailerService.LoginRetailer(email,password);

                if (retailer !=null){
                    request.getSession().setAttribute("retailer",retailer);
                    response.sendRedirect("index.jsp");
                }else{
                    out.print("user login failed");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
