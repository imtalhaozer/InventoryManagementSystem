package com.example.inventorymanagementsystem.servlet.Retailer;

import com.example.inventorymanagementsystem.dto.request.retailer.RetailerCreateDto;
import com.example.inventorymanagementsystem.dto.response.ResponseDto;
import com.example.inventorymanagementsystem.service.RetailerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register-retailer")
public class RetailerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RetailerService retailerService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        try {
            this.retailerService = new RetailerService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            RetailerCreateDto retailerCreateDto = objectMapper.readValue(request.getReader(), RetailerCreateDto.class);

            if (retailerCreateDto.getEmail() == null || retailerCreateDto.getEmail().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(response.getWriter(), new ResponseDto(false, "Email cannot be empty!"));
                return;
            }

            boolean isRegistered = retailerService.registerRetailer(
                    retailerCreateDto.getName(),
                    retailerCreateDto.getEmail(),
                    retailerCreateDto.getPassword(),
                    retailerCreateDto.getPhoto()
            );

            ResponseDto responseDTO = new ResponseDto();
            if (isRegistered) {
                responseDTO.setSuccess(true);
                responseDTO.setMessage("Retailer successfully registered!");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Email already exists!");
                response.setStatus(HttpServletResponse.SC_CONFLICT);
            }

            objectMapper.writeValue(response.getWriter(), responseDTO);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ResponseDto errorResponse = new ResponseDto(false, "An error occurred: " + e.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }
}
