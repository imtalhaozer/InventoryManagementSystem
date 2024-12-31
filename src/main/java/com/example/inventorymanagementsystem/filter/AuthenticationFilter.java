package com.example.inventorymanagementsystem.filter;

import com.example.inventorymanagementsystem.dto.response.user.UserResponseDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        if (uri.contains("private")) {
            Cookie[] cookies = httpRequest.getCookies();
            boolean authenticated = false;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("authToken".equals(cookie.getName()) && "authenticatedUser".equals(cookie.getValue())) {
                        authenticated = true;
                        break;
                    }
                }
            }

            if (!authenticated) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Unauthorized");
                return;
            }
        }

        chain.doFilter(request, response);
    }

}
