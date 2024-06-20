package com.example.myapplication.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomerAdapter;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCustomerList;
    private RecyclerView recyclerView;
    private CustomerService customerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_list);
        getCustomers();
    }
    private void getCustomers(){
        customerService = RetrofitClient.getCustomerService();
        customerService.getCustomers().enqueue(new Callback<List<CustomerResponseDTO>>() {
            @Override
            public void onResponse(Call<List<CustomerResponseDTO>> call, Response<List<CustomerResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CustomerResponseDTO> customers = response.body();
                    recyclerView = findViewById(R.id.view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CustomerListActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapterCustomerList = new CustomerAdapter((ArrayList<CustomerResponseDTO>) customers);
                    recyclerView.setAdapter(adapterCustomerList);
                } else {
                    Log.e("CoursesListActivity", "Response not successful");
                    Toast.makeText(CustomerListActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CustomerResponseDTO>> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
                Toast.makeText(CustomerListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
