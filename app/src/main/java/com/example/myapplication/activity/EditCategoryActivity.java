package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CategoryRequestDTO;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCategoryActivity extends AppCompatActivity {
    private EditText nameCategory;
    private Button buttonSave;
    private CategoryService categoryService;
    private CategoryResponseDTO category;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_category);

        nameCategory = findViewById(R.id.editCategory);
        buttonSave = findViewById(R.id.saveCategoryButton);
        ImageView backBtn;
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(EditCategoryActivity.this, CategoryListActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("category")) {
            category = (CategoryResponseDTO) intent.getSerializableExtra("category");
            if (category != null) {
                nameCategory.setText(category.getName());
            }
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category != null) {
                    category.setName(nameCategory.getText().toString());
                    updateCategory(category);
                } else {
                    Toast.makeText(EditCategoryActivity.this, "Category is not loaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateCategory(CategoryResponseDTO categoryResponseDTO) {
        categoryService = RetrofitClient.getCategoryService();
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(categoryResponseDTO.getName(), categoryResponseDTO.getImg());
        categoryService.updateCategory(categoryResponseDTO.getId(), categoryRequestDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditCategoryActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditCategoryActivity.this, CategoryListActivity.class);
                    startActivity(intent); // Close the activity
                } else {
                    Toast.makeText(EditCategoryActivity.this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(EditCategoryActivity.this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
                Toast.makeText(EditCategoryActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
