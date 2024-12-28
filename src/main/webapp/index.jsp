<%@ page import="com.example.inventorymanagementsystem.dto.response.retailer.RetailerResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    RetailerResponseDto retailer = (RetailerResponseDto) request.getSession().getAttribute("retailer");
    if (retailer == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>ITG - Management System</title>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
</h1>
<br/>

<%@include file="includes/footer.jsp" %>
</body>
</html>