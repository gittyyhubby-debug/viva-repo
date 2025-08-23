package com.example.Business.items.controller;

import com.example.Business.items.dto.ProductDTO;
import com.example.Business.items.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/viewProducts")
public class ViewProductsController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ViewProductsController.class.getName());
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            List<ProductDTO> products = productService.getAllProductViews();
            req.setAttribute("products", products);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/ProductForm.jsp");
            dispatcher.forward(req, resp);
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving products", e);
            req.setAttribute("error", "Failed to load products");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/ProductForm.jsp");
            dispatcher.forward(req, resp);
        }
    }
}