package com.example.Business.customer;

import com.example.persistence.model.Customer;
import com.example.util.DBConn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/edit-customer")
public class CustomerEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String nic = request.getParameter("nic");
        Customer customer = null;

        try (Connection conn = DBConn.getConnection()) {
            String sql = "SELECT * FROM customers WHERE nic=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nic);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setNic(rs.getString("nic"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("customer-edit.jsp").forward(request, response);
    }
}
