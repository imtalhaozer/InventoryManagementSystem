package com.example.inventorymanagementsystem.servlet.Supplier;

import com.example.inventorymanagementsystem.dto.request.supplier.SupplierCreateDto;
import com.example.inventorymanagementsystem.service.SupplierService;
import com.example.inventorymanagementsystem.util.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/public-register-supplier")
public class SupplierRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SupplierService supplierService;

    @Override
    public void init() throws ServletException {
        try {
            this.supplierService = new SupplierService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize SupplierService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            SupplierCreateDto supplierCreateDto = extractSupplierCreateDto(request);
            handleValidation(supplierCreateDto, request, response);

            if (registerSupplier(supplierCreateDto, request)) {
                forwardWithToast(request, response, "Supplier successfully registered!", "success");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                forwardWithToast(request, response, "Something went wrong during registration!", "error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            forwardWithToast(request, response, "An unexpected error occurred.", "error");
        }
    }

    private SupplierCreateDto extractSupplierCreateDto(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String photo = null; // Placeholder for photo handling
        return new SupplierCreateDto(name, email, password, photo);
    }

    private void handleValidation(SupplierCreateDto supplierCreateDto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validationErrors = ValidationUtil.validate(supplierCreateDto);
        if (validationErrors != null) {
            forwardWithToast(request, response, validationErrors, "error");
        }
    }

    private boolean registerSupplier(SupplierCreateDto supplierCreateDto, HttpServletRequest request) throws Exception {
        return supplierService.registerSupplier(supplierCreateDto);
    }

    private void forwardWithToast(HttpServletRequest request, HttpServletResponse response, String message, String toastType) throws ServletException, IOException {
        request.setAttribute("toastMessage", message);
        request.setAttribute("toastType", toastType);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

}
