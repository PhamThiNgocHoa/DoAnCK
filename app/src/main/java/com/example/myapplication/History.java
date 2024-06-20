package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {
    private TextView idOrder, totalAmount, date;
    private OrderService orderService = RetrofitClient.getOrderService();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_su_don_hang);
        ImageView back = findViewById(R.id.back);
        TextView success = findViewById(R.id.success);
        TextView detail = findViewById(R.id.detail);

        idOrder = findViewById(R.id.idOrder);
        totalAmount = findViewById(R.id.totalAmount);
        date = findViewById(R.id.date);

        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
        int customerId = sharedPrefManager.getCustomerId();

        Call<List<OrderResponseDTO>> call = orderService.getOrderByCustomerId(customerId);
        call.enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful()){
                    List<OrderResponseDTO> orderList = response.body();
                    
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable t) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, OrderSuccess.class);
                startActivity(intent);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, DetailOrder.class);
                startActivity(intent);
            }
        });


    }
}
