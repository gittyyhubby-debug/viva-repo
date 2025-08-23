package com.example.Business.items.controller;

import com.example.persistence.model.CashierProducts;
import com.example.persistence.dao.ProductsDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-product")
public class ProductAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CashierProducts p = new CashierProducts();
            p.setName(req.getParameter("name"));
            p.setDescription(req.getParameter("description"));
            p.setCategory(req.getParameter("category"));
            p.setQuantity(Integer.parseInt(req.getParameter("quantity")));
            p.setPrice(Double.parseDouble(req.getParameter("price")));

            ProductsDAO dao = new ProductsDAO();
            dao.insertProduct(p);
            resp.sendRedirect("products");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("ProductForm.jsp?error=add");
        }
    }
}
