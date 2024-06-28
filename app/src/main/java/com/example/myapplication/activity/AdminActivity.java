package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class AdminActivity extends AppCompatActivity {
    private ConstraintLayout khachHang, sanPham, danhMuc, banHang;
    private ConstraintLayout thongKeBtn, donHangBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        thongKeBtn = findViewById(R.id.thong_ke_btn);
        donHangBtn = findViewById(R.id.don_hang_btn);
        khachHang = findViewById(R.id.khachHang);
//        sanPham = findViewById(R.id.sanPham);
//        danhMuc = findViewById(R.id.danhMuc);

//        banHang = findViewById(R.id.banHang);
        khachHang.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, CustomerListActivity.class);
            startActivity(intent);
        });
        sanPham.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
        thongKeBtn = findViewById(R.id.thong_ke_btn);
        donHangBtn = findViewById(R.id.don_hang_btn);
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

