package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.service.BillItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-bill-item")
public class DeleteBillItemServlet extends HttpServlet {
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
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            billItemService.deleteBillItem(id);
            response.sendRedirect(request.getContextPath()+"/get-order-approvals");
        } catch (Exception e) {
            response.setStatus(500);
        }
    }
}
