package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrderService {
    @GET("api/order/list")
    Call<List<OrderResponseDTO>> getOrders();
}
