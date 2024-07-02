package com.example.myapplication.network;

import com.example.myapplication.network.dto.response.UrlResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImageUploadService {
    @POST("api/cloudinary/upload")
    @Multipart
    Call<UrlResponse> uploadImage(@Part MultipartBody.Part file);



}
