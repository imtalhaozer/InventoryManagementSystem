<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto" %>
<%
    RetailerResponseDto retailer = (RetailerResponseDto) request.getSession().getAttribute("retailer");
    if (retailer != null) {
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>Login Page</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Login</div>
        <div class="card-body">
            <form action="retailer-login" method="post">
                <div class="form-group">
                    <label>Email address</label>
                    <input type="email" name="login-email" class="form-control" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="login-password" class="form-control" placeholder="Password">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/includes/footer.jsp"%>
</body>
</html>
