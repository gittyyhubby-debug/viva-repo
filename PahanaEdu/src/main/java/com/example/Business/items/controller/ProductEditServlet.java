package com.example.Business.items.controller;

import com.example.persistence.model.CashierProducts;
import com.example.persistence.dao.ProductsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-product")
public class ProductEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            ProductsDAO dao = new ProductsDAO();
            CashierProducts product = dao.getProductById(id);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/Admin/Products/ProductForm.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("products?error=editLoad");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CashierProducts p = new CashierProducts();
            p.setId(Integer.parseInt(req.getParameter("id")));
            p.setName(req.getParameter("name"));
            p.setDescription(req.getParameter("description"));
            p.setCategory(req.getParameter("category"));
            p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            p.setPrice(Double.parseDouble(req.getParameter("price")));

            ProductsDAO dao = new ProductsDAO();
            dao.updateProduct(p);
            resp.sendRedirect("products");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("products?error=editSave");
        }
    }
}

