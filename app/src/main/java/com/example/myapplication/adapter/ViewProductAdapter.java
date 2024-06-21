package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.activity.DetailedActivity;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.ViewHolder> {
    ArrayList<ProductResponseDTO> items;

    public ViewProductAdapter(ArrayList<ProductResponseDTO> items){
        this.items = items;
    }
    Context context;
    @NonNull
    @Override
    public ViewProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_product, parent, false);
        context = parent.getContext();
        return  new ViewProductAdapter.ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductAdapter.ViewHolder holder, int position) {
        ProductResponseDTO product = items.get(position);
        holder.textProductName.setText(product.getName());
        holder.textProductPrice.setText(formatCurrency(product.getPrice()));
        Glide.with(context).load(product.getImg()).into(holder.imageProduct);
        // Set OnClickListener for "Xem chi tiết" button
        holder.buttonViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("id", product.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textProductName, textProductPrice, buttonViewDetail;
        ImageView imageProduct;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.textProductName);
            buttonViewDetail = itemView.findViewById(R.id.buttonViewDetail);
            textProductPrice = itemView.findViewById(R.id.textProductPrice);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            layout = itemView.findViewById(R.id.frameProduct);
        }
    }
    // Format currency to display VND
    private String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,### VNĐ");
        return formatter.format(amount * 1000);
    }
}
