package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.OrderItemAdapter;
import com.example.myapplication.adapter.ProductCustomerAdapter;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;

public class Order extends AppCompatActivity {
    private RecyclerView recyclerViewOrderItem;
    private RecyclerView.Adapter adapterOrderItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        ImageView back = findViewById(R.id.back);
        Button xacNhan = findViewById(R.id.xacNhan);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Order.this, History.class);
                startActivity(intent);
            }
        });
        TextView orderId = findViewById(R.id.orderId);
        TextView orderDate = findViewById(R.id.OrderDate);
        TextView price_now = findViewById(R.id.price_now);
        TextView price = findViewById(R.id.price_total);
        TextView hoTen = findViewById(R.id.hoVaTen);
        TextView phone = findViewById(R.id.phoneOrder);
        TextView address = findViewById(R.id.addressOrder);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("orderDetail")) {
            OrderResponseDTO orderResponseDTO = (OrderResponseDTO) intent.getSerializableExtra("orderDetail");
            if(orderResponseDTO != null){
                Toast.makeText(Order.this, "Da vao duoc", Toast.LENGTH_SHORT).show();
                orderId.setText(String.valueOf(orderResponseDTO.getId()));
                orderDate.setText(orderResponseDTO.getOrderDate().toString());
                price_now.setText(String.valueOf(orderResponseDTO.getTotalAmount()));
                price.setText(String.valueOf(orderResponseDTO.getTotalAmount()));
                phone.setText(orderResponseDTO.getNumberPhone());
                address.setText(orderResponseDTO.getAddress());
                recyclerViewOrderItem = findViewById(R.id.view_order_item);
                recyclerViewOrderItem.setLayoutManager(new LinearLayoutManager(Order.this, LinearLayoutManager.VERTICAL, false));
                adapterOrderItem = new OrderItemAdapter((ArrayList<OrderDetailResponseDTO>) orderResponseDTO.getOrderDetails());
                recyclerViewOrderItem.setAdapter(adapterOrderItem);
            }else {
                Toast.makeText(Order.this, "Loi order = null", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
