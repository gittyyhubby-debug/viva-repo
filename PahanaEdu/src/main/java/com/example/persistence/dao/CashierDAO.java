package com.example.persistence.dao;

import com.example.persistence.model.Cashier;
import com.example.persistence.model.User;
import com.example.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CashierDAO {

    public List<Cashier> getAllCashiers() {
        List<Cashier> cashierList = new ArrayList<>();

        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login_tbl WHERE role = 'cashier'");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cashier cashier = new Cashier();
                cashier.setId(rs.getInt("id"));
                cashier.setName(rs.getString("name"));
                cashier.setUsername(rs.getString("username"));
                cashier.setEmail(rs.getString("email"));
                cashier.setProfile(rs.getString("profile"));

                cashierList.add(cashier);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Always log SQL exceptions
        }

        return cashierList;
    }

    public boolean saveUser(User user) {
        return false;
    }
}
