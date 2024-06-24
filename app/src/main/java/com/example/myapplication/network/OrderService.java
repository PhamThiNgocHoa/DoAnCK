package com.example.myapplication.network;

import com.example.myapplication.network.dto.request.OrderEditRequestDTO;
import com.example.myapplication.network.dto.response.MonthlyRevenueResponse;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderService {
    @GET("api/order/list")
    Call<List<OrderResponseDTO>> getOrders();

    @PUT("api/order/order/{orderId}")
    Call<Void> editOrder(@Path("orderId") int orderId, @Body OrderEditRequestDTO requestDTO);

    @DELETE("api/order/{orderId}")
    Call<Void> deleteOrder(@Path("orderId") int orderId);

    @GET("api/order/revenue")
    Call<List<MonthlyRevenueResponse>> getMonthlyRevenue();
}
