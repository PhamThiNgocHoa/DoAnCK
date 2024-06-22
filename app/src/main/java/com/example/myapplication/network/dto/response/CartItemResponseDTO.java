package com.example.myapplication.network.dto.response;

import com.example.myapplication.network.dto.request.ProductRequestDTO;

public class CartItemResponseDTO {
    private int cartId;
    private ProductRequestDTO productId;
    private int quantity;

    public CartItemResponseDTO(int cartId, ProductRequestDTO productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public ProductRequestDTO getProductId() {
        return productId;
    }

    public void setProductId(ProductRequestDTO productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
