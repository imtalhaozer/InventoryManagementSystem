<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<%= request.getContextPath() %>/public-product-get-all">Inventory Management System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%= request.getContextPath() %>/public-product-get-all">
                    <i class="fas fa-home"></i> Home <span class="sr-only">(current)</span>
                </a>
            </li>
            <% if (retailer != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/private-get-cart-items">
                    <i class="fas fa-shopping-cart"></i> Cart
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/get-orders">
                    <i class="fas fa-box"></i> Orders
                </a>
            </li>
            <% }%>
            <% if (supplier != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/products-supplier">
                    <i class="fa-solid fa-tag"></i> My Products
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="addProduct.jsp">
                    <i class="fa-solid fa-cube"></i> Add Product
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/get-order-approvals">
                    <i class="fa-solid fa-circle-check"></i> Approve Orders
                </a>
            </li>
            <% }%>
            <% if (retailer != null || supplier != null) { %>

            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/log-out">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/login.jsp">
                    <i class="fas fa-sign-in-alt"></i> Login
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/register.jsp">
                    <i class="fa-solid fa-file-signature"></i> Sign Up
                </a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
