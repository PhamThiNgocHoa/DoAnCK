package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {
    private EditText usernameEditText;
    private Button sentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        usernameEditText = findViewById(R.id.username);
        sentButton = findViewById(R.id.sentButton);

        sentButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(ForgotPassword.this, "Please enter username", Toast.LENGTH_SHORT).show();
                return;
            }

            // Gọi phương thức initPasswordReset từ Retrofit Service
            CustomerService service = RetrofitClient.getCustomerService();
            Call<Void> call = service.initPasswordReset(username);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Check email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassword.this, EnterCode.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgotPassword.this, "Failed to send password reset request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ForgotPassword.this, "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}
