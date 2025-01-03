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
    private CartItemService cartItemService;

    @Override
    public void init() throws ServletException {
        try {
            this.productService = new ProductService();
            this.cartItemService = new CartItemService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getSession().getAttribute("cartId");

        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));


            boolean checkItem = cartItemService.checkItemForQuantity(id, productId, quantity);
            if(!checkItem)
                cartItemService.addCartItem(id, productId, quantity);

            response.sendRedirect(request.getContextPath() + "/public-product-get-all");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
