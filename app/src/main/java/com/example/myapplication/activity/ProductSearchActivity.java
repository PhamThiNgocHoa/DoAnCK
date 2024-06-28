package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ProductActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductSearchAdapter;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSearchActivity extends AppCompatActivity {
    private ProductSearchAdapter productAdapter;
    private ProductService productService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(ProductSearchActivity.this, ProductActivity.class);
            startActivity(intent);
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productService = RetrofitClient.getProductService();

        List<ProductResponseDTO> initialProductList = new ArrayList<>();
        productAdapter = new ProductSearchAdapter(initialProductList, this); // Pass context here
        recyclerView.setAdapter(productAdapter);

        EditText searchView = findViewById(R.id.searchview);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void searchProducts(String query) {
        if (productService == null) {
            return;
        }

        Call<List<ProductResponseDTO>> call = productService.getProductByName(query);
        call.enqueue(new Callback<List<ProductResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productAdapter.updateProductList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
