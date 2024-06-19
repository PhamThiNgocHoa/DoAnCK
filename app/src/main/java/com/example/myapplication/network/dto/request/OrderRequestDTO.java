package com.example.myapplication.network.dto.request;


import java.io.Serializable;
import java.util.Set;

public class OrderRequestDTO implements Serializable {
    private Integer customerId;
    private double totalAmount;
    private String address;
    private String numberPhone;
    private String status;
    private Set<OrderDetailRequestDTO> orderDetails;

    public OrderRequestDTO(Integer customerId, double totalAmount, String address, String numberPhone, String status, Set<OrderDetailRequestDTO> orderDetails) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<OrderDetailRequestDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailRequestDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

