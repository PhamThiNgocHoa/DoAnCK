package com.example.myapplication.network.dto.request;


public class OrderEditRequestDTO {
    private String fullname;
    private String address;
    private String status;
    private String phone;

    public OrderEditRequestDTO(String fullname, String address, String status, String phone) {
        this.fullname = fullname;
        this.address = address;
        this.status = status;
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
