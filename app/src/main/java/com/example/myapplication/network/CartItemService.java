package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.CartItemRequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartItemService {
    @POST("api/cartItem")
    Call<Integer> saveCartItem(@Body CartItemRequestDTO cartItemRequestDTO);
    @POST("api/cartItem")
    Call<Integer> addCartItem(@Body CartItemRequestDTO cartItemRequestDTO);
    @PUT("api/cartItem/updatequantity/{cartItemId}")
    Call<Void> updateQuantityCartItem(@Path("cartItemId") int cartItemId, @Body int quantity);
    @DELETE("api/cartItem/{cartItemId}")
    Call<Void> deleteCartItem(@Path("cartItemId") int cartItemId);
}
