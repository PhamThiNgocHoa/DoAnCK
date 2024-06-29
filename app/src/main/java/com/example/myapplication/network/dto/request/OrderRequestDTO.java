package com.example.myapplication.network.dto.request;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class OrderRequestDTO implements Serializable {
    private Integer customerId;
    private Integer totalAmount;
    private String address;
    private String numberPhone;
    private String status;
    private String receiver;
    private List<OrderDetailRequestDTO> orderDetails;

    public OrderRequestDTO(Integer customerId, Integer totalAmount, String address, String numberPhone, String status, List<OrderDetailRequestDTO> orderDetails) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public OrderRequestDTO(Integer customerId, Integer totalAmount, String address, String numberPhone, String status, String receiver, List<OrderDetailRequestDTO> orderDetails) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.receiver = receiver;
        this.orderDetails = orderDetails;
    }

    public OrderRequestDTO() {
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

    public void setTotalAmount(Integer totalAmount) {
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

    public List<OrderDetailRequestDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailRequestDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}

