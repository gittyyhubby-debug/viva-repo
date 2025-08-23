package com.example.persistence.dao;

import com.example.persistence.model.CashierProducts;
import com.example.util.DBConn;
import java.sql.*;
import java.util.*;

public class ProductsDAO {

    public List<CashierProducts> getAllProducts() throws SQLException {
        List<CashierProducts> list = new ArrayList<>();
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CashierProducts p = new CashierProducts();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setCategory(rs.getString("category"));
                p.setQuantity(rs.getInt("quantity"));
                list.add(p);
            }
        }
        return list;
    }

    public CashierProducts getProductById(int id) throws SQLException {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CashierProducts p = new CashierProducts();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setCategory(rs.getString("category"));
                p.setQuantity(rs.getInt("quantity"));
                return p;
            }
        }
        return null;
    }

    public void insertProduct(CashierProducts p) throws SQLException {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO products (name, description, price, category, quantity) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getQuantity());
            ps.executeUpdate();
        }
    }

    public void updateProduct(CashierProducts p) throws SQLException {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE products SET name=?, description=?, price=?, category=?, quantity=? WHERE id=?")) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getQuantity());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public boolean updateProductQuantity(int productId, int newQuantity) throws SQLException {
        String sql = "UPDATE products SET quantity = ? WHERE id = ?";
        try (Connection conn = DBConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }
    }

}
