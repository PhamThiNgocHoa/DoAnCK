package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    private ImageView detailActivityShoeIV;
    private TextView detailActivityShoeNameTv, detailActivityShoePriceTv, textView3;
    private ProductService productService;
   private ImageView back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        detailActivityShoeIV = findViewById(R.id.detailActivityShoeIV);
        detailActivityShoeNameTv = findViewById(R.id.detailActivityShoeNameTv);
        detailActivityShoePriceTv = findViewById(R.id.detailActivityShoePriceTv);
        textView3 = findViewById(R.id.textView3);
        back =  findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        int productId = getIntent().getIntExtra("id", -1);
        if (productId != -1) {
            getProductDetails(productId);
        } else {
            Toast.makeText(this, "Product ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void getProductDetails(int productId) {
        productService = RetrofitClient.getProductService();
        productService.getProduct(productId).enqueue(new Callback<ProductResponseDTO>() {
            @Override
            public void onResponse(Call<ProductResponseDTO> call, Response<ProductResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponseDTO product = response.body();
                    detailActivityShoeNameTv.setText(product.getName());
                    detailActivityShoePriceTv.setText(formatCurrency(product.getPrice()));
                    textView3.setText(product.getDetail());
                    Glide.with(DetailedActivity.this).load(product.getImg()).into(detailActivityShoeIV);
                } else {
                    Toast.makeText(DetailedActivity.this, "Failed to load product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponseDTO> call, Throwable t) {
                Toast.makeText(DetailedActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Format currency to display VND
    private String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,### VNĐ");
        return formatter.format(amount * 1000);
    }
}
