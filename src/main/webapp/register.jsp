<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    if (retailer != null || supplier != null) {
        response.sendRedirect("index.jsp");
    }

    String toastMessage = (String) request.getAttribute("toastMessage");
    String toastType = (String) request.getAttribute("toastType");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title>User Registration</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <% if (toastMessage != null) { %>
    <div aria-live="polite" aria-atomic="true" style="position: fixed; top: 3rem; right: 1rem; z-index: 1050;">
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="mr-auto"><%= "success".equals(toastType) ? "Successful" : "Something wrong" %></strong>
            </div>
            <div class="toast-body">
                <%= toastMessage %>
            </div>
        </div>
    </div>
    <% } %>
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Registration</div>
        <div class="card-body">
            <form id="register-form" method="post">
                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="name" class="form-control" placeholder="Enter your full name" required>
                </div>
                <div class="form-group">
                    <label>Email address</label>
                    <input type="email" name="email" class="form-control" placeholder="Enter your email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" placeholder="Enter a password" required>
                </div>
                <div class="form-group">
                    <label>Account Type</label>
                    <select name="role" id="account-type" class="form-control" required>
                        <option value="">Select account type</option>
                        <option value="retailer">Retailer</option>
                        <option value="supplier">Supplier</option>
                    </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>

<script>
    document.getElementById('register-form').addEventListener('submit', function (event) {
        const accountType = document.getElementById('account-type').value;

        if (accountType === "retailer") {
            this.action = "public-register-retailer";
        } else if (accountType === "supplier") {
            this.action = "public-register-supplier";
        } else {
            event.preventDefault();
            alert("Please select a valid account type!");
        }
    });

    document.addEventListener("DOMContentLoaded", function () {
        const toastEl = document.querySelector('.toast');
        if (toastEl) {
            $(toastEl).toast('show');
        }
    });
</script>
</body>
</html>
