<%@ page import="com.example.persistence.model.CartItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession userSession = request.getSession(false);
    String username = null;
    if (userSession != null) {
        username = (String) userSession.getAttribute("username");
    } else {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>POS Billing</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-950 text-white min-h-screen p-6 font-sans">

<div class="max-w-5xl mx-auto">

    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <h1 class="text-3xl font-bold text-white">🧾 POS Billing System</h1>
        <div class="text-sm text-gray-300">
            Welcome, <span class="font-semibold text-white"><%= username != null ? username : "Guest" %></span>
            <a href="help.jsp" class="ml-4 text-white-400 hover:text-white-600">🆘 Help</a>
            <a href="${pageContext.request.contextPath}/Cashier/logout" class="ml-4 text-red-400 hover:text-red-600">Logout</a>
        </div>
    </div>

    <!-- Action Buttons -->
    <div class="flex gap-4 mb-6">
        <a href="customers" class="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded text-white font-medium shadow">
            👥 Manage Customers
        </a>
    </div>

    <!-- Checkout -->
    <form id="checkoutForm" method="post" action="checkout"
          class="bg-gray-800 mt-6 p-6 rounded space-y-4"
          onsubmit="return openPaymentModal(event)">
        <div class="grid grid-cols-2 gap-4">
            <div class="relative">
                <input id="customerName" name="customerName" placeholder="Customer Name"
                       class="px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring w-full" autocomplete="off" oninput="showSuggestions()">
                <div id="nameSuggestions" class="absolute z-10 bg-gray-800 border border-gray-600 rounded-md mt-1 w-full max-h-48 overflow-y-auto hidden"></div>
            </div>
            <input id="customerPhone" name="customerPhone" placeholder="Mobile Number"
                   class="px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring" autocomplete="off">
        </div>
        <button type="submit"
                class="w-full bg-indigo-600 hover:bg-indigo-700 px-4 py-2 rounded text-white font-semibold">
            ✅ Checkout & Print Bill
        </button>
    </form>

    <!-- Payment Modal -->
    <div id="paymentModal" class="fixed inset-0 bg-black bg-opacity-50 hidden flex items-center justify-center z-50">
        <div class="bg-white rounded-lg p-6 w-96 text-black shadow-lg">
            <h2 class="text-2xl font-bold mb-4 text-center flex items-center justify-center">
                <span class="mr-2">💰</span> Payment Details
            </h2>
            <p class="mb-4">Total Bill: Rs. <span id="modalTotal" class="font-semibold text-lg">0.00</span></p>
            <div class="mb-4">
                <label for="amountGiven" class="block text-sm font-medium mb-1">Amount Given: Rs.</label>
                <input type="number" id="amountGiven" placeholder="Enter amount" min="0" step="0.01"
                       class="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-indigo-500" autocomplete="off">
            </div>
            <div class="flex justify-end gap-2">
                <button type="button" onclick="closePaymentModal()" class="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400">Cancel</button>
                <button type="button" onclick="proceedPayment()" class="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700">Proceed</button>
            </div>
        </div>
    </div>

    <!-- Balance Modal -->
    <div id="balanceModal" class="fixed inset-0 bg-black bg-opacity-50 hidden flex items-center justify-center z-50">
        <div class="bg-white rounded-lg p-6 w-80 text-black shadow-lg text-center">
            <h2 class="text-2xl font-bold mb-4 flex items-center justify-center">
                <span class="mr-2">💵</span> Balance to Return
            </h2>
            <p class="text-lg font-semibold mb-6">Rs. <span id="balanceAmountModal">0.00</span></p>
            <div class="flex justify-center gap-4">
                <button type="button" onclick="backToPaymentModal()" class="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400">Back</button>
                <button type="button" onclick="confirmPayment()" class="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">Confirm Payment</button>
            </div>
        </div>
    </div>

    <!-- Invalid Input Modal -->
    <div id="invalidInputModal" class="fixed inset-0 bg-black bg-opacity-50 hidden flex items-center justify-center z-50">
        <div class="bg-yellow-400 rounded-lg p-6 w-80 text-black shadow-lg text-center">
            <h2 class="text-xl font-bold mb-4 flex items-center justify-center">
                <span class="mr-2">⚠️</span> Invalid Input
            </h2>
            <p class="mb-4 font-semibold">Please check values and try again.</p>
            <button type="button" onclick="closeInvalidInputModal()"
                    class="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700">
                OK
            </button>
        </div>
    </div>

    <!-- Message Alerts -->
    <%
        String status = request.getParameter("status");
        String error = request.getParameter("error");
    %>
    <% if ("added".equals(status)) { %>
    <div class="bg-green-500 text-white p-3 rounded mb-4">✅ Product added to bill!</div>
    <% } else if ("notfound".equals(error)) { %>
    <div class="bg-red-500 text-white p-3 rounded mb-4">❌ Product not found! Please check the ID.</div>
    <% } else if ("billed".equals(status)) { %>
    <div class="bg-blue-400 text-black p-3 rounded mb-4">🧾 Bill generated successfully!</div>
    <% } else if ("empty".equals(error)) { %>
    <div class="bg-red-600 text-white p-3 rounded mb-4">🛑 Cannot bill. The cart is empty.</div>
    <% } else if ("saving".equals(error)) { %>
    <div class="bg-red-600 text-white p-3 rounded mb-4">❌ Error saving the bill. Please try again.</div>
    <% } else if ("outofstock".equals(error)) { %>
    <div class="bg-red-600 text-white p-3 rounded mb-4">❌ Quantity exceeds stock availability!</div>
    <% } %>

    <!-- Add Product Form -->
    <div class="bg-gray-800 p-4 rounded mb-6 flex gap-4 items-center">
        <input type="number" id="productId" placeholder="Product ID" min="1"
               class="px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring w-1/3" autocomplete="off">
        <input type="number" id="quantity" placeholder="Qty" value="1" min="1"
               class="px-4 py-2 rounded bg-gray-700 border border-gray-600 focus:outline-none focus:ring w-1/4" autocomplete="off">
        <button type="button" onclick="addItem()" class="bg-green-600 hover:bg-green-700 px-5 py-2 rounded text-white font-semibold">
            ➕ Add Item
        </button>
    </div>

    <!-- Clear Cart Button -->
    <div class="flex justify-end mb-2 gap-2">
        <a href="clear-cart"
           class="bg-red-600 hover:bg-red-700 text-white font-semibold px-4 py-2 rounded">
            🗑️ Clear Bill
        </a>
    </div>

    <!-- Bill Table -->
    <div class="overflow-x-auto bg-gray-800 rounded">
        <table class="w-full table-auto text-left text-white">
            <thead class="bg-gray-700 text-sm uppercase">
            <tr>
                <th class="px-4 py-2">ID</th>
                <th class="px-4 py-2">Name</th>
                <th class="px-4 py-2">Category</th>
                <th class="px-4 py-2">Price</th>
                <th class="px-4 py-2">Qty</th>
                <th class="px-4 py-2">Total</th>
                <th class="px-4 py-2">Action</th>
            </tr>
            </thead>
            <tbody class="text-sm">
            <%
                List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
                double total = 0;
                if (cart != null && !cart.isEmpty()) {
                    for (CartItem item : cart) {
                        double rowTotal = item.getTotal();
                        total += rowTotal;
            %>
            <tr class="border-t border-gray-600">
                <td class="px-4 py-2"><%= item.getProduct().getId() %></td>
                <td class="px-4 py-2"><%= item.getProduct().getName() %></td>
                <td class="px-4 py-2"><%= item.getProduct().getCategory() %></td>
                <td class="px-4 py-2">Rs. <%= String.format("%.2f", item.getProduct().getPrice()) %></td>
                <td class="px-4 py-2"><%= item.getQuantity() %></td>
                <td class="px-4 py-2">Rs. <%= String.format("%.2f", rowTotal) %></td>
                <td class="px-4 py-2">
                    <a href="remove-item?id=<%= item.getProduct().getId() %>"
                       class="text-red-500 hover:text-red-700 font-semibold">❌ Remove</a>
                </td>
            </tr>
            <% } } else { %>
            <tr>
                <td colspan="7" class="text-center px-4 py-4 text-gray-400">🛒 Cart is empty</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="text-right mt-4 text-xl font-bold">
        Total: <span id="totalAmount">Rs. <%= String.format("%.2f", total) %></span>
    </div>

</div>

<footer class="text-center text-gray-500 text-sm py-6 border-t border-gray-800">
    © 2025 Pahana Edu. All Rights Reserved.
</footer>

<script>
    let customers = [];

    // Fetch customers from backend for auto-complete
    fetch('${pageContext.request.contextPath}/customers?format=json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched customers:', data);
            customers = data;
            if (!Array.isArray(customers) || customers.length === 0) {
                console.warn('No customers found or invalid data format');
            }
        })
        .catch(error => {
            console.error('Error fetching customers:', error);
            alert('Failed to load customer data. Please try again.');
        });

    // Show suggestions for customer name
    function showSuggestions() {
        const input = document.getElementById('customerName').value.trim().toLowerCase();
        const suggestionsDiv = document.getElementById('nameSuggestions');
        suggestionsDiv.innerHTML = '';
        suggestionsDiv.classList.add('hidden');

        if (input.length === 0) {
            return;
        }

        const filtered = customers.filter(c =>
            c.name && c.name.toLowerCase().includes(input)
        );

        if (filtered.length === 0) {
            return;
        }

        filtered.forEach(c => {
            const div = document.createElement('div');
            div.classList.add('px-4', 'py-2', 'hover:bg-gray-700', 'cursor-pointer', 'text-white');
            div.textContent = c.name + (c.phone ? ` (${c.phone})` : '');
            div.onclick = () => {
                document.getElementById('customerName').value = c.name || '';
                document.getElementById('customerPhone').value = c.phone || '';
                suggestionsDiv.classList.add('hidden');
            };
            suggestionsDiv.appendChild(div);
        });
        suggestionsDiv.classList.remove('hidden');
    }

    // Hide suggestions when clicking outside
    document.addEventListener('click', (event) => {
        const suggestionsDiv = document.getElementById('nameSuggestions');
        const customerNameInput = document.getElementById('customerName');
        if (!suggestionsDiv.contains(event.target) && event.target !== customerNameInput) {
            suggestionsDiv.classList.add('hidden');
        }
    });

    function addItem() {
        const id = document.getElementById("productId").value.trim();
        const qty = document.getElementById("quantity").value.trim();

        if (!id || !qty || Number(qty) <= 0) {
            const url = new URL(window.location.href);
            url.searchParams.set('error', 'invalid');
            window.location.href = url.toString();
            return;
        }

        const form = document.createElement("form");
        form.method = "post";
        form.action = "add-to-cart";

        const idInput = document.createElement("input");
        idInput.type = "hidden";
        idInput.name = "id";
        idInput.value = id;

        const qtyInput = document.createElement("input");
        qtyInput.type = "hidden";
        qtyInput.name = "quantity";
        qtyInput.value = qty;

        form.appendChild(idInput);
        form.appendChild(qtyInput);
        document.body.appendChild(form);
        form.submit();
    }

    let totalAmount = 0;
    let givenAmount = 0;

    function openPaymentModal(event) {
        event.preventDefault();
        const totalText = document.getElementById("totalAmount").textContent || "";
        const parts = totalText.split(" ");
        let parsedTotal = 0;
        if (parts.length === 2) {
            parsedTotal = parseFloat(parts[1]);
        }

        if (isNaN(parsedTotal) || parsedTotal <= 0) {
            alert("🛑 Cannot checkout. Total is 0 or invalid.");
            return false;
        }

        totalAmount = parsedTotal;
        document.getElementById("modalTotal").textContent = totalAmount.toFixed(2);
        document.getElementById("amountGiven").value = "";
        document.getElementById("paymentModal").classList.remove("hidden");
        document.getElementById("balanceModal").classList.add("hidden");
        return false;
    }

    function closePaymentModal() {
        document.getElementById("paymentModal").classList.add("hidden");
    }

    function proceedPayment() {
        const givenInput = document.getElementById("amountGiven");
        const given = parseFloat(givenInput.value);
        if (isNaN(given) || given < totalAmount) {
            alert("⚠️ Please enter a valid amount greater than or equal to total.");
            return;
        }

        givenAmount = given;
        const balance = given - totalAmount;
        closePaymentModal();
        document.getElementById("balanceAmountModal").textContent = balance.toFixed(2);
        document.getElementById("balanceModal").classList.remove("hidden");
    }

    function backToPaymentModal() {
        document.getElementById("balanceModal").classList.add("hidden");
        document.getElementById("paymentModal").classList.remove("hidden");
    }

    function confirmPayment() {
        let form = document.getElementById("checkoutForm");
        let oldInput = document.getElementById("givenAmountInput");
        if (oldInput) form.removeChild(oldInput);

        let input = document.createElement("input");
        input.type = "hidden";
        input.name = "amountGiven";
        input.id = "givenAmountInput";
        input.value = givenAmount;
        form.appendChild(input);

        form.submit();
    }

    function closeInvalidInputModal() {
        document.getElementById('invalidInputModal').classList.add('hidden');
        const url = new URL(window.location.href);
        url.searchParams.delete('error');
        window.history.replaceState({}, document.title, url.toString());
    }

    window.onload = function () {
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('error') === 'invalid') {
            document.getElementById('invalidInputModal').classList.remove('hidden');
        }
    };
</script>

</body>
</html>