package com.example.Business.customer;

import com.example.util.DBConn;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/create-customer")
public class CustomerCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nic = request.getParameter("nic");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (nic == null || name == null || email == null || phone == null ||
                nic.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            response.sendRedirect("customer-register.jsp?error=Please fill all fields");
            return;
        }

        try (Connection conn = DBConn.getConnection()) {
            String sql = "INSERT INTO customers (nic, name, email, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nic);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.executeUpdate();
            response.sendRedirect("customers");
        } catch (SQLIntegrityConstraintViolationException e) {
            response.sendRedirect("customer-register.jsp?error=NIC or Email already exists");
        } catch (SQLException e) {
            response.sendRedirect("customer-register.jsp?error=DB Error");
        }
    }
}

