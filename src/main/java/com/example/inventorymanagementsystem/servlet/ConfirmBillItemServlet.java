package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.service.BillItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/confirm-bill-item")
public class ConfirmBillItemServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        try {
            billItemService.confirmBillItem(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
