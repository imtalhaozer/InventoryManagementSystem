package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.request.product.ProductUpdateDto;
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

        try {
            ProductUpdateDto productUpdateDto = extractProductData(request);
            productService.updateProduct(productUpdateDto);

            response.sendRedirect(request.getContextPath()+"/products-supplier");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ProductUpdateDto extractProductData(HttpServletRequest request) throws NumberFormatException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));

        return new ProductUpdateDto(id, name, stockQuantity, price, discount);
    }


}
