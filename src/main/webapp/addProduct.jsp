<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%
  UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
  UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
  if (retailer == null && supplier == null) {
    response.sendRedirect("login.jsp");
  }

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@include file="includes/head.jsp" %>
  <title>Product Registration</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
  <div class="card w-50 mx-auto my-5">
    <div class="card-header text-center">Product Registration</div>
    <div class="card-body">
      <form id="product-register-form" method="post" action="add-product">
        <div class="form-group">
          <label>Product Name</label>
          <input type="text" name="name" class="form-control" placeholder="Enter product name" required>
        </div>
        <div class="form-group">
          <label>Stock Quantity</label>
          <input type="number" name="stockQuantity" class="form-control" placeholder="Enter stock quantity" required>
        </div>
        <div class="form-group">
          <label>Price</label>
          <input type="number" step="0.01" name="price" class="form-control" placeholder="Enter product price" required>
        </div>
        <div class="form-group">
          <label>Discount (%)</label>
          <input type="number" step="0.01" name="discount" class="form-control" placeholder="Enter discount percentage" required>
        </div>

        <div class="form-group">
          <label>Product Image</label>
          <input type="file" name="photo" class="form-control-file" accept="image/*">
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary">Register Product</button>
        </div>
      </form>
    </div>
  </div>
</div>
<%@include file="/includes/footer.jsp"%>
</body>
</html>
