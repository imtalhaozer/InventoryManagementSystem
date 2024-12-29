
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    List<ProductResponseDto> products = (List<ProductResponseDto>) request.getAttribute("products");

    if (retailer == null && supplier == null) {
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
<div class="container">
    <div class="card-header my-3">All Products</div>
    <div class="row">
        <%
            if (products != null && !products.isEmpty()) {
                for (ProductResponseDto product : products) {
        %>
        <div class="col-md-3">
            <div class="card w-100 mb-3" style="width: 18rem;">
                <img class="card-img-top" src="https://png.pngtree.com/png-clipart/20230110/ourlarge/pngtree-red-fresh-apple-fruit-png-image_6558133.png" alt="<%= product.getName() %>">
                <div class="card-body">
                    <h5 class="card-title"><%= product.getName() %></h5>
                    <h6 class="price">Price: $<%= product.getPrice() %></h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="add-to-cart?id=<%=product.getId()%>" class="btn btn-dark">Add to Cart</a>
                        <a href="#" class="btn btn-primary">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p>No products available.</p>
        <%
            }
        %>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>