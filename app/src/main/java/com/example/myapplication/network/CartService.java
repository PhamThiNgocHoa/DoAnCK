package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.CartRequestDTO;
import com.example.myapplication.network.dto.response.CartResponseDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {
    @POST("api/cart")
    Call<Integer> saveCart(@Body CartRequestDTO cartRequestDTO);

    @GET("api/cart/{customerId}")
    Call<CartResponseDTO> getCartByCustomerId(@Path("customerId") int customerId);
    @POST("api/cart")
    Call<Integer> addCart(@Body CartRequestDTO cartRequestDTO);
    @GET("api/cart/{customerId}")
    Call<CartResponseDTO> getCart(@Path("customerId") int customerId);
}
