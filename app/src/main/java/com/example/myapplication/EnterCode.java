package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//
//public class EnterCode extends AppCompatActivity {
//
//    private EditText codeEditText;
//    private EditText newPassEditText;
//    private String username;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_enter_code);
//
//        codeEditText = findViewById(R.id.code);
//        newPassEditText = findViewById(R.id.newPass);
//        Button sentButton = findViewById(R.id.sentButton);
//
//        // Lấy dữ liệu từ Intent
//        Intent intent = getIntent();
//        if (intent != null) {
//            username = intent.getStringExtra("username");
//        }
//
//        sentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String code = codeEditText.getText().toString();
//                String newPassword = newPassEditText.getText().toString();
//
//                CustomerService customerService = RetrofitClient.getRetrofitInstance().create(CustomerService.class);
//                customerService.resetPassword(username, code, newPassword).enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(EnterCode.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(EnterCode.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(EnterCode.this, "Failed to update password", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Toast.makeText(EnterCode.this, "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }
//}
public class EnterCode extends AppCompatActivity {

    private EditText codeEditText;
    private EditText newPassEditText;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        codeEditText = findViewById(R.id.code);
        newPassEditText = findViewById(R.id.newPass);
        Button sentButton = findViewById(R.id.sentButton);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
        }

        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeEditText.getText().toString();
                String newPassword = newPassEditText.getText().toString();

                CustomerService customerService = RetrofitClient.getRetrofitInstance().create(CustomerService.class);
                customerService.resetPassword(username, code, newPassword).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EnterCode.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EnterCode.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EnterCode.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EnterCode.this, "Network error. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
