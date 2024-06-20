package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderDetailService {
    @GET("api/order-detail/orderId/{orderId}")
    Call<List<OrderDetailResponseDTO>> getOrderDetails(@Path("orderId") int orderId);
}
