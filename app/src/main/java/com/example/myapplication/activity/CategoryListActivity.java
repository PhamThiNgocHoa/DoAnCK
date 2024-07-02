package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CategoryRequestDTO;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryListener {
    private RecyclerView.Adapter adapterCategoryList;
    private RecyclerView recyclerView;
    private CategoryService categoryService;
    List<CategoryResponseDTO> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category_list);
        ConstraintLayout back = findViewById(R.id.back_admin);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryListActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        ConstraintLayout addCategoryButton = findViewById(R.id.add_category);
        addCategoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryListActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });
        getCategories();

    }
    private void getCategories()  {
        categoryService  = RetrofitClient.getCategoryService();
        categoryService.getCategories().enqueue(new Callback<List<CategoryResponseDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryResponseDTO>> call, Response<List<CategoryResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products = response.body();
                    recyclerView = findViewById(R.id.view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterCategoryList = new CategoryAdapter((ArrayList<CategoryResponseDTO>) products, CategoryListActivity.this);
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

    @Override
    public void onEdit(CategoryResponseDTO categoryResponseDTO) {
        Intent intent = new Intent(CategoryListActivity.this, EditCategoryActivity.class);
        intent.putExtra("category", categoryResponseDTO);
        startActivity(intent);
    }

    @Override
    public void onDelete(CategoryResponseDTO categoryResponseDTO) {
        categoryService = RetrofitClient.getCategoryService();
        categoryService.deleteCategory(categoryResponseDTO.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CategoryListActivity.this, "Xóa Thành Công " + categoryResponseDTO.getName(), Toast.LENGTH_SHORT).show();
                    int position = ((ArrayList<CategoryResponseDTO>) products).indexOf(categoryResponseDTO);
                    if (position != -1) {
                        ((ArrayList<CategoryResponseDTO>) products).remove(position);
                        adapterCategoryList.notifyItemRemoved(position);
                    }
                } else {
                    Toast.makeText(CategoryListActivity.this, "Xóa Thất Bại " + categoryResponseDTO.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}