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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/addProduct")
public class ProductController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());
    private final ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String category = req.getParameter("category");
        String quantityStr = req.getParameter("quantity");
        String priceStr = req.getParameter("price");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Admin/Products/add-product.jsp");

        try {
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            ProductDTO dto = new ProductDTO(name, description, category, quantity, price);

            boolean success = productService.addProduct(dto);
            req.setAttribute("status", success ? "success" : "failed");
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Invalid number format for price or quantity", e);
            req.setAttribute("status", "failed");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding product", e);
            req.setAttribute("status", "failed");
        }

        dispatcher.forward(req, resp);
    }
}
