package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CustomerService {

    @GET("api/customer/{customerId}")
    Call<CustomerResponseDTO> getCustomerById(@Path("customerId") int customerId);

}
