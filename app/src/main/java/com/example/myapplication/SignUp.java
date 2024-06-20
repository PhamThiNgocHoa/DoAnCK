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

import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CustomerRequestDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private CustomerService customerService;
    EditText username, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button sigUp = findViewById(R.id.buttonsignup);
        TextView login  = findViewById(R.id.loginRedirectText);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
        username = findViewById(R.id.usernamesignup);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phonesignup);
        password = findViewById(R.id.passwordsignup);
        sigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerService = RetrofitClient.getCustomerService();
                String usernameInput = username.getText().toString();
                String emailInput = email.getText().toString();
                String phoneInput = phone.getText().toString();
                String passwordInput = password.getText().toString();
                CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO(usernameInput, emailInput, passwordInput, phoneInput);
                customerService.register(customerRequestDTO).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if(response.body() != -1){
                                Toast.makeText(SignUp.this, " Đăng Kí Thành Công  ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                username.setError("Tài khoản đã tồn tại ");
                                Log.e("MainActivity", "Response not successful");
                                Toast.makeText(SignUp.this, "Failed login", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            username.setError("Tài khoản hoặc mật khẩu sai");
                            Log.e("MainActivity", "Response not successful");
                            Toast.makeText(SignUp.this, "Failed login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });
    }
}