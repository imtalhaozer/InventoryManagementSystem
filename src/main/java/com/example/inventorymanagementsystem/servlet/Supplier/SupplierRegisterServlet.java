package com.example.inventorymanagementsystem.servlet.Supplier;

import com.example.inventorymanagementsystem.dto.request.supplier.SupplierCreateDto;
import com.example.inventorymanagementsystem.service.SupplierService;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String photo = null; //request.getParameter("photo");

            SupplierCreateDto supplierCreateDto = new SupplierCreateDto(name, email, password, photo);

            boolean isRegistered = supplierService.registerSupplier(
                    supplierCreateDto.getName(),
                    supplierCreateDto.getEmail(),
                    supplierCreateDto.getPassword(),
                    supplierCreateDto.getPhoto()
            );
            String message;
            if (isRegistered){
                message = "Supplier successfully registered!";
            }
            else {
                message = "Something went wrong!";
            }
            request.setAttribute("toastMessage", message);
            request.setAttribute("toastType", isRegistered ? "success" : "error");

            request.getRequestDispatcher("register.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
