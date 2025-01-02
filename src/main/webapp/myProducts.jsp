<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  List<ProductResponseDto> products = (List<ProductResponseDto>) request.getAttribute("products");
  UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
  UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="includes/head.jsp" %>
  <title>Cart Pages</title>
  <style type="text/css">
    .table tbody td {
      vertical-align: middle;
    }
    .form-control {
      width: 100%;
      font-size: 14px;
      padding: 5px;
      text-align: center;
    }
  </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
  <table class="table table-light">
    <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Stock</th>
      <th scope="col">Price</th>
      <th scope="col">Discount</th>
      <th scope="col">Update</th>
      <th scope="col">Cancel</th>
    </tr>
    </thead>
    <tbody>
    <% if (products != null && !products.isEmpty()) {
      for (ProductResponseDto product : products) { %>
    <form action="<%= request.getContextPath() %>/update-product" method="post">
      <tr>
        <input type="hidden" name="id" value="<%= product.getId() %>">


        <td><input type="text" class="form-control" name="name" value="<%= product.getName() %>"></td>
        <td><input type="number" class="form-control" name="stockQuantity" value="<%= product.getStockQuantity() %>"></td>
        <td><input type="number" class="form-control" name="price" value="<%= product.getPrice() %>"></td>
        <td><input type="number" class="form-control" name="discount" value="<%= product.getDiscount() %>"></td>

        <td><button type="submit" class="btn btn-primary">Update</button></td>

        <td><a href="product-remove?id=<%= product.getId() %>" class="btn btn-danger">Remove</a></td>
      </tr>
    </form>
    <% } } %>
    </tbody>
  </table>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>


