package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.CategoryResponseDTO;

import java.util.ArrayList;

public class CategoryCustomerAdapter extends RecyclerView.Adapter<CategoryCustomerAdapter.ViewHolder> {
    ArrayList<CategoryResponseDTO> items;
    private OnCategoryCustomerActionListener onCategoryCustomerActionListener;

    public CategoryCustomerAdapter(ArrayList<CategoryResponseDTO> items, OnCategoryCustomerActionListener onCategoryCustomerActionListener) {
        this.items = items;
        this.onCategoryCustomerActionListener = onCategoryCustomerActionListener;
    }

    Context context;

    public interface OnCategoryCustomerActionListener {
        void onClick(CategoryResponseDTO categoryResponseDTO);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_danhmuc, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(items.get(position).getImg()).into(holder.image_category);
        holder.layout.setOnClickListener(v -> onCategoryCustomerActionListener.onClick(items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_category;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_category = itemView.findViewById(R.id.anhDanhMuc);
            layout = itemView.findViewById(R.id.danhMuc_layout);
        }
    }
}
