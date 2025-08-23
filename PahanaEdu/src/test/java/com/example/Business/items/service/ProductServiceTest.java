package com.example.Business.items.service;

import com.example.Business.items.dto.ProductDTO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    private static ProductService productService;

    @BeforeAll
    static void setup() {
        productService = new ProductService();
    }

    @Test
    @Order(1)
    void testAddProduct() {
        ProductDTO productDTO = new ProductDTO(
                "JUnit Service Product",
                "Description for service test",
                "Service Category",
                10,
                19.99
        );

        boolean result = productService.addProduct(productDTO);
        assertTrue(result, "Product should be added successfully");
    }

    @Test
    @Order(2)
    void testGetAllProductViews() {
        List<ProductDTO> productList = productService.getAllProductViews();
        assertNotNull(productList, "Product list should not be null");
        assertFalse(productList.isEmpty(), "Product list should not be empty");

        boolean found = productList.stream()
                .anyMatch(p -> "JUnit Service Product".equals(p.getName()));
        assertTrue(found, "Previously added product should be in the list");
    }
}
