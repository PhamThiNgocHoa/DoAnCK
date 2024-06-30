package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.adapter.ViewPagerOrderAdminAdapter;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.MonthlyRevenueResponse;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test_tab_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tabLayout = findViewById(R.id.tab_layout_test);
        viewPager = findViewById(R.id.view_pager_test);
        ViewPagerOrderAdminAdapter adapter = new ViewPagerOrderAdminAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}


