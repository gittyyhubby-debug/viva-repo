<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 8/7/2025
--%>
<%@ page import="com.example.persistence.model.Customer" %>
<%
  Customer customer = (Customer) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Customer</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    .emoji-font {
      font-family: "Segoe UI Emoji", "Apple Color Emoji", "Noto Color Emoji", sans-serif;
    }
  </style>
</head>
<body class="bg-gray-950 text-white min-h-screen p-6 font-sans">

<!-- Edit Form Card -->
<div class="w-full max-w-3xl mx-auto bg-gray-800 rounded-lg shadow-lg p-8 pt-20 relative">

  <!-- Header inside the card -->
  <div class="absolute top-4 left-0 w-full flex justify-between items-center px-8 emoji-font z-10">
    <!-- Left button -->
    <a href="billing.jsp" class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded shadow inline-flex items-center space-x-2 transition-colors duration-200">
      <img src="https://twemoji.maxcdn.com/v/latest/svg/1f4cb.svg" alt="📃" class="w-5 h-5">
      <span>Cashier</span>
    </a>

    <!-- Centered title -->
    <h2 class="text-2xl font-bold text-white absolute left-1/2 transform -translate-x-1/2">
      <span class="inline-flex items-center space-x-2">
        <img src="https://twemoji.maxcdn.com/v/latest/svg/1f4cb.svg" alt="📋" class="w-6 h-6">
        <span>Edit Customer</span>
      </span>
    </h2>

    <!-- Right button -->
    <a href="customers" class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded shadow inline-flex items-center space-x-2 transition-colors duration-200">
      <img src="https://twemoji.maxcdn.com/v/latest/svg/1f465.svg" alt="👥" class="w-5 h-5">
      <span>View Customers</span>
    </a>
  </div>

  <!-- Form -->
  <form action="update-customer" method="post" class="space-y-6">
    <input type="hidden" name="nic" value="<%= customer.getNic() %>">
    <div>
      <label for="name" class="block text-sm font-medium text-gray-300">Name</label>
      <input id="name" name="name" value="<%= customer.getName() %>" placeholder="Enter name" required
             class="mt-1 block w-full p-3 rounded-md bg-gray-700 text-white border border-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
    </div>
    <div>
      <label for="email" class="block text-sm font-medium text-gray-300">Email</label>
      <input id="email" name="email" value="<%= customer.getEmail() %>" placeholder="Enter email" required
             class="mt-1 block w-full p-3 rounded-md bg-gray-700 text-white border border-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
    </div>
    <div>
      <label for="phone" class="block text-sm font-medium text-gray-300">Phone</label>
      <input id="phone" name="phone" value="<%= customer.getPhone() %>" placeholder="Enter phone number" required
             class="mt-1 block w-full p-3 rounded-md bg-gray-700 text-white border border-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500">
    </div>
    <div class="flex justify-end space-x-4">
      <a href="customers" class="bg-gray-600 hover:bg-gray-700 text-white font-semibold px-4 py-2 rounded shadow transition-colors duration-200">Cancel</a>
      <button type="submit" class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-4 py-2 rounded shadow transition-colors duration-200">Update Customer</button>
    </div>
  </form>
</div>

<footer class="text-center text-gray-500 text-sm py-6 border-t border-gray-800 mt-6">
  © 2025 Pahana Edu. All Rights Reserved.
</footer>

</body>
</html>