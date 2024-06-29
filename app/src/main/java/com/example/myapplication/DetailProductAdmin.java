package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.AdminActivity;

public class DetailProductAdmin extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product_admin);
        ImageView back = findViewById(R.id.back);
        Button update = findViewById(R.id.detailActivityAddToCartBtn);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(DetailProductAdmin.this, AdminActivity.class);
            startActivity(intent);
        });
        update.setOnClickListener(view -> {
            Intent intent = new Intent(DetailProductAdmin.this, AdminActivity.class);
            startActivity(intent);
        });

    }
}
