<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 7/26/2025
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.persistence.model.CashierProducts" %>

<%
  CashierProducts product = (CashierProducts) request.getAttribute("product");
  boolean isEdit = (product != null);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><%= isEdit ? "Edit Product" : "Add Product" %></title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50 font-sans">

<div class="md:hidden p-4">
  <button id="menu-toggle" class="text-2xl text-gray-700 focus:outline-none">
    <i class='bx bx-menu'></i>
  </button>
</div>

<div class="flex min-h-screen">
  <jsp:include page="../NavigationBar/NavBar.jsp" />

  <main class="flex-1 ml-0 p-6">
    <div class="max-w-md mx-auto bg-white rounded-lg shadow-md p-8">
      <h2 class="text-2xl font-bold mb-6 text-center"><%= isEdit ? "Edit Product" : "Add New Product" %></h2>

      <form method="post" action="<%= isEdit ? request.getContextPath() + "/edit-product" : request.getContextPath() + "/add-product" %>" class="space-y-4">
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= product.getId() %>" />
        <% } %>

        <input type="text" name="name" placeholder="Product Name" required
               value="<%= isEdit ? product.getName() : "" %>"
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <textarea name="description" placeholder="Description" required rows="5"
                  class="w-full px-4 py-2 border rounded resize-y focus:outline-none focus:ring-2 focus:ring-blue-500"><%= isEdit ? product.getDescription() : "" %></textarea>

        <input type="text" name="category" placeholder="Category" required
               value="<%= isEdit ? product.getCategory() : "" %>"
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="number" name="quantity" placeholder="Quantity" min="0" required
               value="<%= isEdit ? product.getQuantity() : "" %>"
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <input type="number" name="price" placeholder="Price" step="0.01" min="0" required
               value="<%= isEdit ? product.getPrice() : "" %>"
               class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500" />

        <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition">
          <%= isEdit ? "Update Product" : "Add Product" %>
        </button>

        <c:if test="${status == 'success'}">
          <p class="text-green-600 text-sm text-center mt-2">
            <%= isEdit ? "Product updated successfully!" : "Product added successfully!" %>
          </p>
        </c:if>
        <c:if test="${status == 'failed'}">
          <p class="text-red-600 text-sm text-center mt-2">
            <%= isEdit ? "Failed to update product. Try again." : "Failed to add product. Try again." %>
          </p>
        </c:if>
      </form>

      <div class="mt-6 text-center">
        <a href="${pageContext.request.contextPath}/view-products" class="text-blue-500 hover:underline">← Back to Product List</a>
      </div>
    </div>
  </main>
</div>
</body>
</html>
