package com.example.Business.billing;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.example.persistence.model.CartItem;

import java.io.IOException;
import java.util.List;

@WebServlet("/remove-item")
public class RemoveCartItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr != null) {
            try {
                int productId = Integer.parseInt(idStr);
                HttpSession session = request.getSession();
                List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

                if (cart != null) {
                    cart.removeIf(item -> item.getProduct().getId() == productId);
                    session.setAttribute("cart", cart);
                }
            } catch (NumberFormatException ignored) {
            }
        }

        response.sendRedirect("billing.jsp");
    }
}
