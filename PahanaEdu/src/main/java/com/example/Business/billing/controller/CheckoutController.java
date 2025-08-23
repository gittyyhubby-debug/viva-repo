package com.example.Business.billing.controller;

import com.example.Business.billing.dto.CartItemDTO;
import com.example.Business.billing.dto.CheckoutDTO;
import com.example.Business.billing.mapper.CartItemMapper;
import com.example.persistence.model.CartItem;
import com.example.Business.billing.service.CheckoutService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

//@WebServlet("/Cashier/checkout")
public class CheckoutController extends HttpServlet {


    private final CheckoutService checkoutService = new CheckoutService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("billing.jsp?error=empty");
            return;
        }

        String customerName = req.getParameter("customerName");
        String customerPhone = req.getParameter("customerPhone");

        // Map CartItem to CartItemDTO
        List<CartItemDTO> cartDTO = cart.stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());

        CheckoutDTO checkoutDTO = new CheckoutDTO(customerName, customerPhone, cartDTO);

        try {
            checkoutService.processCheckout(checkoutDTO);

            // Save invoice info for the JSP
            session.setAttribute("lastInvoice", cart);
            session.setAttribute("customerName", customerName);
            session.setAttribute("customerPhone", customerPhone);
            double totalAmount = cart.stream().mapToDouble(CartItem::getTotal).sum();
            session.setAttribute("totalAmount", totalAmount);
            session.removeAttribute("cart");

            resp.sendRedirect("Invoice.jsp?status=billed");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("billing.jsp?error=database");
        }
    }
}
