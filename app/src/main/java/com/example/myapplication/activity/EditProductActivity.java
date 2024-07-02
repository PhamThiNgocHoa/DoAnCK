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
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductActivity extends AppCompatActivity {
    private TextView category, name, price, image, detail;
    private Button updateButton;
    private ProductResponseDTO product;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_product);
        category = findViewById(R.id.category_name);
        name = findViewById(R.id.fullname_info);
        price = findViewById(R.id.price_info);
        image = findViewById(R.id.img_info);
        detail = findViewById(R.id.detail);
        updateButton = findViewById(R.id.updateButton);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product")) {
            product = (ProductResponseDTO) intent.getSerializableExtra("product");
            if (product != null) {
                category.setText(product.getCategoryName());
                name.setText(product.getName());
                price.setText(String.valueOf(product.getPrice()));
                image.setText(product.getImg());
                detail.setText(product.getDetail());
            }
        }
        productService = RetrofitClient.getProductService();
        updateButton.setOnClickListener(v -> {
            ProductRequestDTO requestDTO = new ProductRequestDTO(name.getText().toString(),
                    image.getText().toString(),
                    Integer.valueOf(price.getText().toString()),
                    category.getText().toString(),
                    detail.getText().toString());
            updateProduct(product.getId(), requestDTO);
        });
    }

    private void updateProduct(int id, ProductRequestDTO requestDTO) {
        productService.updateProduct(id, requestDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(EditProductActivity.this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProductActivity.this, ProductListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(EditProductActivity.this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
