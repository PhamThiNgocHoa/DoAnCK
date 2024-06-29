package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.activity.AdminActivity;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ImageView back = findViewById(R.id.back);
        Button them = findViewById(R.id.updateButton);
            back.setOnClickListener(view -> {
                Intent intent = new Intent(Update.this, AdminActivity.class);
                startActivity(intent);
            });
            them.setOnClickListener(view -> {
                Intent intent = new Intent(Update.this, AdminActivity.class);
                startActivity(intent);
            });

    }
}