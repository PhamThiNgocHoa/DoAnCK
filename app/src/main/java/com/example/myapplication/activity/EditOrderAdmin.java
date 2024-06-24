package com.example.myapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.R;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.OrderEditRequestDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        OrderResponseDTO order;
        Intent intent = getIntent();
        if (intent != null) {
            order = (OrderResponseDTO) intent.getSerializableExtra("order");
            statusView.setText(order.getStatus());
            fullnameView.setText(order.getReceiver());
            phoneView.setText(order.getNumberPhone());
            addressView.setText(order.getAddress());

        } else {
            order = null;

        }

        updateButton.setOnClickListener(v -> {
            String fullnameEdited = fullnameView.getText().toString();
            String addressEdited = addressView.getText().toString();
            String statusEdited = statusView.getText().toString();
            String phoneEdited = phoneView.getText().toString();

            orderService = RetrofitClient.getOrderService();
            OrderEditRequestDTO requestDTO = new OrderEditRequestDTO(fullnameEdited, addressEdited, statusEdited, phoneEdited);
            orderService.editOrder(order.getId(), requestDTO).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i("Edit thanh cong", "edit thanh cong");

                    // Hiển thị dialog thông báo thành công
                    showSuccessDialog();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("Khong goi duoc api", "khong goi duoc api", t);
                }
            });

        });
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thành công")
                .setMessage("Đã cập nhật đơn hàng thành công")
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Chuyển về trang OrderListActivity khi người dùng bấm xác nhận
                        Intent intent = new Intent(EditOrderAdmin.this, OrderListActivity.class);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
