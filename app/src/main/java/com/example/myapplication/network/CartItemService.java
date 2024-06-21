package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.CartItemRequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CartItemService {
    @POST("api/cartItem")
    Call<Integer> saveCartItem(@Body CartItemRequestDTO cartItemRequestDTO);
}
