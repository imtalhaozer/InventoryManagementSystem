package com.example.inventorymanagementsystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            Cookie authCookie = new Cookie("authToken", "");
            authCookie.setMaxAge(0);
            response.addCookie(authCookie);

            if (request.getSession().getAttribute("retailer") != null) {
                request.getSession().removeAttribute("retailer");
            }
            else if (request.getSession().getAttribute("supplier") != null) {
            request.getSession().removeAttribute("supplier");
            }

            response.sendRedirect("login.jsp");
        }
    }
}
