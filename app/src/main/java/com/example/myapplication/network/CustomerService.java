package com.example.myapplication.network;


import com.example.myapplication.network.dto.request.CustomerUpdateRequestDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.myapplication.network.dto.request.CustomerRequestDTO;
import com.example.myapplication.network.dto.request.LoginRequest;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomerService {

    @GET("api/customer/{customerId}")
    Call<CustomerResponseDTO> getCustomerById(@Path("customerId") int customerId);

    @GET("api/customer/list")
    Call<List<CustomerResponseDTO>> getCustomers();

    @POST("api/customer/login")
    Call<CustomerResponseDTO> login(@Body LoginRequest loginRequest);
    @POST("api/customer")
    Call<Integer> register(@Body CustomerRequestDTO customerRequestDTO);


    @DELETE("api/customer/{customerId}")
    Call<Void> delete(@Path("customerId") int customerId);

    @PUT("api/customer/admin/{customerId}")
    Call<Void> updateByAdmin(@Path("customerId") int customerId, @Body CustomerResponseDTO customerResponseDTO);

    @GET("api/customer/{customerId}")
    Call<CustomerResponseDTO> getCustomer(@Path("customerId") int customerId);

    @PUT("api/customer/{customerId}")
    Call<Void> updateCustomer(@Path("customerId") int customerId, @Body CustomerRequestDTO customer);

    @POST("api/customer/resetPassword/{username}")
    Call<Void> resetPassword(
            @Path("username") String username,
            @Query("resetCode") String resetCode,
            @Query("newPassword") String newPassword
    );


    @POST("api/customer/initPasswordReset/{username}")
    Call<Void> initPasswordReset(
            @Path("username") String username
    );

    @PUT("api/customer/updateByUser/{id}")
    Call<CustomerResponseDTO> updateUserClientSide(@Path("id") int id, @Body CustomerUpdateRequestDTO dto);
}



