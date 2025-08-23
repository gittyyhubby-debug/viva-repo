package com.example.persistence.dao;

import com.example.Business.items.dto.ProductDTO;
import com.example.persistence.model.Product;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddProductDAOTest {

    private static AddProductDAO dao;

    @BeforeAll
    static void setup() {
        dao = new AddProductDAO();
    }

    @Test
    @Order(1)
    void testAddProduct() {
        ProductDTO productDTO = new ProductDTO(
                "JUnit Test Product",
                "Description for test product",
                "Test Category",
                5,
                9.99
        );

        try {
            boolean result = dao.addProduct(productDTO);
            assertTrue(result, "Product should be added successfully");
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }


    @Test
    @Order(2)
    void testGetAllProductViews() {
        try {
            List<Product> products = dao.getAllProductViews();
            assertNotNull(products, "Product list should not be null");
            assertTrue(products.size() > 0, "Product list should contain at least one product");
            // Optionally verify that the product we inserted exists
            boolean found = products.stream()
                    .anyMatch(p -> "JUnit Test Product".equals(p.getName()));
            assertTrue(found, "Inserted test product should be in the list");
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }

    @Test
    @Order(3)
    void testGetTotalProducts() {
        try {
            int total = AddProductDAO.getTotalProducts();
            assertTrue(total > 0, "Total products count should be greater than zero");
        } catch (SQLException e) {
            fail("SQLException thrown: " + e.getMessage());
        }
    }
}
