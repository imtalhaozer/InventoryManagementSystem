<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    List<CartItemResponseDto> cartProduct= (List<CartItemResponseDto>) request.getAttribute("cartItems");
    double sum = request.getAttribute("sum") != null ? (double) request.getAttribute("sum") : 0;
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>Cart Pages</title>
    <style type="text/css">

        .table tbody td{
            vertical-align: middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
        .form-control.text-center {
            width: 50px;
            font-size: 14px;
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="d-flex py-3">
        <h3> Total Price: $<%= String.format("%.2f",sum)  %> </h3>
        <a class="mx-3 btn btn-primary" href="private-checkout">Check Out</a>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Stock</th>
            <th scope="col">Price</th>
            <th scope="col">Unit</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <% if(cartProduct != null && !cartProduct.isEmpty()) {
            for (CartItemResponseDto cart : cartProduct) { %>
        <tr>
            <td><%=cart.getProduct().getName()%></td>
            <td><%=cart.getProduct().getStockQuantity()%></td>
            <td><%= String.format("%.2f", cart.getProduct().getPrice() * (1 - cart.getProduct().getDiscount())) %></td>
            <td>
                <form action="" method="post" class="from-inline">
                    <input type="hidden" name="id" value="<%=cart.getId()%>" class="form-input">
                    <div class="form-group d-flex w-10 h-5">
                        <% if (cart.getQuantity() < cart.getProduct().getStockQuantity()) { %>
                        <a class="btn btn-sm btn-incre" href="<%= request.getContextPath() %>/private/quantity?action=inc&id=<%= cart.getProduct().getId()%>">
                            <i class="fas fa-plus-square"></i>
                        </a>
                        <% } %>
                        <input type="text" name="quantity" class="form-control text-center" value="<%= cart.getQuantity() %>" readonly>
                        <% if (cart.getQuantity() > 0) { %>
                        <a class="btn btn-sm btn-decre" href="<%= request.getContextPath() %>/private/quantity?action=dec&id=<%= cart.getProduct().getId()%>">
                            <i class="fas fa-minus-square"></i>
                        </a>
                        <% } %>
                    </div>
                </form>
            </td>

            <td><a href="remove-from-cart?id=<%= cart.getProduct().getId()%>" class="btn btn-danger">Remove</a></td>
        </tr>
        <% } }%>
        </tbody>
    </table>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>