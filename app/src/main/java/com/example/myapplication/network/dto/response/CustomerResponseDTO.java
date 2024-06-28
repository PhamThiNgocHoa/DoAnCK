package com.example.myapplication.network.dto.response;


import java.io.Serializable;

public class CustomerResponseDTO implements Serializable {
    private int id;

    private String username;

    private String email;

    private String phone;
    private String fullname;
    private boolean role;
    private int cartId;

    public CustomerResponseDTO(int id, String username, String email, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public CustomerResponseDTO(int id, String username, String email, String phone, String fullname, boolean role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.role = role;
    }

    public CustomerResponseDTO(int id, String username, String email, String phone, String fullname, boolean role, int cartId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
        this.role = role;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
