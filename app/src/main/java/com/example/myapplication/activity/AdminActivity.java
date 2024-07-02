package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.ProductActivity;
import com.example.myapplication.R;
import com.example.myapplication.ultil.SharedPrefManager;

public class AdminActivity extends AppCompatActivity {
    private ConstraintLayout khachHang, sanPham, danhMuc, banHang;
    private ConstraintLayout thongKeBtn, donHangBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        thongKeBtn = findViewById(R.id.thong_ke_btn);
        donHangBtn = findViewById(R.id.don_hang_btn);
        khachHang = findViewById(R.id.khachHang);
        sanPham = findViewById(R.id.san_pham_btn);
        danhMuc = findViewById(R.id.danh_muc_btn);
        banHang = findViewById(R.id.ban_hang_btn);

        khachHang.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CustomerListActivity.class);
            startActivity(intent);
        });
        sanPham.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
        danhMuc.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CategoryListActivity.class);
            startActivity(intent);
        });

        banHang.setOnClickListener(v -> {
            SharedPrefManager.deleteCustomer(getApplicationContext());
            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(intent);
        });


        thongKeBtn.setOnClickListener(v ->
        {
            Intent intent = new Intent(AdminActivity.this, StatisticalActivity.class);
            startActivity(intent);
        });
        donHangBtn.setOnClickListener(v ->

        {
            Intent intent = new Intent(AdminActivity.this, OrderListActivity.class);
            startActivity(intent);
        });
    }
}

