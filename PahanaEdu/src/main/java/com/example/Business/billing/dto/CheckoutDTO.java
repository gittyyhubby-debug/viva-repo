package com.example.Business.billing.dto;



import java.util.List;

public class CheckoutDTO {
    private String customerName;
    private String customerPhone;
    private List<CartItemDTO> cartItems;


    public CheckoutDTO() {}

    public CheckoutDTO(String customerName, String customerPhone, List<CartItemDTO> cartItems) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.cartItems = cartItems;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
