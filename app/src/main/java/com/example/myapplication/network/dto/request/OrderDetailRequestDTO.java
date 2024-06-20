package com.example.myapplication.network.dto.request;


import java.io.Serializable;

public class OrderDetailRequestDTO implements Serializable {
    private int productId;
    private int quantity;


    public OrderDetailRequestDTO(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
