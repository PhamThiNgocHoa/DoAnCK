package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.DetailedActivity;
import com.example.myapplication.network.dto.response.ProductResponseDTO;

import java.util.List;

public class ProductSeachAdapter extends RecyclerView.Adapter<ProductSeachAdapter.ProductViewHolder> {
    private List<ProductResponseDTO> productList;
    private Context context;

    public ProductSeachAdapter(List<ProductResponseDTO> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_seach, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if (productList == null) return;
        ProductResponseDTO product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("Giá tiền: $" + product.getPrice());
        Glide.with(holder.itemView.getContext()).load(product.getImg()).into(holder.productImage);

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
        if (productList == null) return 0;
        return productList.size();
    }

    public void updateProductList(List<ProductResponseDTO> newProductList) {
        productList = newProductList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;
        TextView buttonViewDetail;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageProduct);
            productName = itemView.findViewById(R.id.textProductName);
            productPrice = itemView.findViewById(R.id.textProductPrice);
            buttonViewDetail = itemView.findViewById(R.id.buttonViewDetail);
        }
    }
}
