<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.persistence.model.CashierProducts" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Management - Pahana Edu POS</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
  <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/logo.jpg">
  <style>
    body {
      font-family: 'Inter', sans-serif;
      background: linear-gradient(to bottom, #f3f4f6, #e5e7eb);
      margin: 0;
    }
    .container {
      display: flex;
      min-height: 100vh;
    }
    .sidebar {
      background: linear-gradient(to bottom, #ffffff, #f9fafb);
      box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
      width: 16rem;
      flex-shrink: 0;
    }
    .card {
      background: #ffffff;
      border: 1px solid #e5e7eb;
      border-radius: 12px;
      transition: transform 0.2s ease, box-shadow 0.2s ease;
    }
    .card:hover {
      transform: translateY(-4px);
      box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
    }
    .btn-primary {
      background: #10b981;
      color: #ffffff;
      padding: 8px 16px;
      border-radius: 8px;
      transition: background 0.2s ease;
    }
    .btn-primary:hover {
      background: #059669;
    }
    .btn-danger {
      background: #ef4444;
      color: #ffffff;
      padding: 8px 16px;
      border-radius: 8px;
      transition: background 0.2s ease;
    }
    .btn-danger:hover {
      background: #dc2626;
    }
    .low-stock-card {
      background: #fef2f2;
      border: 1px solid #fee2e2;
      border-radius: 12px;
    }
    .header {
      background: #ffffff;
      border-bottom: 1px solid #e5e7eb;
      padding: 16px 24px;
      margin-bottom: 24px;
    }
  </style>
</head>
<body>
<div class="container">
  <jsp:include page="../NavigationBar/NavBar.jsp" />

  <!-- Main Content -->
  <main class="flex-1 ml-0 md:ml-64 p-6">
    <!-- Header -->
    <div class="header flex justify-between items-center mb-6">
      <h1 class="text-3xl font-bold text-green-700">Book Inventory</h1>
      <a href="${pageContext.request.contextPath}/Admin/Products/add-product.jsp" class="btn-primary flex items-center gap-2">
        <i class='bx bx-plus'></i> Add New Book
      </a>
    </div>

    <!-- Mobile Menu Toggle -->
    <div class="md:hidden p-4">
      <button id="menu-toggle" class="text-2xl text-gray-700 focus:outline-none">
        <i class='bx bx-menu'></i>
      </button>
    </div>

    <!-- Low Stock Panel -->
    <%
      List<CashierProducts> productList = (List<CashierProducts>) request.getAttribute("productList");
      boolean hasLowStock = false;
      if (productList != null) {
        for (CashierProducts p : productList) {
          if (p.getQuantity() < 10) {
            hasLowStock = true;
            break;
          }
        }
      }
    %>
    <% if (hasLowStock) { %>
    <div class="mb-8 p-6 low-stock-card shadow-sm">
      <h2 class="text-lg font-semibold text-red-800 flex items-center gap-2 mb-4">
        <i class='bx bxs-error-circle text-red-600'></i> Low Stock Books
      </h2>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        <% for (CashierProducts p : productList) {
          if (p.getQuantity() < 10) { %>
        <div class="low-stock-card p-4 card">
          <div class="flex justify-between items-center mb-1">
            <h3 class="font-bold text-sm text-gray-700"><%= p.getName() %></h3>
            <span class="text-xs bg-red-100 text-red-800 px-2 py-0.5 rounded-full"><%= p.getCategory() %></span>
          </div>
          <p class="text-xs text-gray-500">ID: <%= p.getId() %></p>
          <p class="text-sm text-gray-600 mb-2">Qty:
            <span class="font-semibold text-red-600"><%= p.getQuantity() %></span>
          </p>

          <!-- Edit Button -->
          <div class="flex justify-end">
            <a href="${pageContext.request.contextPath}/edit-product?id=<%= p.getId() %>"
               class="btn-primary text-xs text-center px-3 py-1">
              Edit
            </a>
          </div>
        </div>

        <% } } %>
      </div>
    </div>
    <% } %>

    <!-- Product Grid -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
      <%
        if (productList != null && !productList.isEmpty()) {
          for (CashierProducts p : productList) {
      %>
      <div class="card p-6">
        <div class="flex justify-between items-center mb-2">
          <h2 class="text-lg font-semibold text-gray-800"><%= p.getName() %></h2>
          <span class="text-sm bg-green-100 text-green-700 px-2 py-1 rounded-full"><%= p.getCategory() %></span>
        </div>
        <p class="text-xs text-gray-500 mb-1">ID: <%= p.getId() %></p>
        <p class="text-gray-500 text-sm mb-2 line-clamp-2"><%= p.getDescription() %></p>
        <p class="text-gray-700 font-bold text-lg mb-2">Rs.<%= String.format("%.2f", p.getPrice()) %></p>
        <p class="text-sm text-gray-600 mb-4">Qty: <%= p.getQuantity() %></p>
        <div class="flex justify-between gap-2">
          <a href="${pageContext.request.contextPath}/edit-product?id=<%= p.getId() %>" class="btn-primary text-xs text-center flex-1">
            Edit
          </a>
          <a href="${pageContext.request.contextPath}/delete-product?id=<%= p.getId() %>"
             onclick="return confirm('Are you sure you want to delete this book?');"
             class="btn-danger text-xs text-center flex-1">
            Delete
          </a>
        </div>
      </div>
      <%
        }
      } else {
      %>
      <div class="col-span-full text-center text-gray-500 text-lg py-10">
        No books available in inventory.
      </div>
      <% } %>
    </div>

    <!-- Back Button -->
    <div class="mt-6">
      <a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp"
         class="text-blue-600 hover:underline flex items-center gap-1 text-sm">
        <i class='bx bx-arrow-back'></i> Back to Dashboard
      </a>
    </div>
  </main>
</div>

<!-- Sidebar toggle script -->
<script>
  const toggle = document.getElementById('menu-toggle');
  const sidebar = document.getElementById('sidebar');
  toggle.addEventListener('click', () => {
    sidebar.classList.toggle('hidden');
    sidebar.classList.toggle('translate-x-0');
    sidebar.classList.toggle('-translate-x-full');
  });
</script>
</body>
</html>