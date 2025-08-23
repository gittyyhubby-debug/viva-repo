<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer Registration</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-950 text-white font-sans min-h-screen flex items-center justify-center px-4">

<div class="bg-gray-800 rounded-lg shadow-lg p-8 w-full max-w-md">
  <div class="flex justify-between items-center mb-6">
    <a href="billing.jsp"
       class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded shadow">
      📃 Cashier
    </a>
  </div>
  <h2 class="text-2xl font-bold text-center mb-6">👤 Register Customer</h2>

  <%
    String msg = request.getParameter("msg");
    String error = request.getParameter("error");
    if ("success".equals(msg)) {
  %>
  <div class="bg-green-600 text-white text-sm font-medium p-3 mb-4 rounded text-center">
    ✅ Customer registered successfully!
  </div>
  <% } else if (error != null) { %>
  <div class="bg-red-600 text-white text-sm font-medium p-3 mb-4 rounded text-center">
    ❌ <%= error %>
  </div>
  <% } %>

  <form method="post" action="register-customer" class="space-y-4">
    <input type="text" name="nic" placeholder="NIC Number" required
           class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring focus:ring-indigo-500 text-white">
    <input type="text" name="name" placeholder="Full Name" required
           class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring focus:ring-indigo-500 text-white">
    <input type="email" name="email" placeholder="Email Address" required
           class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring focus:ring-indigo-500 text-white">
    <input type="text" name="phone" placeholder="Phone Number" required
           class="w-full px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring focus:ring-indigo-500 text-white">

    <button type="submit"
            class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 rounded shadow">
      ➕ Register Customer
    </button>
  </form>
</div>
</body>
</html>
