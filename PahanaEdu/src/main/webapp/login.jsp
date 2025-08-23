<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 7/12/2025
  Time: 8:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%-- Created by IntelliJ IDEA. User: abhit Date: 7/12/2025 Time: 8:25 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check if user is already logged in or not
    HttpSession userSession = request.getSession(false);
    if (userSession != null && userSession.getAttribute("username") != null) {
        String userType = (String) userSession.getAttribute("userType");
        if ("admin".equals(userType)) {
            response.sendRedirect("Admin/AdminHome.jsp");
        } else {
            response.sendRedirect("Cashier/CashierHome.jsp");
        }
        return;
    }
%>
<html>
<head>
    <title>Login | Pahana Edu</title>
</head>
<style>

    body {
        margin: 0;
        font-family: 'Segoe UI', Arial, sans-serif;
        background: url('images/website-login-page-template-design_1017-30785.avif') no-repeat center center fixed;
        background-size: cover;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }


    .overlay {
        background: rgba(255, 255, 255, 0.85);
        padding: 40px;
        border-radius: 15px;
        box-shadow: 0 6px 30px rgba(0, 0, 0, 0.25);
        width: 380px;
    }

    .login-title {
        font-size: 26px;
        font-weight: 700;
        margin-bottom: 25px;
        text-align: center;
        color: #333;
    }

    .login-form .form-group {
        margin-bottom: 18px;
    }

    .login-form input {
        width: 100%;
        padding: 14px;
        border-radius: 8px;
        border: 1px solid #ccc;
        outline: none;
        font-size: 15px;
        transition: border 0.3s ease;
    }

    .login-form input:focus {
        border-color: #4a6cf7;
    }

    .btn {
        width: 100%;
        padding: 14px;
        border: none;
        border-radius: 8px;
        font-size: 16px;
        font-weight: 600;
        background: linear-gradient(90deg, #4a6cf7, #357ae8);
        color: #fff;
        cursor: pointer;
        transition: background 0.3s ease;
    }

    .btn:hover {
        background: linear-gradient(90deg, #3758d4, #2c62d0);
    }

    .form-options {
        margin: 10px 0;
        font-size: 14px;
        color: #555;
    }

    .error-msg {
        margin-top: 15px;
        font-size: 14px;
        color: #ff4d4d;
        text-align: center;
    }

    .brand {
        margin-top: 15px;
        text-align: center;
        font-size: 22px;
        font-weight: bold;
        color: #4a6cf7;
    }

    .tagline {
        text-align: center;
        font-size: 14px;
        color: #555;
        margin-bottom: 15px;
    }
</style>
<body>
<div class="overlay">
    <h2 class="login-title">Login</h2>
    <form action="login" method="post" class="login-form">
        <div class="form-group">
            <input type="text" id="username" name="username" placeholder="Username" autocomplete="off" required />
        </div>
        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="Password" autocomplete="off" required />
        </div>
        <button type="submit" name="login" class="btn">Log in</button>
    </form>

    <%
        String status = (String) request.getAttribute("status");
        if ("failed".equals(status)) {
    %>
    <p class="error-msg">⚠ Invalid username or password!</p>
    <% } else if ("error".equals(status)) { %>
    <p class="error-msg">⚠ An error occurred: <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "Please try again." %></p>
    <% } %>

    <h1 class="brand">Pahana Edu</h1>
    <p class="tagline">Point Of Sale Management System</p>
    <p class="tagline">© 2025 Pahana Edu. All Rights Reserved.</p>
</div>
</body>
</html>
