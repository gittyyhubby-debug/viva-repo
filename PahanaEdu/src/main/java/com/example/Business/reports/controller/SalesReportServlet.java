package com.example.Business.reports.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/SalesReport")
public class SalesReportServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pahana_edu";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String customer = request.getParameter("customer");
        String minAmount = request.getParameter("minAmount");
        String maxAmount = request.getParameter("maxAmount");
        String sortBy = request.getParameter("sortBy");
        String topN = request.getParameter("topN");

        List<Sale> salesList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT id, customer_name, customer_phone, total_amount, discount, service_charge, created_at " +
                        "FROM sales WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (fromDate != null && !fromDate.isEmpty()) {
            sql.append(" AND DATE(created_at) >= ?");
            params.add(fromDate);
        }
        if (toDate != null && !toDate.isEmpty()) {
            sql.append(" AND DATE(created_at) <= ?");
            params.add(toDate);
        }
        if (customer != null && !customer.isEmpty()) {
            sql.append(" AND customer_name LIKE ?");
            params.add("%" + customer + "%");
        }
        if (minAmount != null && !minAmount.isEmpty()) {
            sql.append(" AND total_amount >= ?");
            params.add(Double.parseDouble(minAmount));
        }
        if (maxAmount != null && !maxAmount.isEmpty()) {
            sql.append(" AND total_amount <= ?");
            params.add(Double.parseDouble(maxAmount));
        }
        if ("latest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY created_at DESC");
        } else if ("highest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY total_amount DESC");
        } else if ("lowest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY total_amount ASC");
        } else {
            sql.append(" ORDER BY id DESC");
        }
        if (topN != null && !topN.isEmpty()) {
            sql.append(" LIMIT ?");
            params.add(Integer.parseInt(topN));
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    salesList.add(new Sale(
                            rs.getInt("id"),
                            rs.getString("customer_name"),
                            rs.getString("customer_phone"),
                            rs.getDouble("total_amount"),
                            rs.getDouble("discount"),
                            rs.getDouble("service_charge"),
                            rs.getString("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error: " + e.getMessage());
        }

        if ("true".equals(request.getParameter("ajax"))) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                for (Sale sale : salesList) {
                    out.println("<tr class='hover:bg-gray-50'>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getId() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getCustomerName() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getCustomerPhone() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getTotalAmount() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getDiscount() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getServiceCharge() + "</td>");
                    out.println("<td class='px-4 py-2 border'>" + sale.getCreatedAt() + "</td>");
                    out.println("</tr>");
                }
            } finally {
                out.close();
            }
        } else {
            request.setAttribute("salesList", salesList);
            request.getRequestDispatcher("Admin/SalesReport.jsp").forward(request, response);
        }
    }

    public static class Sale {
        private int id;
        private String customerName;
        private String customerPhone;
        private double totalAmount;
        private double discount;
        private double serviceCharge;
        private String createdAt;

        public Sale(int id, String customerName, String customerPhone, double totalAmount,
                    double discount, double serviceCharge, String createdAt) {
            this.id = id;
            this.customerName = customerName;
            this.customerPhone = customerPhone;
            this.totalAmount = totalAmount;
            this.discount = discount;
            this.serviceCharge = serviceCharge;
            this.createdAt = createdAt;
        }

        public int getId() { return id; }
        public String getCustomerName() { return customerName; }
        public String getCustomerPhone() { return customerPhone; }
        public double getTotalAmount() { return totalAmount; }
        public double getDiscount() { return discount; }
        public double getServiceCharge() { return serviceCharge; }
        public String getCreatedAt() { return createdAt; }
    }
}