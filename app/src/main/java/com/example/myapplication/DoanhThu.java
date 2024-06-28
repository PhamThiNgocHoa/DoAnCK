package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.AdminActivity;

public class DoanhThu extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.actitvity_doanh_thu);
        ImageView back = findViewById(R.id.back);
        TextView product = findViewById(R.id.product);
        TextView order = findViewById(R.id.order);
        TextView doanh_thu = findViewById(R.id.doanh_thu);

        back.setOnClickListener(view -> {
            Intent intent = new Intent(DoanhThu.this, AdminActivity.class);
            startActivity(intent);
        });
        product.setOnClickListener(view -> {
            Intent intent = new Intent(DoanhThu.this, AdminActivity.class);
            startActivity(intent);
        });

        order.setOnClickListener(view -> {
            Intent intent = new Intent(DoanhThu.this, OrderAdmin.class);
            startActivity(intent);
        });
        doanh_thu.setOnClickListener(view -> {
            Intent intent = new Intent(DoanhThu.this, DoanhThu.class);
            startActivity(intent);
        });
    }
}
