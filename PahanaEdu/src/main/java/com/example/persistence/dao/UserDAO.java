package com.example.persistence.dao;

import com.example.persistence.model.User;
import com.example.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Existing INSERT method...

    public List<User> getAllCashiers() throws SQLException {
        List<User> cashiers = new ArrayList<>();
        String sql = "SELECT id, name, email, username FROM login_tbl WHERE role = 'cashier'";

        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User cashier = new User();
                cashier.setId(rs.getInt("id"));
                cashier.setName(rs.getString("name"));
                cashier.setEmail(rs.getString("email"));
                cashier.setUsername(rs.getString("username"));
                cashiers.add(cashier);
            }
        }

        return cashiers;
    }
}
