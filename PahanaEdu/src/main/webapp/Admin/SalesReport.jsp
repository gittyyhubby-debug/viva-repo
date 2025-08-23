<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 8/6/2025
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Bookshop Sales Report</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <style>
    #last-updated { font-style: italic; color: #666; }
  </style>
  <script>
    function updateTable() {
      const form = document.getElementById('filter-form');
      const formData = new FormData(form);
      const params = new URLSearchParams(formData).toString();
      fetch('${pageContext.request.contextPath}/SalesReport?ajax=true&' + params)
              .then(response => response.text())
              .then(html => {
                document.querySelector('#sales-tbody').innerHTML = html;
                document.getElementById('last-updated').textContent =
                        'Last updated: ' + new Date().toLocaleString();
              })
              .catch(error => console.error('Error refreshing data:', error));
    }
    function applyFilters() {
      updateTable();
    }
    function clearFilters() {
      document.getElementById('filter-form').reset();
      updateTable();
    }
    setInterval(updateTable, 30000);
  </script>
</head>
<body class="bg-gray-100 font-sans">
<div class="flex">
  <jsp:include page="NavigationBar/NavBar.jsp" />
  <div class="flex-1 ml-0 md:ml-64 p-6">
    <h1 class="text-2xl font-semibold text-gray-800 mb-4">Bookshop Sales Report</h1>
    <div id="last-updated" class="text-right mb-4">Last updated: <%= new java.util.Date().toString() %></div>

    <form id="filter-form" class="mb-6 bg-white p-4 rounded shadow">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">From Date</label>
          <input type="date" name="fromDate" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">To Date</label>
          <input type="date" name="toDate" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Customer Name</label>
          <input type="text" name="customer" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Search by name">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Min Amount</label>
          <input type="number" name="minAmount" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Min amount">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Max Amount</label>
          <input type="number" name="maxAmount" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Max amount">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Sort By</label>
          <select name="sortBy" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
            <option value="latest">Latest</option>
            <option value="highest">Highest Amount</option>
            <option value="lowest">Lowest Amount</option>
          </select>
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Top N Records</label>
          <input type="number" name="topN" class="mt-1 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Number of records">
        </div>
      </div>
      <div class="mt-4 flex space-x-4">
        <button type="button" onclick="applyFilters()" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Apply Filters</button>
        <button type="button" onclick="clearFilters()" class="bg-gray-500 text-white px-4 py-2 rounded hover:bg-gray-600">Clear Filters</button>
      </div>
    </form>

    <div class="overflow-x-auto">
      <table class="min-w-full bg-white border border-gray-300 rounded shadow">
        <thead class="bg-gray-100">
        <tr>
          <th class="px-4 py-2 border">ID</th>
          <th class="px-4 py-2 border">Customer Name</th>
          <th class="px-4 py-2 border">Customer Phone</th>
          <th class="px-4 py-2 border">Total Amount (Rs.)</th>
                    <th class="px-4 py-2 border">Discount (Rs.)</th>
                    <th class="px-4 py-2 border">Service Charge (Rs.)</th>
          <th class="px-4 py-2 border">Created At</th>
        </tr>
        </thead>
        <tbody id="sales-tbody">
        <c:forEach var="sale" items="${salesList}">
          <tr class="hover:bg-gray-50">
            <td class="px-4 py-2 border">${sale.id}</td>
            <td class="px-4 py-2 border">${sale.customerName}</td>
            <td class="px-4 py-2 border">${sale.customerPhone}</td>
            <td class="px-4 py-2 border">${sale.totalAmount}</td>
                          <td class="px-4 py-2 border">${sale.discount}</td>
                          <td class="px-4 py-2 border">${sale.serviceCharge}</td>
            <td class="px-4 py-2 border">${sale.createdAt}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>