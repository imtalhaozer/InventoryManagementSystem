<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%
  UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
  UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
  String toastMessage = (String) request.getAttribute("toastMessage");
  String toastType = (String) request.getAttribute("toastType");
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
  <% if (toastMessage != null) { %>
  <div aria-live="polite" aria-atomic="true" style="position: fixed; top: 3rem; right: 1rem; z-index: 1050;">
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <strong class="mr-auto"><%= "success".equals(toastType) ? "Başarılı" : "Hata" %></strong>
      </div>
      <div class="toast-body">
        <%= toastMessage %>
      </div>
    </div>
  </div>
  <% } %>
  <div class="card w-50 mx-auto my-5">
    <div class="card-header text-center">Product Registration</div>
    <div class="card-body">
      <form id="product-register-form" method="post" enctype="multipart/form-data" action="private-add-product">
        <div class="form-group">
          <label>Product Name</label>
          <input type="text" name="name" class="form-control" placeholder="Enter product name" required>
        </div>
        <div class="form-group">
          <label>Stock Quantity</label>
          <input type="number" name="stockQuantity" class="form-control" placeholder="Enter stock quantity" min="0" step="1" required>
        </div>
        <div class="form-group">
          <label>Price</label>
          <input type="number" name="price" class="form-control" placeholder="Enter product price" min="0" step="0.01" required>
        </div>
        <div class="form-group">
          <label>Discount (%)</label>
          <input type="number" name="discount" class="form-control" placeholder="Enter discount percentage" min="0" max="99" step="0.01" required>
        </div>
        <div class="form-group">
          <label>Description</label>
          <input type="text" name="description" class="form-control" placeholder="Enter description" required>
        </div>
        <div class="form-group">
          <label>Product Image</label>
          <input type="file" name="photo" class="form-control-file" accept="image/*">
          <input type="file" name="photo2" class="form-control-file" accept="image/*">
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
