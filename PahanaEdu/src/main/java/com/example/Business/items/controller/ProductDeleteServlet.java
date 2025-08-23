package com.example.Business.items.controller;

import com.example.persistence.dao.ProductsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-product")
public class ProductDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            ProductsDAO dao = new ProductsDAO();
            dao.deleteProduct(id);
            resp.sendRedirect("products");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("products?error=delete");
        }
    }
}
