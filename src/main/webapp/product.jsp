<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    ProductResponseDto product = (ProductResponseDto) request.getAttribute("product");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="includes/head.jsp" %>
    <title>Product Details</title>
    <style>
        .product-card {
            display: flex;
            flex-wrap: wrap;
            max-width: 800px;
            margin: auto;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .product-slider {
            flex: 1 1 50%;
            max-width: 50%;
        }

        .product-details {
            flex: 1 1 50%;
            max-width: 50%;
            padding: 20px;
            text-align: left;
        }

        .product-details h2 {
            margin-bottom: 15px;
        }

        .product-details p {
            margin-bottom: 10px;
        }

        .product-details form {
            margin-top: 20px;
        }

        .carousel img {
            max-height: 300px;
            object-fit: cover;
            width: 100%;
        }

        .carousel-control-next,
        .carousel-control-prev /*, .carousel-indicators */ {
            filter: invert(100%);
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container my-5">
    <div class="product-card">
        <div class="product-slider">
            <div id="productCarousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <% for (int i = 0; i < product.getImageUrlList().size(); i++) { %>
                    <div class="carousel-item <%= i == 0 ? "active" : "" %>">
                        <img src="<%= product.getImageUrlList().get(i) %>" class="d-block w-100" alt="Product Image">
                    </div>
                    <% } %>
                </div>
                <a class="carousel-control-prev" href="#productCarousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#productCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <div class="product-details">
            <h2><%= product.getName() %></h2>
            <p>Stock: <%= product.getStockQuantity() %></p>
            <p>Price: <del>$<%= String.format("%.2f", product.getPrice()) %></del> <strong>$<%= String.format("%.2f", (product.getPrice()* (1-product.getDiscount()))) %></strong></p>
            <form action="private/add-to-cart" method="POST" >
                <input type="hidden" name="id" value="<%= product.getId() %>">
                <input
                        type="number"
                        name="quantity"
                        value="1"
                        min="1"
                        max="<%= product.getStockQuantity() %>"
                        oninput="checkMaxValue(this, <%= product.getStockQuantity() %>)">
                <button type="submit" class="btn btn-primary ml-2">Add to Cart</button>
            </form>
            <p><strong>Description:</strong> <%= product.getDescription() != null ? product.getDescription() : "No description available." %></p>
        </div>
    </div>
</div>

<%@include file="includes/footer.jsp" %>
<script>
    function checkMaxValue(input, max) {
        if (parseInt(input.value) > max) {
            input.value = max;
        }
    }
</script>
</body>
</html>
