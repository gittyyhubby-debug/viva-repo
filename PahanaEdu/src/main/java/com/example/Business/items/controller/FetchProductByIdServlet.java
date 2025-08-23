package com.example.Business.items.controller;

import com.example.persistence.model.CashierProducts;
import com.example.persistence.dao.ProductsDAO;
//import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/fetch-product")
public class FetchProductByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        ProductsDAO dao = new ProductsDAO();
        CashierProducts product = null;

        try {
            product = dao.getProductById(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        resp.setContentType("application/json");
//        if (product != null) {
//            resp.getWriter().write(new Gson().toJson(product));
//        } else {
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            resp.getWriter().write("{\"error\":\"Product not found\"}");
//        }
    }
}

