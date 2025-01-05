package com.example.inventorymanagementsystem.servlet.Retailer;

import com.example.inventorymanagementsystem.dto.request.retailer.RetailerCreateDto;
import com.example.inventorymanagementsystem.service.CartService;
import com.example.inventorymanagementsystem.service.RetailerService;
import com.example.inventorymanagementsystem.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/public-register-retailer")
public class RetailerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RetailerService retailerService;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        try {
            this.retailerService = new RetailerService();
            this.cartService = new CartService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize services", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            RetailerCreateDto retailerCreateDto = extractRetailerCreateDto(request);
            handleValidation(retailerCreateDto, request, response);

            if (registerRetailer(retailerCreateDto, request)) {
                forwardWithToast(request, response, "Retailer successfully registered!", "success");
            } else {
                forwardWithToast(request, response, "Something went wrong during registration!", "error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            forwardWithToast(request, response, "An unexpected error occurred.", "error");
        }
    }

    private RetailerCreateDto extractRetailerCreateDto(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String photo = null; // Placeholder for photo handling
        return new RetailerCreateDto(name, email, password, photo);
    }

    private void handleValidation(RetailerCreateDto retailerCreateDto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validationErrors = ValidationUtil.validate(retailerCreateDto);
        if (validationErrors != null) {
            forwardWithToast(request, response, validationErrors, "error");
        }
    }

    private boolean registerRetailer(RetailerCreateDto retailerCreateDto, HttpServletRequest request) throws Exception {
        if (!retailerService.registerRetailer(retailerCreateDto)) {
            return false;
        }

        String retailerId = retailerService.getRetailerIdByEmail(retailerCreateDto.getEmail());
        cartService.addCart(retailerId);
        return true;
    }

    private void forwardWithToast(HttpServletRequest request, HttpServletResponse response, String message, String toastType) throws ServletException, IOException {
        request.setAttribute("toastMessage", message);
        request.setAttribute("toastType", toastType);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

}
