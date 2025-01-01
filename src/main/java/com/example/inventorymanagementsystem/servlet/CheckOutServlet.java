package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.service.BillItemService;
import com.example.inventorymanagementsystem.service.BillService;
import com.example.inventorymanagementsystem.service.CartItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/private-checkout")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CartItemService cartItemService;
    private BillItemService billItemService;
    private BillService billService;

    @Override
    public void init() throws ServletException {
        try {
            this.cartItemService = new CartItemService();
            this.billItemService = new BillItemService();
            this.billService = new BillService();
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

            String billId = UUID.randomUUID().toString();
            billService.addBill(billId, id);

            billItemService.addBillItem(billId, cartItems);
            for (CartItemResponseDto cartItem : cartItems) {
                cartItemService.deleteCartItem(id, cartItem.getProduct().getId());
            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
