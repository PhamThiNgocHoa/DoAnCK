package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSuccess extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private OrderService orderService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hoan_thanh);
        ImageView back = findViewById(R.id.back);
//        TextView load = findViewById(R.id.load);
//        TextView detail = findViewById(R.id.detail);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo adapter và set cho RecyclerView
        historyAdapter = new HistoryAdapter(this);
        recyclerView.setAdapter(historyAdapter);
        orderService = RetrofitClient.getRetrofitInstance().create(OrderService.class);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSuccess.this, History.class);
                startActivity(intent);
            }
        });
//        load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OrderSuccess.this, History.class);
//                startActivity(intent);
//            }
//        });
//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(OrderSuccess.this, DetailOrder.class);
//                startActivity(intent);
//            }
//        });

    }
    private void fetchOrders() {
        Log.d("API_REQUEST", "Yêu cầu đơn hàng với trạng thái: " );
        Call<List<OrderResponseDTO>> call = orderService.getOrdersByStatus("Đang xử lý");
        call.enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                Log.d("API_RESPONSE", "Nhận được phản hồi từ server");
                if (response.isSuccessful()) {
                    List<OrderResponseDTO> orders = response.body();
                    if (orders != null && !orders.isEmpty()) {
                        Log.e("API_SUCCESS", "Nhận được " + orders.size() + " đơn hàng");
                        for (OrderResponseDTO order : orders) {
                            Log.d("ORDER_DETAIL", "Order: " + new Gson().toJson(order));
                        }
                        historyAdapter.setData(orders);
                    } else {
                        Log.e("API_SUCCESS", "Phản hồi thành công nhưng không có dữ liệu" + response);
                    }
                } else {
                    Log.e("API_ERROR", "Mã lỗi: " + response.code());
                    try {
                        Log.e("API_ERROR", "Nội dung lỗi: " + response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("API_ERROR", "Không thể đọc nội dung lỗi", e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<OrderResponseDTO>> call, @NonNull Throwable t) {
                Log.e("API_FAILURE", "Lỗi kết nối: " + t.getMessage(), t);
                Toast.makeText(OrderSuccess.this, "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
}}
