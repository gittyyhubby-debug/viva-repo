<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Pahana Edu POS - Admin Help</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-950 text-gray-100 font-sans">

<!-- Sticky Header -->
<header class="sticky top-0 z-50 bg-gradient-to-r from-indigo-600 via-purple-600 to-pink-500 shadow-lg">
  <div class="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">
    <div class="flex items-center gap-3">
      <h1 class="text-2xl font-bold tracking-wide">Pahana Edu POS - Admin</h1>
    </div>
    <nav class="hidden md:flex gap-6 text-sm font-medium">
      <a href="#products" class="hover:text-yellow-300 transition">📦 Products</a>
      <a href="#sales" class="hover:text-yellow-300 transition">📈 Sales Records</a>
      <a href="#billing" class="hover:text-yellow-300 transition">🧾 Billing</a>
      <a href="#checkout" class="hover:text-yellow-300 transition">🛒 Checkout</a>
      <a href="#faq" class="hover:text-yellow-300 transition">❓ FAQ</a>
    </nav>
    <a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold px-4 py-2 rounded-lg shadow transition">
      Back to Dashboard
    </a>
  </div>
</header>

<main class="max-w-7xl mx-auto px-6 py-12 space-y-20">

  <!-- Intro -->
  <section class="text-center space-y-4">
    <h2 class="text-4xl font-bold">Admin Help & Instructions</h2>
    <p class="text-gray-400 max-w-3xl mx-auto">
      This guide explains how administrators can manage the POS system, including adding products, viewing sales data, and performing essential operations.
    </p>
  </section>

  <!-- Features Overview -->
  <section class="grid md:grid-cols-2 gap-8">
    <div class="bg-gradient-to-br from-gray-800 to-gray-900 p-6 rounded-xl shadow-lg hover:shadow-2xl transition">
      <h3 class="text-xl font-semibold mb-2">📊 Improved Dashboard</h3>
      <p class="text-gray-400">Track sales, monitor inventory, and view reports in a clean and responsive dashboard.</p>
    </div>
    <div class="bg-gradient-to-br from-gray-800 to-gray-900 p-6 rounded-xl shadow-lg hover:shadow-2xl transition">
      <h3 class="text-xl font-semibold mb-2">💼 Full Admin Control</h3>
      <p class="text-gray-400">Manage products, customers, sales, and staff from one central place.</p>
    </div>
  </section>

  <!-- Add & View Products -->
  <section id="products" class="grid md:grid-cols-2 gap-8 items-center">
    <div>
      <span class="bg-green-600 text-xs px-3 py-1 rounded-full">Product Management</span>
      <h3 class="text-3xl font-bold mt-4">Add & View Products</h3>
      <p class="mt-4 text-gray-400">Admins can add new products to the inventory and view all existing products in the system.</p>
      <ol class="list-decimal ml-6 mt-3 text-gray-400 space-y-1">
        <li>Go to <strong>Products</strong> → <em>Add Product</em>.</li>
        <li>Fill in name, description, category, quantity, and price.</li>
        <li>Upload a product image (optional).</li>
        <li>Click <strong>Save</strong> to add it to the system.</li>
        <li>To view products, go to <strong>Products</strong> → <em>View Products</em>.</li>
        <li>Search, sort, and manage stock directly from the list view.</li>
      </ol>
    </div>
    <img src="images/viewProducts.png" alt="Add & View Products" class="rounded-lg shadow-lg hover:scale-105 transition-transform">
  </section>

  <!-- View Sales Records -->
  <section id="sales" class="grid md:grid-cols-2 gap-8 items-center">
    <img src="images/sales.png" alt="Sales Records" class="rounded-lg shadow-lg hover:scale-105 transition-transform">
    <div>
      <span class="bg-orange-600 text-xs px-3 py-1 rounded-full">Sales Records</span>
      <h3 class="text-3xl font-bold mt-4">View Sales Records</h3>
      <p class="mt-4 text-gray-400">Access detailed sales reports for analysis and decision-making.</p>
      <ul class="list-disc ml-6 mt-3 text-gray-400 space-y-1">
        <li>Go to <strong>Reports</strong> → <em>Sales Records</em>.</li>
        <li>Filter by date range, cashier, or product category.</li>
        <li>View totals, discounts, taxes, and net sales.</li>
        <li>Export sales reports as PDF or Excel for record-keeping.</li>
      </ul>
    </div>
  </section>

  <!-- Existing Sections -->
  <section id="billing" class="grid md:grid-cols-2 gap-8 items-center">
    <img src="images/billing-process.png" alt="Billing" class="rounded-lg shadow-lg hover:scale-105 transition-transform">
    <div>
      <span class="bg-purple-600 text-xs px-3 py-1 rounded-full">Billing Process</span>
      <h3 class="text-3xl font-bold mt-4">Quick and Accurate Billing</h3>
      <ul class="list-decimal ml-6 mt-3 text-gray-400 space-y-1">
        <li>Enter product ID or search by name.</li>
        <li>Adjust quantities or apply discounts.</li>
        <li>Select payment method & confirm.</li>
        <li>Print or email receipt.</li>
      </ul>
    </div>
  </section>

  <section id="checkout">
    <h3 class="text-3xl font-bold mb-6">✅ Checkout Process</h3>
    <div class="grid md:grid-cols-3 gap-6">
      <div class="bg-gray-800 p-4 rounded-lg hover:scale-105 transition cursor-pointer">
        <img src="images/checkout1.png" class="w-full h-32 object-cover rounded">
        <p class="mt-2 text-center text-gray-300">Enter Amount</p>
      </div>
      <div class="bg-gray-800 p-4 rounded-lg hover:scale-105 transition cursor-pointer">
        <img src="images/checkout2.png" class="w-full h-32 object-cover rounded">
        <p class="mt-2 text-center text-gray-300">Balance Display</p>
      </div>
      <div class="bg-gray-800 p-4 rounded-lg hover:scale-105 transition cursor-pointer">
        <img src="images/checkout3.png" class="w-full h-32 object-cover rounded">
        <p class="mt-2 text-center text-gray-300">Save & Print</p>
      </div>
    </div>
  </section>

  <!-- Admin Features -->
  <section class="bg-gray-900 p-6 rounded-xl shadow-lg">
    <h3 class="text-2xl font-bold mb-4">🛡 Admin Features</h3>
    <ul class="list-disc ml-6 text-gray-400 space-y-1">
      <li>Manage products (Add/Edit/Delete).</li>
      <li>Generate & export sales reports.</li>
      <li>View customer purchase history.</li>
    </ul>
  </section>

  <!-- FAQ -->
  <section id="faq" class="bg-gray-900 p-6 rounded-xl shadow-lg">
    <h3 class="text-2xl font-bold mb-4">❓ FAQ</h3>
    <div class="space-y-4 text-gray-400">
      <div>
        <h4 class="font-semibold">Can I edit a bill after checkout?</h4>
        <p>No, but you can issue a return or create a new bill.</p>
      </div>
      <div>
        <h4 class="font-semibold">Does the system work offline?</h4>
        <p>No, internet connection is required.</p>
      </div>
      <div>
        <h4 class="font-semibold">How do I backup my sales data?</h4>
        <p>Admins can export data from the Reports section.</p>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <footer class="text-center text-gray-500 text-sm py-6 border-t border-gray-800">
    © 2025 Pahana Edu. All Rights Reserved.
  </footer>

</main>

</body>
</html>
