package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.network.CartItemService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CartItemRequestDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedActivity extends AppCompatActivity {
    private ProductResponseDTO product;
    private ImageView product_img;
    private TextView name, category, price, detail;
    private CartItemService cartItemService;

    CustomerResponseDTO savedCustomer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed);
        Button addToCart = findViewById(R.id.detailActivityAddToCartBtn);
        ImageView back = findViewById(R.id.back);
        savedCustomer = SharedPrefManager.getCustomer(getApplicationContext());
        back.setOnClickListener(view -> {
            Intent intent = new Intent(DetailedActivity.this, ProductActivity.class);
            startActivity(intent);
        });
        Intent intent = getIntent();
        product_img = findViewById(R.id.detailProductImage);
        name = findViewById(R.id.detail_nameProduct);
        category = findViewById(R.id.detail_category);
        price = findViewById(R.id.detail_price);
        detail = findViewById(R.id.detail_product);
        if (intent != null && intent.hasExtra("product")) {
            product = (ProductResponseDTO) intent.getSerializableExtra("product");
            if (product != null) {
                name.setText(product.getName());
                category.setText("Danh Mục: " + product.getCategoryName());
                price.setText("Giá: " + String.valueOf(product.getPrice()));
                detail.setText(product.getDetail());
                Glide.with(this)
                        .load(product.getImg())// URL của hình ảnh
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(product_img);
            }

        }
        addToCart.setOnClickListener(view -> {
            cartItemService = RetrofitClient.getCartItemService();
            CartItemRequestDTO cartItemRequestDTO = new CartItemRequestDTO(savedCustomer.getCartId(), product.getId(), 1);
            cartItemService.addCartItem(cartItemRequestDTO).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful() & response.body() != null) {
                        Toast.makeText(DetailedActivity.this, " Thêm vào giỏ hàng thành công ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailedActivity.this, " Thêm vào giỏ hàng thất bại ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });

        });

    }
}
