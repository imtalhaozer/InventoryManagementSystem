<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">Inventory Management System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">
                    <i class="fas fa-home"></i> Home <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="cart.jsp">
                    <i class="fas fa-shopping-cart"></i> Cart
                </a>
            </li>
            <% if (supplier != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="addProduct.jsp">
                    <i class="fa-solid fa-cube"></i> Add Product
                </a>
            </li>
            <% }%>
            <% if (retailer != null || supplier != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fas fa-box"></i> Orders
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="log-out">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
            <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="login.jsp">
                    <i class="fas fa-sign-in-alt"></i> Login
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register.jsp">
                    <i class="fa-solid fa-file-signature"></i> Sign Up
                </a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>
