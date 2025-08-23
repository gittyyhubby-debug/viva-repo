package com.example.Business.billing.service;

import com.example.Business.billing.dto.CartItemDTO;
import com.example.Business.billing.dto.CheckoutDTO;
import com.example.persistence.dao.ProductsDAO;
//import com.example.persistence.dao.SalesDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.example.util.DBConn;

public class CheckoutService {

    private final ProductsDAO productsDAO = new ProductsDAO();
//    private final SalesDAO salesDAO = new SalesDAO();

    public void processCheckout(CheckoutDTO checkoutDTO) throws SQLException {
        List<CartItemDTO> cartItems = checkoutDTO.getCartItems();

        try (Connection conn = DBConn.getConnection()) {
            conn.setAutoCommit(false);

            // Validate stock and update quantities
            for (CartItemDTO item : cartItems) {
                // Fetch product to check stock
                var product = productsDAO.getProductById(item.getProductId());
                if (product == null) throw new SQLException("Product not found: " + item.getProductId());
                if (product.getQuantity() < item.getQuantity()) {
                    throw new SQLException("Insufficient stock for product " + product.getName());
                }
                int newQty = product.getQuantity() - item.getQuantity();
                productsDAO.updateProductQuantity(product.getId(), newQty);
            }

            // Calculate total amount
            double totalAmount = cartItems.stream()
                    .mapToDouble(i -> i.getPrice() * i.getQuantity())
                    .sum();

            // Insert sale and sale items
//            int saleId = salesDAO.insertSale(checkoutDTO.getCustomerName(), checkoutDTO.getCustomerPhone(), totalAmount);
//            salesDAO.insertSaleItems(saleId, cartItems);

            conn.commit();
        }
    }
}
