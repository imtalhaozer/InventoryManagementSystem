package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.request.product.ProductCreateDto;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.models.Supplier;
import com.example.inventorymanagementsystem.service.ProductService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/private/add-product")
public class ProductAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private  ProductService productService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
        try {
            String name = request.getParameter("name");
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            ProductCreateDto productCreateDto = new ProductCreateDto(supplier.getId(),name, stockQuantity, price, discount);

            boolean isAdded = productService.addProduct(
                    productCreateDto.getSupplierId(),
                    productCreateDto.getName(),
                    productCreateDto.getStockQuantity(),
                    productCreateDto.getPrice(),
                    productCreateDto.getDiscount()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
