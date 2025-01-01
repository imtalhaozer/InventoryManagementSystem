package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/private/quantity")
public class QuantityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartItemService cartItemService;

    @Override
    public void init() throws ServletException {
        try {
            this.cartItemService = new CartItemService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String id = (String) request.getSession().getAttribute("cartId");
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            if(action != null){
                if(action.equals("inc")){
                    cartItemService.checkItemForQuantity(id,productId,1);
                }
                }
                if(action.equals("dec")){
                    cartItemService.checkItemForQuantity(id,productId,-1);
                }
            response.sendRedirect(request.getContextPath() + "/private-get-cart-items");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
