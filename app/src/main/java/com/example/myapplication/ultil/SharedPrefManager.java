package com.example.myapplication.ultil;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.google.gson.Gson;

public class SharedPrefManager {
    private static final String PREF_NAME = "customer_prefs";
    private static final String CUSTOMER_KEY = "customer";

    public static void saveCustomer(Context context, CustomerResponseDTO customer) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String customerJson = gson.toJson(customer);
        editor.putString(CUSTOMER_KEY, customerJson);
        editor.apply();
    }

    public static CustomerResponseDTO getCustomer(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String customerJson = sharedPreferences.getString(CUSTOMER_KEY, "");
        return gson.fromJson(customerJson, CustomerResponseDTO.class);
    }

    public static void deleteCustomer(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CUSTOMER_KEY);
        editor.apply();
    }

}
