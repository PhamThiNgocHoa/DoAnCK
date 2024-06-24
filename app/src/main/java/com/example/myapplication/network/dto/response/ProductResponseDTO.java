package com.example.myapplication.network.dto.response;


import java.io.Serializable;

public class ProductResponseDTO implements Serializable {
    private int id;
    private String name;
    private String img;
    private Integer price;
    private String categoryName;
    private String detail;

    public ProductResponseDTO(int id, String name, String img, int price, String categoryName, String detail) {

        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.categoryName = categoryName;
        this.detail = detail;

    }

    public ProductResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getPrice() {

        return price;
    }

    public void setPrice(Integer price) {
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

    @Override
    public String toString() {
        return "ProductResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

}
