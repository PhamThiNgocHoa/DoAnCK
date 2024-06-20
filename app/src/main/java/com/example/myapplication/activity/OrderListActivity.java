package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);
        getOrders();
    }

    private void getOrders() {
        orderService = RetrofitClient.getOrderService();
        orderService.getOrders().enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderResponseDTO> orders = response.body();
                    recyclerView = findViewById(R.id.view);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(OrderListActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    adapterOrderList = new OrderAdapter((ArrayList<OrderResponseDTO>) orders);
                    adapterOrderList.setOnOrderClickListener(order -> {
                        // Xử lý khi order được click
                        // Chuyển đến màn hình khác với order id
                        // Ví dụ:
                        Intent intent = new Intent(OrderListActivity.this, OrderDetailListActivity.class);
                        intent.putExtra("orderId", order.getId());
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(adapterOrderList);
                } else {
                    Log.e("OrderListActivity", "Response not successfull");
                    Toast.makeText(OrderListActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(OrderListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
