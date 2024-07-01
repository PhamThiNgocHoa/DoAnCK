package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.ProductRequestDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    private TextView category, name, price, image, detail;
    private Button addButton;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);
        category = findViewById(R.id.category_name);
        name = findViewById(R.id.fullname_info);
        price = findViewById(R.id.price_info);
        image = findViewById(R.id.img_info);
        detail = findViewById(R.id.detail);
        addButton = findViewById(R.id.updateButton);

        addButton.setOnClickListener(v -> {
            ProductRequestDTO requestDTO = new ProductRequestDTO(name.getText().toString(),
                    image.getText().toString(),
                    Integer.parseInt(price.getText().toString()),
                    category.getText().toString(),
                    detail.getText().toString());
            addProduct(requestDTO);
        });
    }

    private void addProduct(ProductRequestDTO requestDTO) {
        productService = RetrofitClient.getProductService();
        productService.addProduct(requestDTO).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(AddProductActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddProductActivity.this, ProductListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                Toast.makeText(AddProductActivity.this, "Thêm Khong Thành Công", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
