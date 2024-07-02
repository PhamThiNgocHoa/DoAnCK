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
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.adapter.ViewPagerAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private OrderService orderService;


    private TextView processingTab, completedTab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_su_don_hang);
        ImageView back = findViewById(R.id.back);


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Đang xử lý");
                    break;
                case 1:
                    tab.setText("Hoàn thành");
                    break;
            }
        }).attach();


        // Gọi API để lấy danh sách đơn hàng đang xử lý

        back.setOnClickListener(view -> {
            Intent intent = new Intent(History.this, ProductActivity.class);
            startActivity(intent);
        });
//        success.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(History.this, OrderSuccess.class);
//                startActivity(intent);
//            }
//        });
//        detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(History.this, DetailOrder.class);
//                startActivity(intent);
//            }
//        });
    }

}