package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.format.FormatCurrency;
import com.example.myapplication.network.OrderDetailService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {
    ArrayList<OrderDetailResponseDTO> items;
    Context context;
    ProductService productService = RetrofitClient.getProductService();

    public OrderDetailAdapter(ArrayList<OrderDetailResponseDTO> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity, category;
        ImageView image;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price_product);
            quantity = itemView.findViewById(R.id.quantity_product);
            category = itemView.findViewById(R.id.category_product);
            image = itemView.findViewById(R.id.img_product);
            layout = itemView.findViewById(R.id.linear_layout);


        }
    }

    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout XML here
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_order_detail, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        holder.quantity.setText("x" + items.get(position).getQuantity());
        Call<ProductResponseDTO> call = productService.getProduct(items.get(position).getProductId());

        call.enqueue(new Callback<ProductResponseDTO>() {
            @Override
            public void onResponse(Call<ProductResponseDTO> call, Response<ProductResponseDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponseDTO responseDTO = response.body();
//                    Glide.with(context).load(responseDTO.getImg()).into(holder.image);
                    holder.price.setText(FormatCurrency.formatCurrency(responseDTO.getPrice()));
                    holder.name.setText(responseDTO.getName());
                    holder.category.setText(responseDTO.getCategoryName());
                } else {
                    Log.e("Không tìm thấy product", "Không tìm thấy product");
                }
            }

            @Override
            public void onFailure(Call<ProductResponseDTO> call, Throwable t) {
                Log.e("CoursesListActivity", "onFailure: ", t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
