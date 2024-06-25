package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class AdminActivity extends AppCompatActivity {
    private ConstraintLayout thongKeBtn, donHangBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
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
