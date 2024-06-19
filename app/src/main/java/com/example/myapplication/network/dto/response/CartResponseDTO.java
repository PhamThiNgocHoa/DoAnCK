package com.example.myapplication.network.dto.response;


import java.util.List;

public class CartResponseDTO {
    private int id;
    private int customerId;
    private float totalPrice;
    private List<CartItemResponseDTO> cartItems;

    public CartResponseDTO(int id, int customerId, float totalPrice, List<CartItemResponseDTO> cartItems) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemResponseDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
