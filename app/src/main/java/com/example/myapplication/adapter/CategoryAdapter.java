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
import com.example.myapplication.network.dto.response.CategoryResponseDTO;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryResponseDTO> items;

    public CategoryAdapter(ArrayList<CategoryResponseDTO> items) {
        this.items = items;
    }
    Context context;

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_category, parent, false);
        context = parent.getContext();
        return new CategoryAdapter.ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.name_category.setText(items.get(position).getName());
        Glide.with(context).load(items.get(position).getImg()).into(holder.image_category);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_category;
        ImageView image_category;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_category = itemView.findViewById(R.id.name_category);
            image_category = itemView.findViewById(R.id.image_category);
            layout = itemView.findViewById(R.id.category_layout);
        }
    }
}
