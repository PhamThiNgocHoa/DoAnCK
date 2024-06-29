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
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.ArrayList;

public class ProductCustomerAdapter extends RecyclerView.Adapter<ProductCustomerAdapter.ViewHolder> {
    ArrayList<ProductResponseDTO> items;
    private OnProductCustomerActionListener onProductCustomerActionListener;

    public ProductCustomerAdapter(ArrayList<ProductResponseDTO> items, OnProductCustomerActionListener onProductCustomerActionListener) {
        this.onProductCustomerActionListener = onProductCustomerActionListener;
        this.items = items;
    }

    Context context;

    public interface OnProductCustomerActionListener {
        void onViewDetail(ProductResponseDTO productResponseDTO);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_sanpham, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_product.setText(items.get(position).getName());
        holder.price.setText(String.valueOf(items.get(position).getPrice()));
        Glide.with(context).load(items.get(position).getImg()).centerInside().into(holder.image_product);
        holder.layout.setOnClickListener(v -> onProductCustomerActionListener.onViewDetail(items.get(position)));

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price;
        ImageView image_product;
        TextView btn_viewDetail;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.textProductName);
            price = itemView.findViewById(R.id.textProductPrice);
            image_product = itemView.findViewById(R.id.imageProduct);
            layout = itemView.findViewById(R.id.sanpham_layout);
        }
    }
}
