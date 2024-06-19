package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OrderAdmin extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acitivity_order_admin);
        ImageView back = findViewById(R.id.back);
        TextView product = findViewById(R.id.product);
        TextView idOrder = findViewById(R.id.idOrder);
        TextView order = findViewById(R.id.order);
        TextView doanh_thu = findViewById(R.id.doanh_thu);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderAdmin.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderAdmin.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        idOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderAdmin.this, ChiTietDonHangAdmin.class);
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderAdmin.this, OrderAdmin.class);
                startActivity(intent);
            }
        });
        doanh_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderAdmin.this, DoanhThu.class);
                startActivity(intent);
            }
        });
    }
}
