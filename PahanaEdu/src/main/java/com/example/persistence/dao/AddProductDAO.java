package com.example.persistence.dao;

import com.example.Business.items.dto.ProductDTO;
import com.example.persistence.model.Product;
import com.example.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddProductDAO {

    // Updated SQL to include category and quantity
    private static final String INSERT_PRODUCT_SQL =
            "INSERT INTO products (name, description, price, category, quantity) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT id, name, description, price, category, quantity FROM products";

    public boolean addProduct(ProductDTO product) throws SQLException {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCT_SQL)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setString(4, product.getCategory());
            stmt.setInt(5, product.getQuantity());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    public List<Product> getAllProductViews() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
        }

        return products;
    }

    public static int getTotalProducts() throws SQLException {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection con = DBConn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}
