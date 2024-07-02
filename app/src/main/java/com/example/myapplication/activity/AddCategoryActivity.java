package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CategoryRequestDTO;
import com.example.myapplication.network.dto.request.ProductRequestDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryActivity extends AppCompatActivity {
    private TextView category, image;
    private Button addButton;
    private CategoryService categoryService;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);
        category = findViewById(R.id.category_name);
        image = findViewById(R.id.img_info);
        addButton = findViewById(R.id.updateButton);
        addButton.setOnClickListener(v -> {
            CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(category.getText().toString(), image.getText().toString());
            addCategory(categoryRequestDTO);
        });
    }

    private void addCategory(CategoryRequestDTO requestDTO) {
        categoryService = RetrofitClient.getCategoryService();
        categoryService.addCategory(requestDTO).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(AddCategoryActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCategoryActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                Toast.makeText(AddCategoryActivity.this, "Thêm Khong Thành Công", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
