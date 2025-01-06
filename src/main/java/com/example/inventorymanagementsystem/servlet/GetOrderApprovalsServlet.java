package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.response.BillItemResponseDto;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.BillItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/get-order-approvals")
public class GetOrderApprovalsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BillItemService billItemService;

    @Override
    public void init() throws ServletException {
        try {
            this.billItemService = new BillItemService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
        try {
            List<BillItemResponseDto> billItems = billItemService.getBillItemsSupplier(supplier.getId());
            request.setAttribute("billItems", billItems);
            request.getRequestDispatcher("approveOrders.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
