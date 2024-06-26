package com.example.myapplication.network.dto.request;


import java.io.Serializable;

public class CategoryRequestDTO implements Serializable {
    private String name;
    private String img;

    public CategoryRequestDTO(String name, String img) {
        this.name = name;
        this.img = img;
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
}
