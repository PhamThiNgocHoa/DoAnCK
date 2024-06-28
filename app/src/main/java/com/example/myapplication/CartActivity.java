package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CartItemAdapter;
import com.example.myapplication.adapter.CategoryCustomerAdapter;
import com.example.myapplication.adapter.ProductCustomerAdapter;
import com.example.myapplication.network.CartItemService;
import com.example.myapplication.network.CartService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CartItemRequestDTO;
import com.example.myapplication.network.dto.response.CartItemResponseDTO;
import com.example.myapplication.network.dto.response.CartResponseDTO;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartItemAdapter.OnCartItemActionListener {
    private CartService cartService;
    private CartItemService cartItemService;
    private RecyclerView.Adapter adapterCartItemList;
    private RecyclerView recyclerView;
    TextView totalPrice;

    CustomerResponseDTO savedCustomer;
    private CartResponseDTO products;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        savedCustomer = SharedPrefManager.getCustomer(getApplicationContext());
        Button checkout = findViewById(R.id.cartActivityCheckoutBtn);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ThongTinKHActivity.class);
                intent.putExtra("cart", products);
                startActivity(intent);
            }
        });
        getCartItems();
        totalPrice = findViewById(R.id.cartActivityTotalPriceTv);

    }
    private void getCartItems(){
        cartService  = RetrofitClient.getCartService();
        cartService.getCart(savedCustomer.getId()).enqueue(new Callback<CartResponseDTO>() {
            @Override
            public void onResponse(Call<CartResponseDTO> call, Response<CartResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products = response.body();
                    recyclerView = findViewById(R.id.cartitem_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterCartItemList = new CartItemAdapter((ArrayList<CartItemResponseDTO>) products.getCartItems(), CartActivity.this);
                    recyclerView.setAdapter(adapterCartItemList);
                    totalPrice.setText(String.valueOf(products.getPrice()));
                    Log.e("CoursesListActivity", "Response success");
                    Toast.makeText(CartActivity.this, products.getCartItems().get(1).getProductId().getName() + "la kich thuoc cua no", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CartActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponseDTO> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPlus(CartItemResponseDTO cartItemResponseDTO) {
        cartItemService = RetrofitClient.getCartItemService();
        cartItemService.updateQuantityCartItem(cartItemResponseDTO.getId(), 1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for (CartItemResponseDTO item : products.getCartItems()) {
                        if (item.getId() == cartItemResponseDTO.getId()) {
                            item.setQuantity(item.getQuantity() + 1);  // Tăng số lượng sản phẩm lên 1
                            break;
                        }
                    }
                    totalPrice.setText(String.valueOf(products.getPrice()));
                    adapterCartItemList.notifyDataSetChanged();
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CartActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMinus(CartItemResponseDTO cartItemResponseDTO) {
        cartItemService = RetrofitClient.getCartItemService();
        cartItemService.updateQuantityCartItem(cartItemResponseDTO.getId(), -1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for (CartItemResponseDTO item : products.getCartItems()) {
                        if (item.getId() == cartItemResponseDTO.getId()) {
                            item.setQuantity(item.getQuantity() -1 );
                            if(item.getQuantity() <= 0){
                                products.getCartItems().remove(item);
                            }
                            break;
                        }
                    }
                    totalPrice.setText(String.valueOf(products.getPrice()));
                    adapterCartItemList.notifyDataSetChanged();
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CartActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDelete(CartItemResponseDTO cartItemResponseDTO) {
        cartItemService = RetrofitClient.getCartItemService();
        cartItemService.deleteCartItem(cartItemResponseDTO.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for (CartItemResponseDTO item : products.getCartItems()) {
                        if (item.getId() == cartItemResponseDTO.getId()) {
                            products.getCartItems().remove(item);
                            break;
                        }
                    }
                    totalPrice.setText(String.valueOf(products.getPrice()));
                    adapterCartItemList.notifyDataSetChanged();
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CartActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
