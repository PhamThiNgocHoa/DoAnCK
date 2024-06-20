package com.example.myapplication.network.impl;

import com.example.myapplication.network.OrderService;

import retrofit2.Call;
import retrofit2.Response;

import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.io.IOException;
import java.util.List;


public class OrderSeviceImpl {
    private OrderService service = RetrofitClient.getOrderService();

    public List<OrderResponseDTO> getOrders() throws IOException {
        Call<List<OrderResponseDTO>> call = service.getOrders();
        Response<List<OrderResponseDTO>> response = call.execute();
        if (response.isSuccessful()) {
            System.out.println(response);
            return response.body();
        } else {
            throw new IOException("Error: " + response.code());
        }

    }

    public static void main(String[] args) throws IOException {
        OrderSeviceImpl orderSevice = new OrderSeviceImpl();
        orderSevice.getOrders();
    }
}
