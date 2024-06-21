package com.example.myapplication.activity;
import android.content.Intent;
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
public class CustomerListActivity extends AppCompatActivity implements CustomerAdapter.OnCustomerActionListener {

    private RecyclerView recyclerView;
    private CustomerAdapter adapterCustomerList;
    private CustomerService customerService;
    private List<CustomerResponseDTO> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_list);

        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getCustomers();
    }

    private void getCustomers() {
        customerService = RetrofitClient.getCustomerService();
        customerService.getCustomers().enqueue(new Callback<List<CustomerResponseDTO>>() {
            @Override
            public void onResponse(Call<List<CustomerResponseDTO>> call, Response<List<CustomerResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    customers = response.body();
                    adapterCustomerList = new CustomerAdapter((ArrayList<CustomerResponseDTO>) customers, CustomerListActivity.this);
                    recyclerView.setAdapter(adapterCustomerList);
                } else {
                    Log.e("CustomerListActivity", "Response not successful");
                    Toast.makeText(CustomerListActivity.this, "Failed to load customers", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CustomerResponseDTO>> call, Throwable t) {
                Log.e("CustomerListActivity", "onFailure: ", t);
                Toast.makeText(CustomerListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEdit(CustomerResponseDTO customer) {
        Intent intent = new Intent(CustomerListActivity.this, EditCustomerActivity.class);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }

    @Override
    public void onDelete(CustomerResponseDTO customer) {
        customerService = RetrofitClient.getCustomerService();
        customerService.delete(customer.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CustomerListActivity.this, "Deleted successfully: " + customer.getUsername(), Toast.LENGTH_SHORT).show();
                    int position = customers.indexOf(customer);
                    if (position != -1) {
                        customers.remove(position);
                        adapterCustomerList.notifyItemRemoved(position);
                    }
                } else {
                    Toast.makeText(CustomerListActivity.this, "Failed to delete: " + customer.getUsername(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CustomerListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
