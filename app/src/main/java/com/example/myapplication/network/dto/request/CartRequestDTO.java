package com.example.myapplication.network.dto.request;


import java.io.Serializable;

public class CartRequestDTO implements Serializable {
    private int customerId;

    public CartRequestDTO(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
