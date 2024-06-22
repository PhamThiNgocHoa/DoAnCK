package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CustomerRequestDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCustomerActivity extends AppCompatActivity {
    private EditText editTextPhone, editTextEmail;
    private Button buttonSave;
    private CustomerService customerService;
    private CustomerResponseDTO customer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_customer);

        editTextPhone = findViewById(R.id.editPhone);
        editTextEmail = findViewById(R.id.editEmail);
        buttonSave = findViewById(R.id.saveButton);

        // Get the passed customer data
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("customer")) {
            customer = (CustomerResponseDTO) intent.getSerializableExtra("customer");
            if (customer != null) {
                // Pre-populate the fields with customer data
                editTextPhone.setText(customer.getPhone());
                editTextEmail.setText(customer.getEmail());
            }
        }

        customerService = RetrofitClient.getCustomerService();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer.setPhone(editTextPhone.getText().toString());
                customer.setEmail(editTextEmail.getText().toString());
                updateCustomer(customer);
            }
        });
    }

    private void updateCustomer(CustomerResponseDTO customer) {
        customerService.updateByAdmin(customer.getId(), customer).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditCustomerActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    Toast.makeText(EditCustomerActivity.this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(EditCustomerActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
