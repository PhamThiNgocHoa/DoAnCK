package com.example.myapplication.network.dto.response;




public class CartItemResponseDTO {
    private int id;
    private ProductResponseDTO productId;
    private int quantity;

    public CartItemResponseDTO(int id, ProductResponseDTO productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductResponseDTO getProductId() {
        return productId;
    }

    public void setProductId(ProductResponseDTO productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
