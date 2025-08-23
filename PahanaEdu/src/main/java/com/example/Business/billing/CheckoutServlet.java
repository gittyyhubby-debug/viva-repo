package com.example.Business.billing;

import com.example.persistence.dao.ProductsDAO;
import com.example.persistence.model.CartItem;
import com.example.persistence.model.CashierProducts;
import com.example.util.DBConn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private final ProductsDAO productsDAO = new ProductsDAO();

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

        // Calculate total
        double totalAmount = 0;
        for (CartItem item : cart) {
            totalAmount += item.getTotal();
        }

        try (Connection conn = DBConn.getConnection()) {
            conn.setAutoCommit(false);

            // Validate and update stock
            for (CartItem item : cart) {
                CashierProducts product = productsDAO.getProductById(item.getProduct().getId());
                if (product == null || product.getQuantity() < item.getQuantity()) {
                    resp.sendRedirect("billing.jsp?error=invalid");
                    return;
                }
                int newQty = product.getQuantity() - item.getQuantity();
                productsDAO.updateProductQuantity(product.getId(), newQty);
            }

            // Insert into sales table
            String insertSaleSQL = "INSERT INTO sales (customer_name, customer_phone, total_amount) VALUES (?, ?, ?)";
            PreparedStatement saleStmt = conn.prepareStatement(insertSaleSQL, Statement.RETURN_GENERATED_KEYS);
            saleStmt.setString(1, customerName);
            saleStmt.setString(2, customerPhone);
            saleStmt.setDouble(3, totalAmount);
            saleStmt.executeUpdate();

            ResultSet rs = saleStmt.getGeneratedKeys();
            int saleId = 0;
            if (rs.next()) {
                saleId = rs.getInt(1);
            }

            // Insert sale_items
            String insertItemSQL = "INSERT INTO sale_items (sale_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(insertItemSQL);

            for (CartItem item : cart) {
                itemStmt.setInt(1, saleId);
                itemStmt.setInt(2, item.getProduct().getId());
                itemStmt.setInt(3, item.getQuantity());
                itemStmt.setDouble(4, item.getProduct().getPrice());
                itemStmt.addBatch();
            }

            itemStmt.executeBatch();
            conn.commit();

            // Save invoice data to session
            session.setAttribute("lastInvoice", cart);
            session.setAttribute("customerName", customerName);
            session.setAttribute("customerPhone", customerPhone);
            session.setAttribute("totalAmount", totalAmount);

            session.removeAttribute("cart");

            resp.sendRedirect("Invoice.jsp?status=billed");

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("billing.jsp?error=database");
        }
    }
}
