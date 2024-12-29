package com.example.inventorymanagementsystem.servlet;

import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;
import com.example.inventorymanagementsystem.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        try {
            this.userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("login-email");
        String password = request.getParameter("login-password");

            try {
                UserResponseDto user= userService.Login(email,password);

                if (user != null) {
                    if ("Retailer".equals(user.getRole())) {
                        request.getSession().setAttribute("retailer", user);
                        response.sendRedirect("index.jsp");
                    } else if ("Supplier".equals(user.getRole())) {
                        request.getSession().setAttribute("supplier", user);
                        response.sendRedirect("index.jsp");
                    }

                } else {
                    request.setAttribute("toastMessage", "Invalid email or password.");
                    request.setAttribute("toastType", "error");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

