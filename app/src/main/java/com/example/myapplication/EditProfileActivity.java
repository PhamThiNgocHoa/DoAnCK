package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CustomerRequestDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPhoneNumber, editPassword;
    private CustomerService customerService = RetrofitClient.getCustomerService();
    private CustomerRequestDTO customerRequestDTO;
    private Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        Button saveButton = findViewById(R.id.saveButton);
        ImageView back = findViewById(R.id.back);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editPassword = findViewById(R.id.editPassword);
        Intent intent = getIntent();
        if (intent != null) {
            CustomerResponseDTO customer = (CustomerResponseDTO) intent.getSerializableExtra("customer");
            editName.setText(customer.getUsername());
            editEmail.setText(customer.getEmail());
            editPhoneNumber.setText(customer.getPhone());

        }
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(this);
        int customerId = sharedPrefManager.getCustomerId();
        editPassword.setText(sharedPrefManager.getPassword());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = editEmail.getText().toString().trim().toLowerCase();
                String newName = editName.getText().toString().trim().toLowerCase();
                String newPhoneNumber = editPhoneNumber.getText().toString().trim().toLowerCase();
                String newPassword = editPassword.getText().toString().trim().toLowerCase();
                customerRequestDTO = new CustomerRequestDTO(newName, newEmail, newPassword, newPhoneNumber);

                if (TextUtils.isEmpty(newEmail) &&
                        TextUtils.isEmpty(newName)&&
                        TextUtils.isEmpty(newPhoneNumber)&&
                        TextUtils.isEmpty(newPassword)){
                    Toast.makeText(EditProfileActivity.this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newName)){
                    Toast.makeText(EditProfileActivity.this, "You have not entered a user name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newEmail)){
                    Toast.makeText(EditProfileActivity.this, "You have not entered an email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                    Toast.makeText(EditProfileActivity.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newPhoneNumber)){
                    Toast.makeText(EditProfileActivity.this, "You have not entered a phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newPassword)){
                    Toast.makeText(EditProfileActivity.this, "You have not entered a password", Toast.LENGTH_SHORT).show();
                    return;
                }if (newPassword.length() < 6){
                    Toast.makeText(EditProfileActivity.this, "Password length must be greater than 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                Call<Void> call = customerService.updateCustomer(customerId, customerRequestDTO);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            sharedPrefManager.setPassword(newPassword);
                        } else {
                            // Handle the error response
                            // You can show a Toast or a dialog to inform the user
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle the failure response
                        // You can show a Toast or a dialog to inform the user
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });
    }
}