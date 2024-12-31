package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/private/get-cart-items")
public class GetCartItemsServlet extends HttpServlet {
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = (String) request.getSession().getAttribute("cartId");

        try {
            List<CartItemResponseDto> cartItems = cartItemService.getCartItems(id);
            double sum = 0;
            for (CartItemResponseDto cartItem : cartItems) {
                sum += cartItem.getProduct().getPrice() * cartItem.getQuantity()*(1-cartItem.getProduct().getDiscount());
            }

            request.setAttribute("sum", sum);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
