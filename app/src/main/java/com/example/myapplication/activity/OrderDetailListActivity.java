package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderDetailAdapter;
import com.example.myapplication.network.OrderDetailService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderDetailListActivity extends AppCompatActivity {
    private OrderDetailAdapter adapterOrderDetailList;
    private RecyclerView recyclerView;
    private OrderDetailService orderDetailService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang_admin);
        // Lấy intent từ activity gọi OrderDetailActivity
        Intent intent = getIntent();

        // Kiểm tra xem intent có chứa dữ liệu không
        if (intent != null) {
            // Lấy dữ liệu từ intent (ví dụ: orderId)
            int orderId = intent.getIntExtra("orderId", -1); // -1 là giá trị mặc định nếu không tìm thấy
            // Xử lý dữ liệu theo nhu cầu của bạn
            getOrderDetails(orderId);
            // Ví dụ: hiển thị thông tin chi tiết của order có orderId
        } else {
            // Xử lý khi không có intent
            Toast.makeText(this, "No data received from intent", Toast.LENGTH_SHORT).show();
        }


    }

    private void getOrderDetails(int orderId) {
        orderDetailService = RetrofitClient.getOrderDetailService();
        orderDetailService.getOrderDetails(orderId).enqueue(new Callback<List<OrderDetailResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDetailResponseDTO>> call, Response<List<OrderDetailResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderDetailResponseDTO> orderDetails = response.body();
                    recyclerView = findViewById(R.id.view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterOrderDetailList = new OrderDetailAdapter((ArrayList<OrderDetailResponseDTO>) orderDetails);
                    recyclerView.setAdapter(adapterOrderDetailList);
                } else {
                    Log.e("OrderListActivity", "Response not successfull");
                    Toast.makeText(OrderDetailListActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetailResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(OrderDetailListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
