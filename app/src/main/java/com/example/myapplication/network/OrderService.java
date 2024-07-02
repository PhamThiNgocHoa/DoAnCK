package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrderService {
    @GET("api/order/{status}")
    Call<List<OrderResponseDTO>> getOrdersByStatus(@Path("status") String status);
}
