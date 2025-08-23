package com.example.Business.register.controller;

import com.example.Business.register.dto.CashierDTO;
import com.example.Business.register.service.CashierService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/signup2")
public class CusRegController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CusRegController.class.getName());
    private final CashierService regService = new CashierService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        CashierDTO dto = new CashierDTO(name, email, username, password);
        RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");

        try {
            boolean success = regService.registerCustomer(dto);
            req.setAttribute("status", success ? "success" : "failed");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error registering customer", e);
            req.setAttribute("status", "failed");
        }

        dispatcher.forward(req, resp);
    }
}
