package com.example.myapplication.network.dto.request;


import java.io.Serializable;

public class ProductRequestDTO implements Serializable {
    private int id;
    private String name;
    private String img;
    private double price;
    private int categoryId;
    private String detail;

    public ProductRequestDTO(int id, String name, String img, double price, int categoryId, String detail) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.categoryId = categoryId;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
