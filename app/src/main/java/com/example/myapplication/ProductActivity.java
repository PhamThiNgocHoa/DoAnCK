package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CategoryCustomerAdapter;
import com.example.myapplication.adapter.ProductCustomerAdapter;
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements ProductCustomerAdapter.OnProductCustomerActionListener,CategoryCustomerAdapter.OnCategoryCustomerActionListener {
    private RecyclerView.Adapter adapterCategoryList;
    private RecyclerView recyclerView;
    private CategoryService categoryService;
    private RecyclerView.Adapter adapterCoursesList;
    private ProductService productService;
    private RecyclerView recyclerViewProduct;
    private SearchView find_Product;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        CustomerResponseDTO savedCustomer = SharedPrefManager.getCustomer(getApplicationContext());

//        TextView detailProduct = findViewById(R.id.buttonViewDetail);
        ImageView home = findViewById(R.id.home);
        ImageView history = findViewById(R.id.history);
        ImageView user = findViewById(R.id.user);
        ImageView cartIv = findViewById(R.id.cartIv);
        ImageView logout = findViewById(R.id.logout);
        find_Product = findViewById(R.id.search_product);
//        if (find_Product == null) {
//            Log.e("ProductActivity", "SearchView is null");
//            return;
//        }
        find_Product.setImeOptions(EditorInfo.IME_ACTION_SEARCH); // Set IME action to "Search"
        find_Product.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String query) {
                                                  Toast.makeText(ProductActivity.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                                                  productService  = RetrofitClient.getProductService();
                                                  productService.getProductsByName(query).enqueue(new Callback<List<ProductResponseDTO>>() {
                                                      @Override
                                                      public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                                                          if (response.isSuccessful() && response.body() != null) {
                                                              List<ProductResponseDTO> products = response.body();
                                                              Intent intent = new Intent(ProductActivity.this, TimKiemSanPhamActivity.class);
                                                              intent.putExtra("products", (Serializable) products);
                                                              startActivity(intent);
                                                          } else {
                                                              Log.e("CoursesListActivity", "Response not successful");
                                                              Toast.makeText(ProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                                                          }
                                                      }

                                                      @Override
                                                      public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                                                          Log.e("CoursesListActivity", "onFailure: ", t);
                                                          Toast.makeText(ProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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


        cartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, History.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.deleteCustomer(getApplicationContext());
                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        getCategories();
        getProducts();

    }
    private void getCategories() {
        categoryService  = RetrofitClient.getCategoryService();
        categoryService.getCategories().enqueue(new Callback<List<CategoryResponseDTO>>() {
            @Override
            public void onResponse(Call<List<CategoryResponseDTO>> call, Response<List<CategoryResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CategoryResponseDTO> products = response.body();
                    recyclerView = findViewById(R.id.view_danhMuc);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ProductActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    adapterCategoryList = new CategoryCustomerAdapter((ArrayList<CategoryResponseDTO>) products, ProductActivity.this);
                    recyclerView.setAdapter(adapterCategoryList);
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(ProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(ProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getProducts()  {
        productService  = RetrofitClient.getProductService();
        productService.getProducts().enqueue(new Callback<List<ProductResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductResponseDTO> products = response.body();
                    recyclerViewProduct = findViewById(R.id.view_product);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductActivity.this, 2);

                    recyclerViewProduct.setLayoutManager(gridLayoutManager);
                    adapterCoursesList = new ProductCustomerAdapter((ArrayList<ProductResponseDTO>) products, ProductActivity.this);
                    recyclerViewProduct.setAdapter(adapterCoursesList);

                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(ProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(ProductActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewDetail(ProductResponseDTO productResponseDTO) {
        Intent intent = new Intent(ProductActivity.this, DetailedActivity.class);
        intent.putExtra("product", productResponseDTO);
        startActivity(intent);
    }

    @Override
    public void onClick(CategoryResponseDTO categoryResponseDTO) {
        Toast.makeText(ProductActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        productService.getProductsByCategoryId(categoryResponseDTO.getId()).enqueue(new Callback<List<ProductResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductResponseDTO> products = response.body();
                    Intent intent = new Intent(ProductActivity.this, TimKiemSanPhamActivity.class);
                    intent.putExtra("products", (Serializable) products);
                    Toast.makeText(ProductActivity.this, "Oke", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(ProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {

            }
        });
    }
}
