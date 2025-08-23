package com.example.persistence.dao;

import com.example.persistence.model.Sale;
import com.example.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    public List<Sale> getSalesWithFilters(String fromDate, String toDate, String customer,
                                          String minAmount, String maxAmount, String sortBy, String topN) throws SQLException {
        List<Sale> salesList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT id, customer_name, customer_phone, total_amount, discount, service_charge, created_at " +
                        "FROM sales WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        // Build dynamic query based on filters
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

        // Add sorting
        if ("latest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY created_at DESC");
        } else if ("highest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY total_amount DESC");
        } else if ("lowest".equalsIgnoreCase(sortBy)) {
            sql.append(" ORDER BY total_amount ASC");
        } else {
            sql.append(" ORDER BY id DESC");
        }

        // Add limit
        if (topN != null && !topN.isEmpty()) {
            sql.append(" LIMIT ?");
            params.add(Integer.parseInt(topN));
        }

        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sale sale = new Sale(
                            rs.getInt("id"),
                            rs.getString("customer_name"),
                            rs.getString("customer_phone"),
                            rs.getDouble("total_amount"),
                            rs.getDouble("discount"),
                            rs.getDouble("service_charge"),
                            rs.getString("created_at")
                    );
                    salesList.add(sale);
                }
            }
        }

        return salesList;
    }

    public List<Sale> getAllSales() throws SQLException {
        return getSalesWithFilters(null, null, null, null, null, null, null);
    }

    public double getTotalSalesAmount() throws SQLException {
        String sql = "SELECT SUM(total_amount) FROM sales";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0.0;
    }

    public int getTotalSalesCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM sales";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
}