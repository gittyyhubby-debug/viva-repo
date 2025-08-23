package com.example.persistence.dao;

import com.example.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public static int getTotalCustomers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers";
        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}
