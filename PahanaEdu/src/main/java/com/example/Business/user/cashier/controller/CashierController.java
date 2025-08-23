package com.example.Business.cashier.controller;

import com.example.Business.user.cashier.dto.CashierDTO;
import com.example.Business.user.cashier.service.CashierService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cashiers")
public class CashierController extends HttpServlet {

    private final CashierService cashierService = new CashierService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CashierDTO> cashiers = cashierService.getAllCashiers();
        req.setAttribute("cashiers", cashiers);

        req.getRequestDispatcher("/Admin/Cashiers/cashiers.jsp").forward(req, resp);
    }
}
