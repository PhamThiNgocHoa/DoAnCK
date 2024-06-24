package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.MonthlyRevenueResponse;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {
    private OrderAdapter adapterOrderList;
    private RecyclerView recyclerView;
    private OrderService orderService;
    private CustomerService customerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);
        getOrders();
    }

    private void getOrders() {
        recyclerView = findViewById(R.id.view);

        orderService = RetrofitClient.getOrderService();
        orderService.getOrders().enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderResponseDTO> orders = response.body();
                    setupRecyclerView(orders);
                } else {
                    Log.e("Response null", "Response null");
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable throwable) {
                handleOrderLoadError(throwable);
            }
        });

    }


    private void setupRecyclerView(List<OrderResponseDTO> orders) {
        recyclerView = findViewById(R.id.view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderListActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapterOrderList = new OrderAdapter((ArrayList<OrderResponseDTO>) orders);
        adapterOrderList.setOnOrderClickListener(this::handleOrderClick);
        recyclerView.setAdapter(adapterOrderList);
    }

    private void handleOrderClick(OrderResponseDTO order) {
        Intent intent = new Intent(OrderListActivity.this, OrderDetailListActivity.class);
        intent.putExtra("order", order); // Đối tượng order là một instance của OrderResponseDTO
        startActivity(intent);
    }


    private void handleOrderLoadError(Throwable throwable) {
        Log.e("OrderListActivity", "Response not successful" + throwable.getMessage());
        Toast.makeText(OrderListActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
    }


}
