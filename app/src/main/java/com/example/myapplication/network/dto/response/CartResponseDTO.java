package com.example.myapplication.network.dto.response;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CartResponseDTO implements Serializable {
    private int id;
    private int customerId;
    private Integer totalPrice;
    @SerializedName("cartItems")
    private List<CartItemResponseDTO> cartItems;

    public CartResponseDTO(int id, int customerId, Integer totalPrice, List<CartItemResponseDTO> cartItems) {
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemResponseDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDTO> cartItems) {
        this.cartItems = cartItems;
    }
    public Integer getPrice(){
        Integer result = 0;
        for(CartItemResponseDTO cartItemResponseDTO: cartItems){
            result+= cartItemResponseDTO.getProductId().getPrice() * cartItemResponseDTO.getQuantity();
        }
        return result;
    }
}
