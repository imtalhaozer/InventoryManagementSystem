package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/remove-from-cart")
public class DeleteCartItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartItemService cartItemService;

    @Override
    public void init() throws ServletException {
        try {
            this.cartItemService = new CartItemService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartId = (String) request.getSession().getAttribute("cartId");
        Long productId = Long.parseLong(request.getParameter("id"));

        try {
            cartItemService.deleteCartItem(cartId, productId);
            response.setStatus(HttpServletResponse.SC_OK);
            request.getRequestDispatcher("/private-get-cart-items").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
