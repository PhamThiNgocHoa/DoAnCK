package com.example.myapplication.network.dto.response;


import java.time.LocalDateTime;
import java.util.Set;

public class OrderResponseDTO {
    private Integer id;
    private CustomerResponseDTO customerId;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String address;
    private String numberPhone;
    private String status;
    private Set<OrderDetailResponseDTO> orderDetails;

    public OrderResponseDTO(Integer id, CustomerResponseDTO customerId, LocalDateTime orderDate, double totalAmount, String address, String numberPhone, String status, Set<OrderDetailResponseDTO> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerResponseDTO getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerResponseDTO customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
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

    public Set<OrderDetailResponseDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailResponseDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
