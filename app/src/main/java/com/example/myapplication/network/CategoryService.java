package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.CategoryRequestDTO;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("api/category/list")
    Call<List<CategoryResponseDTO>> getCategories();
    @PUT("api/category/{categoryId}")
    Call<Void> updateCategory(@Path("categoryId") int categoryId, @Body CategoryRequestDTO categoryRequestDTO);
    @DELETE("api/category/{categoryId}")
    Call<Void> deleteCategory(@Path("categoryId") int categoryId);

}
