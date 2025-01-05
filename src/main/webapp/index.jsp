<%@ page import="com.example.inventorymanagementsystem.dto.response.user.UserResponseDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    UserResponseDto retailer = (UserResponseDto) request.getSession().getAttribute("retailer");
    UserResponseDto supplier = (UserResponseDto) request.getSession().getAttribute("supplier");
    List<ProductResponseDto> products = (List<ProductResponseDto>) request.getSession().getAttribute("products");
    String error = (String) request.getAttribute("error");
    String baseImage= "https://media.licdn.com/dms/image/v2/C560BAQE9U_RVSFeJow/company-logo_200_200/company-logo_200_200/0/1630628233729?e=1744243200&v=beta&t=3Im6F2zYieT_m7_oSMx3gXPozmdkOq2HBMp8_Cw1vIs";
%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="includes/head.jsp" %>
    <title>ITG - Management System</title>
    <style>
        .search-bar {
            margin-bottom: 20px;
        }
        .product-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
<div class="container">
    <div class="card-header my-3">All Products</div>

    <div class="search-bar d-flex justify-content-between">
        <input type="text" id="searchInput" class="form-control w-50" placeholder="Search products by name..." onkeyup="filterProducts()">
        <select id="sortOptions" class="form-select w-25" onchange="sortProducts()">
            <option value="">Sort by</option>
            <option value="priceLowHigh">Price: Low to High</option>
            <option value="priceHighLow">Price: High to Low</option>
            <option value="nameAZ">Name: A to Z</option>
            <option value="nameZA">Name: Z to A</option>
        </select>
    </div>

    <div class="row" id="productContainer">
        <%
            if (products != null && !products.isEmpty()) {
                for (ProductResponseDto product : products) {
        %>
        <div class="col-md-3 product-card" data-name="<%= product.getName().toLowerCase() %>" data-price="<%= product.getPrice() %>">
            <div class="card w-100 mb-3" style="width: 18rem;">
                <%
                    String imageUrl = (product.getImageUrlList() != null && !product.getImageUrlList().isEmpty())
                            ? product.getImageUrlList().get(0)
                            : baseImage;
                %>
                <img class="card-img-top" src="<%= imageUrl %>" alt="<%= product.getName() %>">
                <div class="card-body">
                    <h5 class="card-title">
                        <a href="<%= request.getContextPath() %>/public-product-get?id=<%= product.getId() %>" class="text-decoration-none">
                            <%= product.getName() %>
                        </a>
                    </h5>
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
                                    type="hidden"
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

<script>
    function filterProducts() {
        const searchInput = document.getElementById("searchInput").value.toLowerCase();
        const productCards = document.querySelectorAll(".product-card");

        productCards.forEach(card => {
            const productName = card.getAttribute("data-name");
            if (productName.includes(searchInput)) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    }

    function sortProducts() {
        const sortOption = document.getElementById("sortOptions").value;
        const productContainer = document.getElementById("productContainer");
        const productCards = Array.from(document.querySelectorAll(".product-card"));

        productCards.sort((a, b) => {
            const priceA = parseFloat(a.getAttribute("data-price"));
            const priceB = parseFloat(b.getAttribute("data-price"));
            const nameA = a.getAttribute("data-name");
            const nameB = b.getAttribute("data-name");

            switch (sortOption) {
                case "priceLowHigh":
                    return priceA - priceB;
                case "priceHighLow":
                    return priceB - priceA;
                case "nameAZ":
                    return nameA.localeCompare(nameB);
                case "nameZA":
                    return nameB.localeCompare(nameA);
                default:
                    return 0;
            }
        });

        productContainer.innerHTML = "";
        productCards.forEach(card => productContainer.appendChild(card));
    }

    function checkMaxValue(input, max) {
        if (parseInt(input.value) > max) {
            input.value = max;
            alert("You cannot add more than the available stock.");
        }
    }
</script>
</body>
</html>
