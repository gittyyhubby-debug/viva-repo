package com.example.Business.billing.mapper;

import com.example.Business.billing.dto.CartItemDTO;
import com.example.persistence.model.CartItem;
import com.example.persistence.model.CashierProducts;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem item) {
        CashierProducts p = item.getProduct();
        return new CartItemDTO(p.getId(), p.getName(), p.getCategory(), p.getPrice(), item.getQuantity());
    }

    public static CartItem toModel(CartItemDTO dto) {
        CashierProducts product = new CashierProducts();
        product.setId(dto.getProductId());
        product.setName(dto.getProductName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        // Quantity in product model is stock, so set to 0 or ignore here
        CartItem item = new CartItem(product, dto.getQuantity());
        return item;
    }

    public static List<CartItemDTO> toDTOList(List<CartItem> items) {
        return items.stream().map(CartItemMapper::toDTO).collect(Collectors.toList());
    }
}
