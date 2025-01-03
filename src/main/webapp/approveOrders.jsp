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
    <title>Approve Orders</title>
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
            <th scope="col">Total Price</th>
            <th scope="col">Confirm</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <% if (billItems != null && !billItems.isEmpty()) {
            for (BillItemResponseDto billItem : billItems) {
                if (billItem.getConfirm() != BillItemStatusEnum.UNCERTAIN) {
                    continue;
                }
        %>
        <tr>
            <input type="hidden" name="id" value="<%= billItem.getId() %>">

            <td><input type="text" class="form-control" name="name" value="<%= billItem.getProductName() %>" readonly></td>
            <td><input type="number" class="form-control" name="stockQuantity" value="<%= billItem.getQuantity() %>" readonly></td>
            <td><input type="text" class="form-control" name="price" value="<%= String.format("%.2f", billItem.getPrice()) %>" readonly></td>

            <td>
                <form action="confirm-bill-item" method="POST">
                    <input type="hidden" name="id" value="<%= billItem.getId() %>">
                    <input type="hidden" name="success" value="true">
                    <button type="submit" class="btn btn-success">Confirm</button>
                </form>
            </td>
            <td>
                <form action="confirm-bill-item" method="POST">
                    <input type="hidden" name="id" value="<%= billItem.getId() %>">
                    <input type="hidden" name="success" value="false">
                    <button type="submit" class="btn btn-danger">Remove</button>
                </form>
            </td>
        </tr>
        <% } } %>
        </tbody>

    </table>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>


