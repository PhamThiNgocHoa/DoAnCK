package com.example.myapplication.network.dto.request;


import java.io.Serializable;

public class ProductRequestDTO implements Serializable {
    private String name;
    private String img;
    private int price;
    private String categoryName;
    private String detail;

    public ProductRequestDTO(String name, String img, int price, String categoryName, String detail) {
        this.name = name;
        this.img = img;
        this.price = price;
        this.categoryName = categoryName;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
