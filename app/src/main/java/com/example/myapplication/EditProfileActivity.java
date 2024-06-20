package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPhoneNumber, editPassword;
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
        editPassword.setText(sharedPrefManager.getPassword());


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}
