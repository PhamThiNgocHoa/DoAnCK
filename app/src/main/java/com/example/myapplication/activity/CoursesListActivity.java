package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CoursesAdapter;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.domain.CoursesDomain;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCoursesList;
    private RecyclerView recyclerView;
    private ProductService productService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_list);
        initRecyclerView();
    }
    private void initRecyclerView(){
        ArrayList<CoursesDomain> coursesDomains = new ArrayList<>();
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_1"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_2"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_3"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_4"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_1"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_2"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_3"));
        coursesDomains.add(new CoursesDomain("Laptop1", 300, "ic_4"));
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterCoursesList = new CoursesAdapter(coursesDomains);
        recyclerView.setAdapter(adapterCoursesList);
    }
    private void getProducts()  {
        productService  = RetrofitClient.getProductService();
        productService.getProducts().enqueue(new Callback<List<ProductResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ProductResponseDTO>> call, Response<List<ProductResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductResponseDTO> products = response.body();
                    recyclerView = findViewById(R.id.view);

                    recyclerView.setLayoutManager(new LinearLayoutManager(CoursesListActivity.this, LinearLayoutManager.VERTICAL, false));
        adapterCoursesList = new ProductAdapter((ArrayList<ProductResponseDTO>) products);
        recyclerView.setAdapter(adapterCoursesList);

                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CoursesListActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(CoursesListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}