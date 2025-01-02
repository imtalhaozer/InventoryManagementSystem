package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-product")
public class ProductUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        try {
            this.productService = new ProductService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String stockQuantity = request.getParameter("stockQuantity");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        if (name == null || stockQuantity == null || price == null || discount == null) {
            response.setStatus(400);
            return;
        }
        try {
            int stockQuantityInt = Integer.parseInt(stockQuantity);
            double priceDouble = Double.parseDouble(price);
            double discountDouble = Double.parseDouble(discount);
            productService.updateProduct(id, name, stockQuantityInt, priceDouble, discountDouble);

            response.sendRedirect(request.getContextPath()+"/products-supplier");
        } catch (Exception e) {
            response.setStatus(500);
        }
    }
}
