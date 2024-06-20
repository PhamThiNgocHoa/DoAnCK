package com.example.myapplication.network.dto.response;


public class OrderDetailResponseDTO {
    private int id;
    private int  orderId;
    private ProductResponseDTO  productId;
    private int  quantity;

    public OrderDetailResponseDTO(int id, int orderId, ProductResponseDTO productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
