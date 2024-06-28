package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.ProductActivity;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.LoginRequest;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomerService customerService;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button login = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signupRedirectText);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        TextView forgot = findViewById(R.id.forgot);

        login.setOnClickListener(view -> {
            customerService = RetrofitClient.getCustomerService();
            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();

            if (TextUtils.isEmpty(usernameText) || TextUtils.isEmpty(passwordText)) {
                if (TextUtils.isEmpty(usernameText)) {
                    username.setError("Vui lòng nhập tên đăng nhập");
                }
                if (TextUtils.isEmpty(passwordText)) {
                    password.setError("Vui lòng nhập mật khẩu");
                }
                return;
            }

            LoginRequest loginRequest = new LoginRequest(usernameText, passwordText);
            customerService.login(loginRequest).enqueue(new Callback<CustomerResponseDTO>() {
                @Override
                public void onResponse(Call<CustomerResponseDTO> call, Response<CustomerResponseDTO> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        CustomerResponseDTO customerResponseDTO = response.body();

                        // Lưu thông tin đăng nhập vào Shared Preferences
                        saveLoginInfo(customerResponseDTO);

                        // Chuyển sang màn hình ProductActivity sau khi đăng nhập thành công
                        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                        startActivity(intent);
                        finish(); // Kết thúc Activity hiện tại sau khi chuyển màn hình
                    } else {
                        username.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                        Log.e("MainActivity", "Response không thành công");
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CustomerResponseDTO> call, Throwable t) {
                    Log.e("MainActivity", "Lỗi đăng nhập: " + t.getMessage());
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            });
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });

        forgot.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
            startActivity(intent);
        });
    }

    private void saveLoginInfo(CustomerResponseDTO customerResponseDTO) {
        // Lưu thông tin đăng nhập vào Shared Preferences
        SharedPrefManager.getInstance(MainActivity.this).saveLoginInfo(
                customerResponseDTO.getUsername(),
                String.valueOf(password.getText()), // Thay thế với mã băm mật khẩu nếu có
                customerResponseDTO.getId()
        );
    }
}
