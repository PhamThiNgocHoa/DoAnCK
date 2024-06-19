package com.example.myapplication.network.impl;

import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductServiceImpl {
    private ProductService service = RetrofitClient.getProductService();
    public List<ProductResponseDTO> getLists() throws IOException {
        Call<List<ProductResponseDTO>> call = service.getProducts();
        Response<List<ProductResponseDTO>> response = call.execute();
        if (response.isSuccessful()) {
            System.out.println(response);
            return response.body();
        } else {
            throw new IOException("Error: " + response.code());
        }
    }
}
