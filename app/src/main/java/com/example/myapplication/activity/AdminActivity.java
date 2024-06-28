package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class AdminActivity extends AppCompatActivity {
    private ConstraintLayout thongKe, khachHang, sanPham, danhMuc, donHang, banHang;
    private ConstraintLayout thongKeBtn, donHangBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        thongKe = findViewById(R.id.thongKe);
        khachHang = findViewById(R.id.khachHang);
        sanPham = findViewById(R.id.sanPham);
//        danhMuc = findViewById(R.id.danhMuc);
        donHang = findViewById(R.id.donHang);
//        banHang = findViewById(R.id.banHang);
        khachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });
        sanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        thongKeBtn = findViewById(R.id.thong_ke_btn);
        donHangBtn = findViewById(R.id.don_hang_btn);
        thongKeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, StatisticalActivity.class);
            startActivity(intent);
        });
        donHangBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, OrderListActivity.class);
            startActivity(intent);
        });
    }
}
