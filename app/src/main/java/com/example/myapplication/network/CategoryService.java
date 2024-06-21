package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.CategoryResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("api/category/list")
    Call<List<CategoryResponseDTO>> getCategories();

}
