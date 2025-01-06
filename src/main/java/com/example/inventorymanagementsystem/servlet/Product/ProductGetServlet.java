package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;
import com.example.inventorymanagementsystem.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/public-product-get")
public class ProductGetServlet extends HttpServlet {
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


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            Long productId = Long.parseLong(id);
            ProductResponseDto product = productService.getProductById(productId);

            request.setAttribute("product", product);

            request.getRequestDispatcher("/product.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
