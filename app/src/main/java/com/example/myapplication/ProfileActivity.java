package com.example.myapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.ProductActivity;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private TextView titleUsername, profileName, profileEmail, profilePhone,profilePassword;
    private CustomerService customerService = RetrofitClient.getCustomerService();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        Button editButton = findViewById(R.id.editButton);
        ImageView back = findViewById(R.id.back);
        titleUsername = findViewById(R.id.titleUsername);
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        profilePassword = findViewById(R.id.profilePassword);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
        String password = sharedPrefManager.getPassword();
        int customerId = sharedPrefManager.getCustomerId();
        profilePassword.setText(password);
        System.out.println(customerId);

        Call<CustomerResponseDTO> call = customerService.getCustomer(customerId);
        call.enqueue(new Callback<CustomerResponseDTO>() {
            @Override
            public void onResponse(Call<CustomerResponseDTO> call, Response<CustomerResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CustomerResponseDTO responseDTO = response.body();
                    titleUsername.setText(responseDTO.getUsername());
                    profileName.setText(responseDTO.getUsername());
                    profileEmail.setText(responseDTO.getEmail());
                    profilePhone.setText(responseDTO.getPhone());
                } else {
                    Log.e("khong tim thay", "khong tim thay");
                }
            }

            @Override
            public void onFailure(Call<CustomerResponseDTO> call, Throwable t) {
                // Xử lý khi gọi API thất bại (ví dụ: hiển thị thông báo lỗi)
                // ...
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gọi API để lấy thông tin khách hàng
                Call<CustomerResponseDTO> call = customerService.getCustomer(customerId);
                call.enqueue(new Callback<CustomerResponseDTO>() {
                    @Override
                    public void onResponse(Call<CustomerResponseDTO> call, Response<CustomerResponseDTO> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            CustomerResponseDTO responseDTO = response.body();
                            // Chuyển sang EditProfileActivity và gửi dữ liệu responseDTO
                            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                            intent.putExtra("customer", responseDTO);
                            startActivity(intent);
                        } else {
                            Log.e("ProfileActivity", "Failed to get customer data. Code: " + response.code());
                            // Xử lý khi không thành công (ví dụ: hiển thị thông báo lỗi)
                            // Toast.makeText(ProfileActivity.this, "Failed to get customer data", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<CustomerResponseDTO> call, Throwable t) {
                        Log.e("ProfileActivity", "Error fetching customer data", t);
                        // Xử lý khi gọi API thất bại (ví dụ: hiển thị thông báo lỗi)
                        // Toast.makeText(ProfileActivity.this, "Failed to fetch customer data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
