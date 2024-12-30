package com.example.inventorymanagementsystem.servlet.Retailer;

import com.example.inventorymanagementsystem.dto.request.retailer.RetailerCreateDto;
import com.example.inventorymanagementsystem.dto.response.ResponseDto;
import com.example.inventorymanagementsystem.service.CartService;
import com.example.inventorymanagementsystem.service.RetailerService;

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
            RetailerCreateDto retailerCreateDto = new RetailerCreateDto(name, email, password, photo);

            boolean isRegistered = retailerService.registerRetailer(
                    retailerCreateDto.getName(),
                    retailerCreateDto.getEmail(),
                    retailerCreateDto.getPassword(),
                    retailerCreateDto.getPhoto()
            );

            //create new cart for retailer
            String retailerId = retailerService.getRetailerIdByEmail(email);
            cartService.addCart(retailerId);

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
            e.printStackTrace();
        }
    }
}
