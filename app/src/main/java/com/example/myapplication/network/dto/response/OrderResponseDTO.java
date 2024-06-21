package com.example.myapplication.network.dto.response;


import java.time.LocalDateTime;
import java.util.Set;

public class OrderResponseDTO {
    private Integer id;
    private Integer customerId;
    private String orderDate;
    private Integer totalAmount;
    private String address;
    private String numberPhone;
    private String status;
    private Set<OrderDetailResponseDTO> orderDetails;
    public OrderResponseDTO(Integer id, Integer customerId, String orderDate, Integer totalAmount, String address, String numberPhone, String status, Set<OrderDetailResponseDTO> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public OrderResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalAmount() {
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

    public Set<OrderDetailResponseDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailResponseDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
