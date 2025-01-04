package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.request.product.ProductCreateDto;
import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.ProductImageService;
import com.example.inventorymanagementsystem.service.ProductService;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/private-add-product")
@MultipartConfig
public class ProductAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private  ProductService productService;
    private ProductImageService productImageService;

    @Override
    public void init() throws ServletException {
        try {
            this.productService = new ProductService();
            this.productImageService = new ProductImageService();
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
            String description = request.getParameter("description");

            Part filePart1 = request.getPart("photo");
            File tempFile1 = new File(System.getProperty("java.io.tmpdir"), filePart1.getSubmittedFileName());
            filePart1.write(tempFile1.getAbsolutePath());

            Part filePart2 = request.getPart("photo2");
            File tempFile2 = null;
            if (filePart2 != null && filePart2.getSize() > 0) {
                tempFile2 = new File(System.getProperty("java.io.tmpdir"), filePart2.getSubmittedFileName());
                filePart2.write(tempFile2.getAbsolutePath());
            }

            ProductCreateDto productCreateDto = new ProductCreateDto(supplier.getId(),name, stockQuantity, price, discount, description);

            boolean isAdded = productService.addProduct(
                    productCreateDto.getSupplierId(),
                    productCreateDto.getName(),
                    productCreateDto.getStockQuantity(),
                    productCreateDto.getPrice(),
                    productCreateDto.getDiscount(),
                    productCreateDto.getDescription()
            );

            productImageService.addProductImage(productService.getIdByName(name), tempFile1);

            if (tempFile2 != null) {
                productImageService.addProductImage(productService.getIdByName(name), tempFile2);
            }

            if(isAdded){
                request.setAttribute("toastMessage", "Product successfully added!");
            }
            else {
                request.setAttribute("toastMessage", "Failed to add product!");
            }
            request.setAttribute("toastType", isAdded ? "success" : "error");
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);

            tempFile1.delete();
            if (tempFile2 != null) {
                tempFile2.delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
