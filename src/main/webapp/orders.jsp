<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.BillItemResponseDto" %>
<%@ page import="com.example.inventorymanagementsystem.models.BillItemStatusEnum" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<BillItemResponseDto> billItems = (List<BillItemResponseDto>) request.getAttribute("billItems");
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>Orders</title>
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
            <th scope="col">Product Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Total Price</th>
            <th scope="col">Status</th>
            <th scope="col">Invoice</th>
        </tr>
        </thead>
        <tbody>
        <% if (billItems != null && !billItems.isEmpty()) {
            for (BillItemResponseDto billItem : billItems) {
                if (billItem.getConfirm() == BillItemStatusEnum.NOT_CONFIRMED) {
                    continue;
                }
        %>
        <tr>
            <td><input type="text" class="form-control" name="productName" value="<%= billItem.getProductName() %>" readonly></td>
            <td><input type="number" class="form-control" name="quantity" value="<%= billItem.getQuantity() %>" readonly></td>
            <td><input type="text" class="form-control" name="price" value="<%= String.format("%.2f", billItem.getPrice()) %>" readonly></td>
            <td><input type="text" class="form-control" name="totalPrice" value="<%= String.format("%.2f", billItem.getPrice() * billItem.getQuantity()) %>" readonly></td>
            <td>
                <% if (billItem.getConfirm() == BillItemStatusEnum.CONFIRMED) { %>
                <span>Your order has been confirmed</span>
                <% } else if (billItem.getConfirm() == BillItemStatusEnum.UNCERTAIN) { %>
                <span>Pending</span>
                <% } %>
            </td>
            <td>
                <a href="#" class="btn btn-info">Invoice</a>
            </td>
        </tr>
        <% } } %>
        </tbody>

    </table>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>
