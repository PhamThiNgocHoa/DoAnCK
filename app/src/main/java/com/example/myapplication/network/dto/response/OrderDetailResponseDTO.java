package com.example.myapplication.network.dto.response;


import java.io.Serializable;

public class OrderDetailResponseDTO implements Serializable {
    private Integer id;
    private Integer orderId;
    private ProductResponseDTO productResponseDTO;
    private Integer quantity;

    public OrderDetailResponseDTO(Integer id, Integer orderId, ProductResponseDTO productResponseDTO, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productResponseDTO = productResponseDTO;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetailResponseDTO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productResponseDTO=" + productResponseDTO +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ProductResponseDTO getProductResponseDTO() {
        return productResponseDTO;
    }

    public void setProductResponseDTO(ProductResponseDTO productResponseDTO) {
        this.productResponseDTO = productResponseDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
