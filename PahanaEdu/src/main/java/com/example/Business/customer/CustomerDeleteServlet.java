package com.example.Business.customer;

import com.example.util.DBConn;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/delete-customer")
public class CustomerDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nic = request.getParameter("nic");

        try (Connection conn = DBConn.getConnection()) {
            String sql = "DELETE FROM customers WHERE nic=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nic);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("customers");
    }
}

