package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;

public class EditOrderAdmin extends AppCompatActivity {
    private TextView statusView, fullnameView, phoneView, addressView;
    private Button updateButton;
    private OrderService orderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_order);
        statusView = findViewById(R.id.status_info);
        fullnameView = findViewById(R.id.fullname_info);
        phoneView = findViewById(R.id.phone_info);
        addressView = findViewById(R.id.address_info);
        updateButton = findViewById(R.id.updateButton);
        int orderId;

        Intent intent = getIntent();
        if (intent != null) {
            statusView.setText(intent.getStringExtra("status"));
            fullnameView.setText(intent.getStringExtra("fullname"));
            phoneView.setText(intent.getStringExtra("phone"));
            addressView.setText(intent.getStringExtra("address"));
            orderId = intent.getIntExtra("orderId", -1);
        }

        updateButton.setOnClickListener(v -> {
            String fullnameEdited = (String) fullnameView.getText();
            String addressEdited = (String) addressView.getText();
            String statusEdited = (String) statusView.getText();
            String phoneEdited = (String) phoneView.getText();

            orderService = RetrofitClient.getOrderService();


        });

    }
}
