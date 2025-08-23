package com.example.persistence.dao;

import com.example.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItemDAO {
    public static double getTotalSalesAmount() throws SQLException {
        String sql = "SELECT SUM(quantity * price) FROM sale_items";
        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }
}
