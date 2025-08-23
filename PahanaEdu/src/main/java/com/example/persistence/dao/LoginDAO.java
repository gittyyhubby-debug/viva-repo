package com.example.persistence.dao;

import com.example.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean authenticateCashier(String username, String password) throws SQLException {
        String query = "SELECT * FROM login_tbl WHERE username = ? AND password = ?";

        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // returns true if username+password match
            }
        }
    }
}
