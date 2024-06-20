package com.example.myapplication.network;


import com.example.myapplication.network.dto.request.OrderRequestDTO;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {
    @POST("api/order")
    Call<Integer> saveOrder(@Body OrderRequestDTO orderRequestDTO);

    @GET("api/order/customer/{customerId}")
    Call<List<OrderResponseDTO>> getOrderByCustomerId(@Path("customerId") int customerId);

    @GET("api/order/list")
    Call<List<OrderResponseDTO>> getAllOrders();
}
