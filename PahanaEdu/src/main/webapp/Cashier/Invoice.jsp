<%--
  Created by IntelliJ IDEA.
  User: abhit
  Date: 8/4/2025
  Time: 7:04 AM
  Professional Invoice with modern UI design
--%>
<%@ page import="com.example.persistence.model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<CartItem> cart = (List<CartItem>) session.getAttribute("lastInvoice");
  String customer = (String) session.getAttribute("customerName");
  String mobile = (String) session.getAttribute("customerPhone");
  double discount = session.getAttribute("discount") != null ? (double) session.getAttribute("discount") : 0.0;
  double serviceCharge = session.getAttribute("serviceCharge") != null ? (double) session.getAttribute("serviceCharge") : 0.0;
  double totalAmount = session.getAttribute("totalAmount") != null ? (double) session.getAttribute("totalAmount") : 0.0;

  // Generate invoice details
  SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
  String currentDate = dateFormat.format(new Date());

  Calendar cal = Calendar.getInstance();
  cal.add(Calendar.DAY_OF_MONTH, 30);
  String dueDate = dateFormat.format(cal.getTime());

  // Generate invoice number (you can modify this logic)
  String invoiceNo = "INV-" + String.format("%06d", (int)(Math.random() * 999999) + 1);
  String accountNo = "ACC-" + String.format("%08d", (int)(Math.random() * 99999999) + 1);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Invoice - Pahana Edu</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Arial', sans-serif;
      background-color: #f5f5f5;
      padding: 20px;
      color: #333;
    }

    .invoice-container {
      max-width: 800px;
      margin: 0 auto;
      background: white;
      box-shadow: 0 0 20px rgba(0,0,0,0.1);
      border-radius: 8px;
      overflow: hidden;
    }

    .invoice-header {
      background: linear-gradient(135deg, #2c5aa0, #4a7bc8);
      color: white;
      padding: 40px 40px 20px 40px;
      position: relative;
    }

    .invoice-header::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      height: 4px;
      background: #1a4480;
    }

    .invoice-title {
      font-size: 48px;
      font-weight: bold;
      letter-spacing: 3px;
      margin-bottom: 20px;
      text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
    }

    .invoice-content {
      padding: 40px;
    }

    .company-client-section {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 60px;
      margin-bottom: 40px;
    }

    .company-info, .client-info {
      line-height: 1.6;
    }

    .company-info h3, .client-info h3 {
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 15px;
      color: #2c5aa0;
    }

    .company-info p, .client-info p {
      margin-bottom: 5px;
      color: #555;
    }

    .invoice-details {
      background: #f8f9fa;
      padding: 20px;
      border-radius: 8px;
      margin-bottom: 40px;
    }

    .details-grid {
      display: grid;
      grid-template-columns: repeat(2, 1fr);
      gap: 20px;
    }

    .detail-item {
      display: flex;
      justify-content: space-between;
      padding: 8px 0;
      border-bottom: 1px solid #e0e0e0;
    }

    .detail-item:last-child {
      border-bottom: none;
    }

    .detail-label {
      font-weight: bold;
      color: #2c5aa0;
    }

    .detail-value {
      color: #555;
    }

    .items-section {
      margin-bottom: 40px;
    }

    .items-table {
      width: 100%;
      border-collapse: collapse;
      background: white;
      border-radius: 8px;
      overflow: hidden;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }

    .items-table thead {
      background: linear-gradient(135deg, #2c5aa0, #4a7bc8);
      color: white;
    }

    .items-table th {
      padding: 20px 15px;
      text-align: left;
      font-weight: bold;
      font-size: 14px;
      letter-spacing: 1px;
      text-transform: uppercase;
    }

    .items-table td {
      padding: 20px 15px;
      border-bottom: 1px solid #f0f0f0;
    }

    .items-table tbody tr:hover {
      background-color: #f8f9fa;
    }

    .item-name {
      font-weight: bold;
      color: #333;
      margin-bottom: 5px;
    }

    .item-id {
      color: #666;
      font-size: 12px;
      font-style: italic;
    }

    .text-center {
      text-align: center;
    }

    .text-right {
      text-align: right;
    }

    .totals-section {
      background: #f8f9fa;
      padding: 30px;
      border-radius: 8px;
      margin-top: 30px;
    }

    .totals-table {
      width: 100%;
      max-width: 400px;
      margin-left: auto;
    }

    .totals-table tr {
      border-bottom: 1px solid #e0e0e0;
    }

    .totals-table tr:last-child {
      border-bottom: 3px solid #2c5aa0;
      font-weight: bold;
      font-size: 18px;
      color: #2c5aa0;
    }

    .totals-table td {
      padding: 12px 0;
    }

    .actions {
      margin-top: 40px;
      display: flex;
      gap: 15px;
      justify-content: flex-end;
    }

    .btn {
      padding: 12px 24px;
      border: none;
      border-radius: 6px;
      cursor: pointer;
      font-weight: bold;
      text-decoration: none;
      display: inline-flex;
      align-items: center;
      gap: 8px;
      transition: all 0.3s ease;
    }

    .btn-primary {
      background: #2c5aa0;
      color: white;
    }

    .btn-primary:hover {
      background: #1a4480;
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(44, 90, 160, 0.3);
    }

    .btn-secondary {
      background: #6c757d;
      color: white;
    }

    .btn-secondary:hover {
      background: #545b62;
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(108, 117, 125, 0.3);
    }

    .no-print {
      /* This class will hide elements when printing */
    }

    @media print {
      body {
        background: white;
        padding: 0;
      }

      .invoice-container {
        box-shadow: none;
        border-radius: 0;
      }

      .no-print {
        display: none !important;
      }

      .invoice-header {
        background: #2c5aa0 !important;
        -webkit-print-color-adjust: exact;
      }

      .items-table thead {
        background: #2c5aa0 !important;
        -webkit-print-color-adjust: exact;
      }
    }

    @media (max-width: 768px) {
      .company-client-section {
        grid-template-columns: 1fr;
        gap: 30px;
      }

      .details-grid {
        grid-template-columns: 1fr;
      }

      .invoice-content {
        padding: 20px;
      }

      .invoice-header {
        padding: 30px 20px 15px 20px;
      }

      .invoice-title {
        font-size: 36px;
      }

      .items-table th,
      .items-table td {
        padding: 12px 8px;
        font-size: 14px;
      }

      .actions {
        flex-direction: column;
      }
    }
  </style>
</head>
<body>
<div class="invoice-container">
  <!-- Header -->
  <div class="invoice-header">
    <h1 class="invoice-title">INVOICE</h1>
  </div>

  <!-- Content -->
  <div class="invoice-content">
    <!-- Company and Client Information -->
    <div class="company-client-section">
      <div class="company-info">
        <h3>🎓 Pahana Edu</h3>
        <p>123 Education Street</p>
        <p>Colombo, Western Province, Sri Lanka</p>
        <p>Phone: +94 11 234 5678</p>
        <p>Email: info@pahanaedu.lk</p>
        <p>Website: www.pahanaedu.lk</p>
      </div>

      <div class="client-info">
        <h3>Billed To</h3>
        <p><%= customer != null ? customer : "Walk-in Customer" %></p>
        <% if (mobile != null && !mobile.trim().isEmpty()) { %>
        <p>Phone: <%= mobile %></p>
        <% } else { %>
        <p>Phone: -</p>
        <% } %>
      </div>
    </div>

    <!-- Invoice Details -->
    <div class="invoice-details">
      <div class="details-grid">
        <div class="detail-item">
          <span class="detail-label">Invoice No:</span>
          <span class="detail-value"><%= invoiceNo %></span>
        </div>
        <div class="detail-item">
          <span class="detail-label">Account No:</span>
          <span class="detail-value"><%= accountNo %></span>
        </div>
        <div class="detail-item">
          <span class="detail-label">Issue Date:</span>
          <span class="detail-value"><%= currentDate %></span>
        </div>
        <div class="detail-item">
          <span class="detail-label">Due Date:</span>
          <span class="detail-value"><%= dueDate %></span>
        </div>
      </div>
    </div>

    <!-- Items Table -->
    <div class="items-section">
      <table class="items-table">
        <thead>
        <tr>
          <th>Item Details</th>
          <th class="text-center">Qty</th>
          <th class="text-right">Unit Price</th>
          <th class="text-right">Total</th>
        </tr>
        </thead>
        <tbody>
        <%
          double grandTotal = 0;
          if (cart != null && !cart.isEmpty()) {
            for (CartItem item : cart) {
              double rowTotal = item.getTotal();
              grandTotal += rowTotal;
        %>
        <tr>
          <td>
            <div class="item-name"><%= item.getProduct().getName() %></div>
            <div class="item-id">ID: <%= item.getProduct().getId() %></div>
          </td>
          <td class="text-center"><%= item.getQuantity() %></td>
          <td class="text-right">Rs. <%= String.format("%.2f", item.getProduct().getPrice()) %></td>
          <td class="text-right">Rs. <%= String.format("%.2f", rowTotal) %></td>
        </tr>
        <%
          }
        } else {
        %>
        <tr>
          <td colspan="4" style="text-align: center; padding: 40px; color: #666;">
            No items found in invoice
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>

    <!-- Totals Section -->
    <div class="totals-section">
      <table class="totals-table">
        <tr>
          <td>Subtotal:</td>
          <td class="text-right">Rs. <%= String.format("%.2f", grandTotal) %></td>
        </tr>
        <% if (discount > 0) { %>
        <tr>
          <td>Discount:</td>
          <td class="text-right">Rs. <%= String.format("%.2f", discount) %></td>
        </tr>
        <% } %>
        <% if (serviceCharge > 0) { %>
        <tr>
          <td>Service Charge:</td>
          <td class="text-right">Rs. <%= String.format("%.2f", serviceCharge) %></td>
        </tr>
        <% } %>
        <tr>
          <td>Total Amount:</td>
          <td class="text-right">Rs. <%= String.format("%.2f", totalAmount > 0 ? totalAmount : grandTotal) %></td>
        </tr>
      </table>
    </div>

    <!-- Action Buttons -->
    <div class="actions no-print">
      <button class="btn btn-primary" onclick="printInvoice()">
        🖨️ Print Invoice
      </button>
      <a href="billing.jsp" class="btn btn-secondary">
        🔄 New Bill
      </a>
    </div>
  </div>
</div>

<script>
  // Print function
  function printInvoice() {
    window.print();
  }

  // Auto-print on page load (optional - uncomment if needed)
  // window.onload = function() {
  //     window.print();
  // };
</script>
</body>
</html>