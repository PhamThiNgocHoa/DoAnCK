package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity implements ProductAdapter.OnProductActionListener {
    private RecyclerView.Adapter adapterCoursesList;
    private RecyclerView recyclerView;
    private ProductService productService;
    private List<ProductResponseDTO> products;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_list);
        ConstraintLayout addProductBtn = findViewById(R.id.add_product_btn);
        getProducts();


        addProductBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
    }

    private void getProducts() {
        productService = RetrofitClient.getProductService();
        productService.getProducts().enqueue(new Callback<List<ProductResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products = response.body();
                    recyclerView = findViewById(R.id.view);

                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterCoursesList = new ProductAdapter((ArrayList<ProductResponseDTO>) products, ProductListActivity.this);
                    recyclerView.setAdapter(adapterCoursesList);

                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(ProductListActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(ProductListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEdit(ProductResponseDTO productResponseDTO) {
        Intent intent = new Intent(ProductListActivity.this, EditProductActivity.class);
        intent.putExtra("product", productResponseDTO);
        startActivity(intent);
    }

    @Override
    public void onDelete(ProductResponseDTO productResponseDTO) {
        productService = RetrofitClient.getProductService();
        productService.deleteProduct(productResponseDTO.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductListActivity.this, "Xóa Thành Công " + productResponseDTO.getName(), Toast.LENGTH_SHORT).show();
                    int position = ((ArrayList<ProductResponseDTO>) products).indexOf(productResponseDTO);
                    if (position != -1) {
                        ((ArrayList<ProductResponseDTO>) products).remove(position);
                        adapterCoursesList.notifyItemRemoved(position);
                    }
                } else {
                    Toast.makeText(ProductListActivity.this, "Xóa Thất Bại " + productResponseDTO.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}