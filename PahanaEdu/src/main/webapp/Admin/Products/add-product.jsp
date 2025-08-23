<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 7/19/2025
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Add Product</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50 font-sans">

<!-- Mobile Toggle Button -->
<div class="md:hidden p-4">
  <button id="menu-toggle" class="text-2xl text-gray-700 focus:outline-none">
    <i class='bx bx-menu'></i>
  </button>
</div>

<div class="flex min-h-screen">

  <jsp:include page="../NavigationBar/NavBar.jsp" />

  <!-- Main content -->
  <main class="flex-1 ml-0 md:ml-64 p-6">
    <div class="max-w-md mx-auto bg-white rounded-lg shadow-md p-8">
      <h2 class="text-2xl font-bold mb-6 text-center">Add New Product</h2>

      <!-- Status Messages using JSP Scriptlets -->
      <%
        String status = (String) request.getAttribute("status");
        if (status != null && !status.isEmpty()) {
      %>
      <% if ("success".equals(status)) { %>
      <div class="mb-4 p-3 bg-green-100 border border-green-400 text-green-700 rounded">
        <i class='bx bx-check-circle mr-2'></i>Product added successfully!
      </div>
      <% } else if ("failed".equals(status)) { %>
      <div class="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded">
        <i class='bx bx-error-circle mr-2'></i>Failed to add product. Please try again.
      </div>
      <% } else { %>
      <div class="mb-4 p-3 bg-yellow-100 border border-yellow-400 text-yellow-700 rounded">
        <i class='bx bx-info-circle mr-2'></i><%= status %>
      </div>
      <% } %>
      <% } %>

      <form action="${pageContext.request.contextPath}/addProduct" method="post" class="space-y-4">
        <input type="text" name="name" placeholder="Product Name" required
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="text" name="description" placeholder="Description" required
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="text" name="category" placeholder="Category" required
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="number" name="quantity" placeholder="Quantity" min="0" required
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="number" name="price" placeholder="Price" step="0.01" min="0" required
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition">
          Add Product
        </button>
      </form>

      <div class="mt-6 text-center">
        <%--        <a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="text-blue-500 hover:underline">← Back to Dashboard</a>--%>
      </div>
    </div>
  </main>
</div>

<script>
  // Auto-hide messages after 5 seconds
  document.addEventListener('DOMContentLoaded', function() {
    const messages = document.querySelectorAll('.bg-green-100, .bg-red-100, .bg-yellow-100');
    messages.forEach(function(message) {
      setTimeout(function() {
        message.style.transition = 'opacity 0.5s ease-out';
        message.style.opacity = '0';
        setTimeout(function() {
          message.remove();
        }, 500);
      }, 5000);
    });
  });
</script>

</body>
</html>