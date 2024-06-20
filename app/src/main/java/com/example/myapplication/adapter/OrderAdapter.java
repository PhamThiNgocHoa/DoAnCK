package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.CategoryListActivity;
import com.example.myapplication.activity.OrderListActivity;
import com.example.myapplication.network.CustomerService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    ArrayList<OrderResponseDTO> items;
    CustomerService customerService = RetrofitClient.getCustomerService();

    public OrderAdapter(ArrayList<OrderResponseDTO> items) {
        this.items = items;
    }

    Context context;


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_order, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        // Lấy thông tin khách hàng dựa trên ID
        holder.address.setText(items.get(position).getAddress());
        holder.phone.setText(items.get(position).getNumberPhone());
        holder.orderDate.setText(items.get(position).getOrderDate());
        holder.price.setText(items.get(position).getTotalAmount().toString());
        Integer customerId = items.get(position).getCustomerId();
        System.out.println(customerId);
        Call<CustomerResponseDTO> call = customerService.getCustomerById(customerId);
        call.enqueue(new Callback<CustomerResponseDTO>() {
            @Override
            public void onResponse(Call<CustomerResponseDTO> call, Response<CustomerResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CustomerResponseDTO responseDTO = response.body();
                    holder.customerName.setText(responseDTO.getFullname()); // Sử dụng thông tin khách hàng từ responseDTO
                } else {
                    Log.e("Không tìm thấy customer", "Không tìm thấy customer");
                }
            }

            @Override
            public void onFailure(Call<CustomerResponseDTO> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address, customerName, phone, price, orderDate;
        ConstraintLayout layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            customerName = itemView.findViewById(R.id.customer_name);
            price = itemView.findViewById(R.id.price);
            orderDate = itemView.findViewById(R.id.order_date);
            phone = itemView.findViewById(R.id.phone);
            layout = itemView.findViewById(R.id.order_layout);
        }
    }
}
