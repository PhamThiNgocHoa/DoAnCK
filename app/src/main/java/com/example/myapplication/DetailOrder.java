package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.activity.EditOrderAdmin;
import com.example.myapplication.activity.OrderDetailListActivity;
import com.example.myapplication.adapter.OrderDetailAdapter;
import com.example.myapplication.format.FormatCurrency;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingInflatedId")
public class DetailOrder extends AppCompatActivity {
    private OrderDetailAdapter adapterOrderDetailList;
    private RecyclerView recyclerView;
    private TextView priceView, fullnameView, phoneView, addressView, statusView, receiverView;
    private ConstraintLayout back;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        // Lấy intent từ activity gọi OrderDetailActivity
        Intent intent = getIntent();
        priceView = findViewById(R.id.price);
        fullnameView = findViewById(R.id.fullname);
        phoneView = findViewById(R.id.phone);
        addressView = findViewById(R.id.address);
        statusView = findViewById(R.id.status);
        receiverView = findViewById(R.id.receiver);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            Intent intent1 = new Intent(DetailOrder.this, History.class);
            startActivity(intent1);
        });

        // Kiểm tra xem intent có chứa dữ liệu không
        if (intent != null) {
            // Lấy dữ liệu từ intent (ví dụ: orderId)
            OrderResponseDTO order = (OrderResponseDTO) intent.getSerializableExtra("order");
            priceView.setText(FormatCurrency.formatCurrency(order.getTotalAmount()));
            phoneView.setText(order.getNumberPhone());
            fullnameView.setText(order.getCustomerDTO().getFullname());
            addressView.setText(order.getAddress());
            statusView.setText(order.getStatus());
            receiverView.setText(order.getReceiver());

            // Xử lý dữ liệu theo nhu cầu của bạn
            getOrderDetails(order);
            // Ví dụ: hiển thị thông tin chi tiết của order có orderId
        } else {
            // Xử lý khi không có intent
            Toast.makeText(this, "No data received from intent", Toast.LENGTH_SHORT).show();
        }


    }

    private void getOrderDetails(OrderResponseDTO order) {
        List<OrderDetailResponseDTO> orderDetails = order.getOrderDetails();
        Log.e("OrderDetail", "OrderDetail" + orderDetails);
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailOrder.this, LinearLayoutManager.VERTICAL, false));
        adapterOrderDetailList = new OrderDetailAdapter((ArrayList<OrderDetailResponseDTO>) orderDetails);
        recyclerView.setAdapter(adapterOrderDetailList);

    }
}
