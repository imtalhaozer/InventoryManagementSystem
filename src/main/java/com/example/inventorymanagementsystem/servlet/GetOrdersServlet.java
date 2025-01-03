package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.response.BillItemResponseDto;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.BillItemService;
import com.example.inventorymanagementsystem.service.BillService;
import com.example.inventorymanagementsystem.service.RetailerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/get-orders")
public class GetOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BillItemService billItemService;
    private RetailerService retailerService;
    private BillService billService;

    @Override
    public void init() throws ServletException {
        try {
            this.billItemService = new BillItemService();
            this.retailerService = new RetailerService();
            this.billService = new BillService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
        try {
            String retailerId = retailerService.getRetailerIdByEmail(retailer.getEmail());
            List<BillItemResponseDto> billItems =new ArrayList<>();
            for (String billId : billService.getBillIdByRetailerId(retailerId)) {
                billItems.addAll(billItemService.getBillItemsByBillId(billId));
            }
            request.setAttribute("billItems", billItems);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}

