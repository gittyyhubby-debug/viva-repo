package com.example.Business.items.controller;


import com.example.Business.items.dto.ProductDTO;
import com.example.Business.items.service.ProductService;
import com.example.persistence.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductListController extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<ProductDTO> productList = productService.getAllProductViews();
        req.setAttribute("Product", productList); // match JSP variable name
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/Products/ProductForm.jsp");
        dispatcher.forward(req, resp);
    }
}
