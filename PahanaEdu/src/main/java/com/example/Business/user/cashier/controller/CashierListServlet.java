package com.example.Business.user.cashier.controller;

import com.example.persistence.dao.UserDAO;
import com.example.persistence.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/cashiers")
public class CashierListServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            List<User> cashierList = userDAO.getAllCashiers();
            req.setAttribute("cashiers", cashierList);
        } catch (SQLException e) {
            req.setAttribute("error", "Error fetching cashiers.");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/cashiers.jsp");
        dispatcher.forward(req, resp);
    }
}
