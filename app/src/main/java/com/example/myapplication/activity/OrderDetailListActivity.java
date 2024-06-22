package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.OrderDetailAdapter;
import com.example.myapplication.format.FormatCurrency;
import com.example.myapplication.network.OrderDetailService;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailListActivity extends AppCompatActivity {
    private OrderDetailAdapter adapterOrderDetailList;
    private RecyclerView recyclerView;
    private OrderService orderService;
    private OrderDetailService orderDetailService;
    private TextView priceView, fullnameView, phoneView, addressView, statusView, receiverView;
    private Button updateButton;
    private ImageButton deleteButton;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang_admin);
        // Lấy intent từ activity gọi OrderDetailActivity
        Intent intent = getIntent();
        priceView = findViewById(R.id.price);
        fullnameView = findViewById(R.id.fullname);
        phoneView = findViewById(R.id.phone);
        addressView = findViewById(R.id.address);
        statusView = findViewById(R.id.status);
        receiverView = findViewById(R.id.receiver);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.delete_btn);

        // Kiểm tra xem intent có chứa dữ liệu không
        if (intent != null) {
            // Lấy dữ liệu từ intent (ví dụ: orderId)
            int orderId = intent.getIntExtra("orderId", -1); // -1 là giá trị mặc định nếu không tìm thấy
            int totalPrice = intent.getIntExtra("totalPrice", -1);
            String phone = intent.getStringExtra("phone");
            String address = intent.getStringExtra("address");
            String fullname = intent.getStringExtra("fullname");
            String status = intent.getStringExtra("status");
            String receiver = intent.getStringExtra("receiver");

            priceView.setText(FormatCurrency.formatCurrency(totalPrice));
            phoneView.setText(phone);
            fullnameView.setText(fullname);
            addressView.setText(address);
            statusView.setText(status);
            receiverView.setText(receiver);

            updateButton.setOnClickListener(v -> {
                Intent intent1 = new Intent(OrderDetailListActivity.this, EditOrderAdmin.class);
                intent1.putExtra("phone", phone);
                intent1.putExtra("address", address);
                intent1.putExtra("receiver", receiver);
                intent1.putExtra("status", status);
                intent1.putExtra("orderId", orderId);
                startActivity(intent1);
            });

            deleteButton.setOnClickListener(v -> {
                showDialogDelete(orderId);
            });
            // Xử lý dữ liệu theo nhu cầu của bạn
            getOrderDetails(orderId);
            // Ví dụ: hiển thị thông tin chi tiết của order có orderId
        } else {
            // Xử lý khi không có intent
            Toast.makeText(this, "No data received from intent", Toast.LENGTH_SHORT).show();
        }


    }

    private void getOrderDetails(int orderId) {
        orderDetailService = RetrofitClient.getOrderDetailService();
        orderDetailService.getOrderDetails(orderId).enqueue(new Callback<List<OrderDetailResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderDetailResponseDTO>> call, Response<List<OrderDetailResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderDetailResponseDTO> orderDetails = response.body();
                    recyclerView = findViewById(R.id.view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderDetailListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterOrderDetailList = new OrderDetailAdapter((ArrayList<OrderDetailResponseDTO>) orderDetails);
                    recyclerView.setAdapter(adapterOrderDetailList);
                } else {
                    Log.e("OrderListActivity", "Response not successfull");
                    Toast.makeText(OrderDetailListActivity.this, "Failed to load orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<OrderDetailResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(OrderDetailListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogDelete(int orderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo").setMessage("Bạn muốn xóa đơn hàng này").setPositiveButton("Xác nhận", (dialog, which) -> {
            orderService = RetrofitClient.getOrderService();
            orderService.deleteOrder(orderId).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i("Delete thanh cong", "Delete thanh cong");
                    showSuccessDialog();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("Khong goi duoc api", "khong goi duoc api", t);
                }
            });
        }).setCancelable(true).show();
    }

    private void showSuccessDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Thành công").setMessage("Đã xóa đơn hàng thành công").setPositiveButton("Xác nhận", (dialog, which) -> {
            // Chuyển về trang OrderListActivity khi người dùng bấm xác nhận
            Intent intent = new Intent(OrderDetailListActivity.this, OrderListActivity.class);
            startActivity(intent);
        }).setCancelable(false).show();
    }
}

