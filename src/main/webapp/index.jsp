
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    List<ProductResponseDto> products = (List<ProductResponseDto>) request.getSession().getAttribute("products");
    String error = (String) request.getAttribute("error");

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
                    <div class="d-flex justify-content-between">
                        <h6 class="price">Price: $<%= product.getPrice() %></h6>
                        <h6 class="discount text-danger">Discount: <%= Math.round(product.getDiscount() * 100) %>%</h6>
                    </div>
                    <h6 class="total-price">Total Price: <%= String.format("%.2f",product.getPrice() * (1-product.getDiscount())) %></h6>
                    <% if (retailer != null) { %>
                    <div class="mt-3 d-flex justify-content-between">
                        <form action="private/add-to-cart" method="POST" class="d-flex">
                            <input type="hidden" name="id" value="<%= product.getId() %>">
                            <input
                                    class="quantity form-control ml-2"
                                    type="number"
                                    name="quantity"
                                    value="1"
                                    min="1"
                                    max="<%= product.getStockQuantity() %>"
                                    oninput="checkMaxValue(this, <%= product.getStockQuantity() %>)">
                            <button type="submit" class="btn btn-primary ml-2">Sepete Ekle</button>
                        </form>
                    </div>
                    <% }%>
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
<script>
    function checkMaxValue(input, max) {
        if (parseInt(input.value) > max) {
            input.value = max;
            alert("You cannot add more than the available stock.");
        }
    }
</script>
