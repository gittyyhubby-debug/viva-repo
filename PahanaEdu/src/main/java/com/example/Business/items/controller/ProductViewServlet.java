package com.example.Business.items.controller;

import com.example.persistence.model.CashierProducts;
import com.example.persistence.dao.ProductsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-products")
public class ProductViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductsDAO dao = new ProductsDAO();
            List<CashierProducts> products = dao.getAllProducts();
            req.setAttribute("productList", products);
            req.getRequestDispatcher("/Admin/Products/ProductIndex.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("AdminHome.jsp?error=view");
        }
    }
}
