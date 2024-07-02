package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.network.OrderService;

public class OrderSuccess extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private OrderService orderService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hoan_thanh);
        ImageView back = findViewById(R.id.back);
        TextView load = findViewById(R.id.load);
        TextView detail = findViewById(R.id.detail);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(view -> {
            Intent intent = new Intent(OrderSuccess.this, History.class);
            startActivity(intent);
        });
        load.setOnClickListener(view -> {
            Intent intent = new Intent(OrderSuccess.this, History.class);
            startActivity(intent);
        });
        detail.setOnClickListener(view -> {
            Intent intent = new Intent(OrderSuccess.this, DetailOrder.class);
            startActivity(intent);
        });

    }
}
