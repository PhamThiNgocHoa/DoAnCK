package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.OrderDetailResponseDTO;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder>{
    ArrayList<OrderDetailResponseDTO> items;

    public OrderItemAdapter(ArrayList<OrderDetailResponseDTO> items) {
        this.items = items;
    }
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductResponseDTO productResponseDTO = items.get(position).getProductResponseDTO();
        holder.name_product.setText(productResponseDTO.getName());
        holder.price.setText(String.valueOf(productResponseDTO.getPrice()));
        holder.category.setText(productResponseDTO.getCategoryName());
        holder.quantity.setText(String.valueOf(items.get(position).getQuantity()));
        Glide.with(context).load("http://192.168.1.3:8080/images/products/laptop1.jpg").into(holder.image_product);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price, category, quantity;
        ImageView image_product;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.name_product_order);
            price = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.name_category);
            quantity = itemView.findViewById(R.id.quantity);
            image_product = itemView.findViewById(R.id.image_product);
            layout = itemView.findViewById(R.id.layout_order_item);
        }
    }
}
