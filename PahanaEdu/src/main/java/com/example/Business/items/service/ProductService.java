package com.example.Business.items.service;


import com.example.Business.items.dto.ProductDTO;
import com.example.Business.items.mapper.ProductMapper;
import com.example.persistence.dao.AddProductDAO;
import com.example.persistence.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final AddProductDAO productDAO = new AddProductDAO();

    public boolean addProduct(ProductDTO product) {
        try {
            return productDAO.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ProductDTO> getAllProductViews() {
        try {
            List<Product> products = productDAO.getAllProductViews();
            List<ProductDTO> productDTOs = new ArrayList<>();
            for (Product product : products) {
                productDTOs.add(ProductMapper.toDTO(product));
            }
            return productDTOs;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}