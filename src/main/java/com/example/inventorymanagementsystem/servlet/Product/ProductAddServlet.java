package com.example.inventorymanagementsystem.servlet.Product;

import com.example.inventorymanagementsystem.dto.request.product.ProductRequestDto;
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
    private ProductService productService;
    private ProductImageService productImageService;

    @Override
    public void init() throws ServletException {
        try {
            this.productService = new ProductService();
            this.productImageService = new ProductImageService();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize services", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");

        if (supplier == null) {
            handleError(request, response, "User is not logged in", "error");
            return;
        }

        try {
            ProductRequestDto productRequestDto = extractProductData(request, supplier);
            File tempFile1 = saveUploadedFile(request.getPart("photo"));
            File tempFile2 = saveUploadedFile(request.getPart("photo2"));

            boolean isAdded = addProductAndImages(productRequestDto, tempFile1, tempFile2);

            handleSuccess(request, response, isAdded, tempFile1, tempFile2);

        } catch (Exception e) {
            throw new RuntimeException("Failed to process product addition", e);
        }
    }

    private ProductRequestDto extractProductData(HttpServletRequest request, UserResponseDto supplier) throws NumberFormatException {
        String name = request.getParameter("name");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        String description = request.getParameter("description");

        return new ProductRequestDto(supplier.getId(), name, stockQuantity, price, discount, description);
    }

    private File saveUploadedFile(Part filePart) throws IOException {
        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        File tempFile = new File(System.getProperty("java.io.tmpdir"), filePart.getSubmittedFileName());
        filePart.write(tempFile.getAbsolutePath());
        return tempFile;
    }

    private boolean addProductAndImages(ProductRequestDto productRequestDto, File tempFile1, File tempFile2) throws Exception {
        boolean isAdded = productService.addProduct(productRequestDto);

        if (isAdded) {
            Long productId = productService.getIdByName(productRequestDto.getName());
            productImageService.addProductImage(productId, tempFile1);

            if (tempFile2 != null) {
                productImageService.addProductImage(productId, tempFile2);
            }
        }

        return isAdded;
    }

    private void handleSuccess(HttpServletRequest request, HttpServletResponse response, boolean isAdded, File tempFile1, File tempFile2) throws ServletException, IOException {
        request.setAttribute("toastMessage", isAdded ? "Product successfully added!" : "Failed to add product!");
        request.setAttribute("toastType", isAdded ? "success" : "error");
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);

        cleanupTempFiles(tempFile1, tempFile2);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message, String toastType) throws ServletException, IOException {
        request.setAttribute("toastMessage", message);
        request.setAttribute("toastType", toastType);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void cleanupTempFiles(File... files) {
        for (File file : files) {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }
}

