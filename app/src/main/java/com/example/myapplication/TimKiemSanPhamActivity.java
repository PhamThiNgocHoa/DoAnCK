package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.ProductCustomerAdapter;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemSanPhamActivity extends AppCompatActivity implements ProductCustomerAdapter.OnProductCustomerActionListener {
    private RecyclerView.Adapter adapterProductList;
    private ProductService productService;
    private RecyclerView recyclerViewProduct;
    private SearchView find_Product;
    private ConstraintLayout back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timkiem_product);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> {
            Intent intent = new Intent(TimKiemSanPhamActivity.this, ProductActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        ArrayList<ProductResponseDTO> products = null;
        if (intent != null && intent.hasExtra("products")) {
            products = (ArrayList<ProductResponseDTO>) intent.getSerializableExtra("products");
        }
        if (products != null) {
            recyclerViewProduct = findViewById(R.id.view_product_find);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(TimKiemSanPhamActivity.this, 2);
            recyclerViewProduct.setLayoutManager(gridLayoutManager);
            adapterProductList = new ProductCustomerAdapter((ArrayList<ProductResponseDTO>) products, TimKiemSanPhamActivity.this);
            recyclerViewProduct.setAdapter(adapterProductList);
        }
        find_Product = findViewById(R.id.timkiem_sanpham);
        find_Product.setImeOptions(EditorInfo.IME_ACTION_SEARCH); // Set IME action to "Search"
        find_Product.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(TimKiemSanPhamActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                productService = RetrofitClient.getProductService();
                productService.getProductsByName(query).enqueue(new Callback<List<ProductResponseDTO>>() {
                    @Override
                    public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<ProductResponseDTO> products = response.body();
                            recyclerViewProduct = findViewById(R.id.view_product_find);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(TimKiemSanPhamActivity.this, 2);
                            recyclerViewProduct.setLayoutManager(gridLayoutManager);
                            adapterProductList = new ProductCustomerAdapter((ArrayList<ProductResponseDTO>) products, TimKiemSanPhamActivity.this);
                            recyclerViewProduct.setAdapter(adapterProductList);
                        } else {
                            Log.e("CoursesListActivity", "Response not successful");
                            Toast.makeText(TimKiemSanPhamActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                        Log.e("CoursesListActivity", "onFailure: ", t);
                        Toast.makeText(TimKiemSanPhamActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                find_Product.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    @Override
    public void onViewDetail(ProductResponseDTO productResponseDTO) {
        Intent intent = new Intent(TimKiemSanPhamActivity.this, DetailedActivity.class);
        intent.putExtra("product", productResponseDTO);
        startActivity(intent);
    }
}
