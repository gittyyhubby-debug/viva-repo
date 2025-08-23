<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 7/19/2025
  Time: 3:07 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PahanaEdu Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="img/logo.png">
</head>
<body class="bg-gray-100 font-sans">

<!-- Mobile Header -->
<div class="md:hidden p-4 flex justify-between items-center bg-white shadow">
    <div class="font-bold text-lg text-green-600">Pahana Edu</div>
    <button id="menu-toggle" class="text-2xl text-gray-700 focus:outline-none">
        <i class='bx bx-menu'></i>
    </button>
</div>

<!-- Sidebar -->
<aside id="sidebar"
       class="w-64 bg-gray-900 text-gray-200 fixed top-0 left-0 bottom-0
              transform -translate-x-full md:translate-x-0 transition-transform duration-300
              z-50 flex flex-col">

    <!-- Logo -->
    <div class="flex items-center space-x-2 px-6 py-6 border-b border-gray-700">
        <%--        <img src="logo.png" alt="Logo" class="w-10 h-10 rounded-full">--%>
        <span class="text-xl font-bold text-green-400">Pahana Edu</span>
    </div>

    <!-- Navigation -->
    <nav class="flex-1 px-4 py-6 space-y-2 overflow-y-auto">
        <a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp"
           class="flex items-center px-4 py-2 rounded-lg hover:bg-gray-800 transition-colors
                  font-medium text-green-400">
            <i class='bx bx-home mr-3 text-xl'></i> Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/Admin/Products/add-product.jsp"
           class="flex items-center px-4 py-2 rounded-lg hover:bg-gray-800 transition-colors">
            <i class='bx bx-plus-circle mr-3 text-xl'></i> Add Products
        </a>
        <a href="${pageContext.request.contextPath}/view-products"
           class="flex items-center px-4 py-2 rounded-lg hover:bg-gray-800 transition-colors">
            <i class='bx bx-box mr-3 text-xl'></i> View Products
        </a>
        <a href="${pageContext.request.contextPath}/SalesReport"
           class="flex items-center px-4 py-2 rounded-lg hover:bg-gray-800 transition-colors">
            <i class='bx bx-bar-chart mr-3 text-xl'></i> Sales
        </a>
        <a href="${pageContext.request.contextPath}/Admin/help.jsp"
           class="flex items-center px-4 py-2 rounded-lg hover:bg-gray-800 transition-colors">
            <i class='bx bx-help-circle mr-3 text-xl'></i> Help
        </a>
    </nav>

    <!-- Logout -->
    <div class="px-4 py-6 border-t border-gray-700">
        <a href="${pageContext.request.contextPath}/Admin/logout"
           class="flex items-center px-4 py-2 rounded-lg text-red-400 hover:bg-gray-800 transition-colors font-semibold">
            <i class='bx bx-log-out mr-3 text-xl'></i> Logout
        </a>
        <p class="text-center text-gray-500 text-xs mt-4">© 2025 Pahana Edu</p>
    </div>
</aside>

<!-- Toggle JS -->
<script>
    const toggle = document.getElementById('menu-toggle');
    const sidebar = document.getElementById('sidebar');

    toggle.addEventListener('click', () => {
        sidebar.classList.toggle('-translate-x-full');
    });
</script>

</body>
</html>
