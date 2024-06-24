package com.example.myapplication.network.dto.response;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class OrderResponseDTO implements Serializable {
    private Integer id;
    private CustomerResponseDTO customerDTO;
    private String orderDate;
    private Integer totalAmount;
    private String address;
    private String numberPhone;
    private String status;
    private String receiver;
    private List<OrderDetailResponseDTO> orderDetails;

    public OrderResponseDTO(Integer id, CustomerResponseDTO customerDTO, String orderDate, Integer totalAmount, String address, String numberPhone, String status, String receiver, List<OrderDetailResponseDTO> orderDetails) {
        this.id = id;
        this.customerDTO = customerDTO;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.address = address;
        this.numberPhone = numberPhone;
        this.status = status;
        this.receiver = receiver;
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "id=" + id +
                ", customerDTO=" + customerDTO +
                ", orderDate='" + orderDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", address='" + address + '\'' +
                ", numberPhone='" + numberPhone + '\'' +
                ", status='" + status + '\'' +
                ", receiver='" + receiver + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerResponseDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerResponseDTO customerDTO) {
        this.customerDTO = customerDTO;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<OrderDetailResponseDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailResponseDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
