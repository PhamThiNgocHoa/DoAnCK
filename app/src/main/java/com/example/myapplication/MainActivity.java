package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.activity.CustomerListActivity;
import com.example.myapplication.adapter.CustomerAdapter;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.LoginRequest;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomerService customerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Đảm bảo EdgeToEdge được khai báo và sử dụng đúng cách
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.loginButton);
        TextView sigUp = findViewById(R.id.signupRedirectText);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        TextView forgot = findViewById(R.id.forgot);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerService = RetrofitClient.getCustomerService();
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    if (usernameText.isEmpty()) {
                        username.setError("Username is required");
                    }
                    if (passwordText.isEmpty()) {
                        password.setError("Password is required");
                    }
                    return;
                }

                LoginRequest loginRequest = new LoginRequest(usernameText, passwordText);
                customerService.login(loginRequest).enqueue(new Callback<CustomerResponseDTO>() {
                    @Override
                    public void onResponse(Call<CustomerResponseDTO> call, Response<CustomerResponseDTO> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            CustomerResponseDTO customerResponseDTO = response.body();
                            SharedPrefManager.getInstance(MainActivity.this).saveLoginInfo(
                                    customerResponseDTO.getUsername(),
                                    passwordText, // Thay thế với hash password nếu có
                                    customerResponseDTO.getId()
                            );

                            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            username.setError("Tài khoản hoặc mật khẩu sai");
                            Log.e("MainActivity", "Response not successful");
                            Toast.makeText(MainActivity.this, "Failed login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerResponseDTO> call, Throwable t) {
                        Log.e("MainActivity", "Login error: " + t.getMessage());
                        Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        sigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}