package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/products-supplier")
public class ProductsBySupplierServlet extends HttpServlet {
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
        response.setContentType("application/json;charset=UTF-8");
        try {
            UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
            List<ProductResponseDto> products = productService.getProductsBySupplierId(supplier.getId());

            request.setAttribute("products", products);
            request.getRequestDispatcher("myProducts.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
