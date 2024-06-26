package com.example.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //    private static final String BASE_URL = "http://192.168.1.85:8080/";
    private static final String BASE_URL = "http://192.168.1.17:8080/";
    //    private static final String BASE_URL = "http://192.168.1.2:8080/";
    private static volatile Retrofit retrofit;

    //192.168.1.2
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static CustomerService getCustomerService() {
        return getRetrofitInstance().create(CustomerService.class);
    }

    public static ProductService getProductService() {
        return getRetrofitInstance().create(ProductService.class);
    }

    public static CartItemService getCartItemService() {
        return getRetrofitInstance().create(CartItemService.class);
    }

    public static CartService getCartService() {
        return getRetrofitInstance().create(CartService.class);
    }

    public static CategoryService getCategoryService() {
        return getRetrofitInstance().create(CategoryService.class);
    }

    public static OrderService getOrderService() {
        return getRetrofitInstance().create(OrderService.class);
    }

    public static OrderDetailService getOrderDetailService() {
        return getRetrofitInstance().create(OrderDetailService.class);
    }

}