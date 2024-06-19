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
import com.example.myapplication.domain.CoursesDomain;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<ProductResponseDTO> items;

    public ProductAdapter(ArrayList<ProductResponseDTO> items) {
        this.items = items;
    }

    Context context;

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_product.setText(items.get(position).getName());
        holder.price.setText(String.valueOf(items.get(position).getPrice()));
//        int drawableResourceId = holder.itemView.getResources()
//                .getIdentifier(items.get(position).getImg(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context).load(items.get(position).getImg()).into(holder.image_product);
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price;
        ImageView image_product;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price);
            image_product = itemView.findViewById(R.id.image_product);
            layout = itemView.findViewById(R.id.product_layout);
        }
    }
}
