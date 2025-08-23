<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.persistence.dao.CustomerDAO" %>
<%@ page import="com.example.persistence.dao.AddProductDAO" %>
<%@ page import="com.example.persistence.dao.SaleItemDAO" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - POS System</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Inter', sans-serif;
        }
        .sidebar {
            transition: transform 0.3s ease-in-out;
        }
        @media (max-width: 768px) {
            .sidebar {
                transform: translateX(-100%);
            }
            .sidebar-open {
                transform: translateX(0);
            }
        }
        .gradient-bg {
            background: linear-gradient(135deg, #3b82f6 0%, #1e3a8a 100%);
        }
        .news-card:hover {
            transform: translateY(-4px);
            transition: transform 0.3s ease;
        }
    </style>
</head>
<body class="bg-gray-50">
<div class="flex min-h-screen">
    <!-- Sidebar -->
    <aside class="sidebar fixed inset-y-0 left-0 w-64 bg-white shadow-lg z-20 md:static md:translate-x-0">
        <jsp:include page="NavigationBar/NavBar.jsp" />
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <!-- Mobile Menu Toggle -->
        <div class="md:hidden flex justify-between items-center mb-6">
            <button id="toggleSidebar" class="text-gray-600 focus:outline-none">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16m-7 6h7"></path>
                </svg>
            </button>
            <h1 class="text-xl font-bold text-gray-800">Admin Dashboard</h1>
        </div>

        <!-- Header -->
        <div class="flex justify-between items-center mb-8">
            <h1 class="text-3xl font-bold text-gray-800 hidden md:block">Admin Dashboard</h1>
            <div class="flex items-center gap-4">
                <%
                    String loggedInUser = (String) session.getAttribute("username");
                %>
                <span class="text-sm font-medium text-gray-600"><%= loggedInUser != null ? loggedInUser : "Admin" %></span>
                <div class="w-10 h-10 rounded-full bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-semibold">
                    <%= loggedInUser != null ? loggedInUser.charAt(0) : "A" %>
                </div>
            </div>
        </div>

        <%
            int totalCustomers = CustomerDAO.getTotalCustomers();
            int totalProducts = AddProductDAO.getTotalProducts();
            double totalSales = SaleItemDAO.getTotalSalesAmount();
            String formattedSales = "LKR " + String.format("%,.2f", totalSales);
        %>

        <!-- Summary Cards -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
            <div class="bg-white p-6 rounded-xl shadow-lg hover:shadow-xl transition-shadow gradient-bg text-white">
                <h2 class="text-sm font-medium mb-2">Total Customers</h2>
                <p class="text-3xl font-bold"><%= totalCustomers %></p>
                <div class="mt-4 text-xs opacity-75">Updated in real-time</div>
            </div>
            <div class="bg-white p-6 rounded-xl shadow-lg hover:shadow-xl transition-shadow gradient-bg text-white">
                <h2 class="text-sm font-medium mb-2">Total Products</h2>
                <p class="text-3xl font-bold"><%= totalProducts %></p>
                <div class="mt-4 text-xs opacity-75">Across all categories</div>
            </div>
            <div class="bg-white p-6 rounded-xl shadow-lg hover:shadow-xl transition-shadow gradient-bg text-white">
                <h2 class="text-sm font-medium mb-2">Total Sales</h2>
                <p class="text-3xl font-bold"><%= formattedSales %></p>
                <div class="mt-4 text-xs opacity-75">Cumulative revenue</div>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="flex flex-wrap gap-4 mb-8">
            <a href="${pageContext.request.contextPath}/Admin/Products/add-product.jsp">
                <button class="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors font-medium">
                    Add Product
                </button>
            </a>
            <a href="${pageContext.request.contextPath}/view-products">
                <button class="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors font-medium">
                    View Products
                </button>
            </a>
            <a href="${pageContext.request.contextPath}/SalesReport">
                <button class="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors font-medium">
                    Sales Report
                </button>
            </a>
        </div>

        <!-- Business News Feed -->
        <div class="bg-white p-6 rounded-xl shadow-lg">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">Global Business News</h2>
            <div class="grid gap-4">
                <!-- Sample news items; replace with dynamic data from an API like NewsAPI -->
                <div class="news-card bg-gray-50 p-4 rounded-lg hover:shadow-md transition-shadow">
                    <h3 class="text-lg font-medium text-gray-800">Tech Giants Report Record Q2 Earnings</h3>
                    <p class="text-sm text-gray-600">Major tech companies saw a surge in profits driven by AI and cloud computing advancements.</p>
                    <div class="mt-2 text-xs text-gray-500">Source: Bloomberg | 2025-08-15</div>
                </div>
                <div class="news-card bg-gray-50 p-4 rounded-lg hover:shadow-md transition-shadow">
                    <h3 class="text-lg font-medium text-gray-800">Global Supply Chain Disruptions Ease</h3>
                    <p class="text-sm text-gray-600">Improved logistics and trade agreements are stabilizing international markets.</p>
                    <div class="mt-2 text-xs text-gray-500">Source: Reuters | 2025-08-14</div>
                </div>
                <div class="news-card bg-gray-50 p-4 rounded-lg hover:shadow-md transition-shadow">
                    <h3 class="text-lg font-medium text-gray-800">Retail Sector Embraces Sustainable Practices</h3>
                    <p class="text-sm text-gray-600">Leading retailers announce eco-friendly initiatives to reduce carbon footprints.</p>
                    <div class="mt-2 text-xs text-gray-500">Source: Financial Times | 2025-08-13</div>
                </div>
            </div>
            <div class="mt-4 text-right">
                <a href="https://newsapi.org" target="_blank" class="text-blue-600 text-sm font-medium hover:underline">View More News</a>
            </div>
        </div>
    </main>
</div>

<script>
    // Mobile sidebar toggle
    document.getElementById('toggleSidebar')?.addEventListener('click', () => {
        document.querySelector('.sidebar').classList.toggle('sidebar-open');
    });
</script>
</body>
</html>