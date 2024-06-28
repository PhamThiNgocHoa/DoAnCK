package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.OrderCustomerAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity implements OrderCustomerAdapter.OnOrderCustomerActionListener{
    private RecyclerView.Adapter adapterOrdersList;
    private OrderService orderService;
    private RecyclerView recyclerViewOrder;
    CustomerResponseDTO savedCustomer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_su_don_hang);
        savedCustomer = SharedPrefManager.getCustomer(getApplicationContext());
        ImageView back = findViewById(R.id.back);
        TextView success = findViewById(R.id.success);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(History.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        getOrders();

    }
    private void getOrders(){
        orderService = RetrofitClient.getOrderService();
        orderService.getOrdersByCustomerId(savedCustomer.getId()).enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderResponseDTO> products = response.body();
                    recyclerViewOrder = findViewById(R.id.view_order);
                    recyclerViewOrder.setLayoutManager(new LinearLayoutManager(History.this, LinearLayoutManager.VERTICAL, false));
                    adapterOrdersList = new OrderCustomerAdapter((ArrayList<OrderResponseDTO>) products, History.this);
                    recyclerViewOrder.setAdapter(adapterOrdersList);
                    Toast.makeText(History.this, products.get(0).getOrderDetails().size() +" la kich thuoc cua no", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(History.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable t) {
                Toast.makeText(History.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onViewDetail(OrderResponseDTO orderResponseDTO) {
        Intent intent = new Intent(History.this, Order.class);
        intent.putExtra("orderDetail", orderResponseDTO);
        startActivity(intent);
    }
}
