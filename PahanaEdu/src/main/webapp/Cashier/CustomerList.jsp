<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 8/4/2025
  Time: 11:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.persistence.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer List</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    #last-updated { font-style: italic; color: #aaa; }
  </style>
  <script>
    function updateTable() {
      const form = document.getElementById('search-form');
      const formData = new FormData(form);
      const params = new URLSearchParams(formData).toString();
      fetch('${pageContext.request.contextPath}/customers?ajax=true&' + params)
              .then(response => response.text())
              .then(html => {
                document.querySelector('#customer-tbody').innerHTML = html;
                document.getElementById('last-updated').textContent =
                        'Last updated: ' + new Date().toLocaleString();
              })
              .catch(error => console.error('Error refreshing data:', error));
    }
    function applySearch() {
      updateTable();
    }
    function clearSearch() {
      document.getElementById('search-form').reset();
      updateTable();
    }
    // Optional: Periodic refresh every 30 seconds
    setInterval(updateTable, 30000);
  </script>
</head>
<body class="bg-gray-950 text-white min-h-screen font-sans p-6">

<div class="max-w-5xl mx-auto bg-gray-800 rounded-lg shadow-lg p-6">
  <!-- Header -->
  <div class="flex justify-between items-center mb-6">
    <a href="billing.jsp"
       class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded shadow">
      📃 Cashier
    </a>
    <h2 class="text-2xl font-bold">📋 Registered Customers</h2>
    <a href="customer-register.jsp"
       class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded shadow">
      ➕ Add Customer
    </a>
  </div>

  <!-- Search Form -->
  <form id="search-form" class="mb-6 bg-gray-900 p-4 rounded shadow">
    <div class="flex items-center space-x-4">
      <div class="flex-1">
        <label class="block text-sm font-medium text-gray-300">Search</label>
        <input type="text" name="search" class="mt-1 block w-full border-gray-600 rounded-md shadow-sm bg-gray-800 text-white p-2" placeholder="Search by name, email, or phone">
      </div>
      <div class="flex space-x-2 mt-6">
        <button type="button" onclick="applySearch()" class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded">Search</button>
        <button type="button" onclick="clearSearch()" class="bg-gray-600 hover:bg-gray-700 text-white px-4 py-2 rounded">Clear</button>
      </div>
    </div>
  </form>

  <div id="last-updated" class="text-right mb-4">Last updated: <%= new java.util.Date().toString() %></div>

  <%
    List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
    if (customers != null && !customers.isEmpty()) {
  %>

  <!-- Customer Details Table -->
  <div class="overflow-x-auto">
    <table class="w-full table-auto text-sm text-left border border-gray-700">
      <thead class="bg-gray-700 text-xs uppercase">
      <tr>
        <th class="px-4 py-3 border border-gray-700">NIC</th>
        <th class="px-4 py-3 border border-gray-700">Name</th>
        <th class="px-4 py-3 border border-gray-700">Email</th>
        <th class="px-4 py-3 border border-gray-700">Phone</th>
        <th class="px-4 py-3 border border-gray-700">Actions</th>
      </tr>
      </thead>
      <tbody id="customer-tbody">
      <% for (Customer customer : customers) { %>
      <tr class="border-t border-gray-700 hover:bg-gray-700/50">
        <td class="px-4 py-2 border border-gray-700"><%= customer.getNic() %></td>
        <td class="px-4 py-2 border border-gray-700"><%= customer.getName() %></td>
        <td class="px-4 py-2 border border-gray-700"><%= customer.getEmail() %></td>
        <td class="px-4 py-2 border border-gray-700"><%= customer.getPhone() %></td>
        <td class="px-4 py-2 border border-gray-700">
          <a href="edit-customer?nic=<%= customer.getNic() %>" class="text-yellow-400 hover:underline">Edit</a> |
          <a href="delete-customer?nic=<%= customer.getNic() %>" class="text-red-500 hover:underline"
             onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>

  <% } else { %>
  <!-- No Data Message -->
  <div class="text-center py-8 text-gray-400 text-lg">
    🚫 No customers found.
  </div>
  <% } %>
</div>

<footer class="text-center text-gray-500 text-sm py-6 border-t border-gray-800">
  © 2025 Pahana Edu. All Rights Reserved.
</footer>

</body>
</html>