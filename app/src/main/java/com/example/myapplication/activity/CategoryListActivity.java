package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCategoryList;
    private RecyclerView recyclerView;
    private CategoryService categoryService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category_list);
        getCategories();
    }

    private void getCategories() {
        categoryService = RetrofitClient.getCategoryService();
        categoryService.getCategories().enqueue(new Callback<List<CategoryResponseDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryResponseDTO>> call, Response<List<CategoryResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoryResponseDTO> products = response.body();
                    recyclerView = findViewById(R.id.view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterCategoryList = new CategoryAdapter((ArrayList<CategoryResponseDTO>) products);
                    recyclerView.setAdapter(adapterCategoryList);
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CategoryListActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(CategoryListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}