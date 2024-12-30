package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.CartItemService;
import com.example.inventorymanagementsystem.service.ProductService;
import com.example.inventorymanagementsystem.service.RetailerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
;
import java.sql.SQLException;


@WebServlet("/private/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private RetailerService retailerService;
    private CartItemService cartItemService;

    @Override
    public void init() throws ServletException {
        try {
            this.productService = new ProductService();
            this.retailerService = new RetailerService();
            this.cartItemService = new CartItemService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");

        try {
            String id = retailerService.getRetailerIdByEmail(retailer.getEmail());
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String cartId = id;

            cartItemService.addCartItem(cartId, productId, quantity);

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
