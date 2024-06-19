package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.ProductRequestDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @POST("api/product")
    Call<Integer> addProduct(@Body ProductRequestDTO product);

    @PUT("api/product/{productId}")
    Call<Void> updateProduct(@Path("productId") int productId, @Body ProductRequestDTO product);

    @DELETE("api/product/{productId}")
    Call<Void> deleteProduct(@Path("productId") int productId);

    @GET("api/product/{productId}")
    Call<ProductResponseDTO> getProduct(@Path("productId") int productId);
    @GET("api/product/list")
    Call<List<ProductResponseDTO>> getProducts();
}
