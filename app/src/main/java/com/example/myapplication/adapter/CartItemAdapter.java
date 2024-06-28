package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.CartItemResponseDTO;

import java.util.ArrayList;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {
    ArrayList<CartItemResponseDTO> items;
    private OnCartItemActionListener onCartItemActionListener;

    public CartItemAdapter(ArrayList<CartItemResponseDTO> items, OnCartItemActionListener onCartItemActionListener) {
        this.items = items;
        this.onCartItemActionListener = onCartItemActionListener;
    }

    Context context;

    public interface OnCartItemActionListener {
        void onPlus(CartItemResponseDTO cartItemResponseDTO);
        void onMinus(CartItemResponseDTO cartItemResponseDTO);
        void onDelete(CartItemResponseDTO cartItemResponseDTO);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cartitem, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_product.setText(items.get(position).getProductId().getName());
        holder.name_category.setText(items.get(position).getProductId().getCategoryName());
        holder.price.setText(String.valueOf(items.get(position).getProductId().getPrice()));
        holder.quantity.setText(String.valueOf(items.get(position).getQuantity()));
        holder.delete.setOnClickListener(v -> onCartItemActionListener.onDelete(items.get(position)));
        holder.plus.setOnClickListener(v -> onCartItemActionListener.onPlus(items.get(position)));
        holder.minus.setOnClickListener(v -> onCartItemActionListener.onMinus(items.get(position)));
        Glide.with(context).load(items.get(position).getProductId().getImg()).into(holder.image_productItem);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, name_category, price, quantity;
        ImageView image_productItem ,delete;;
        ImageButton plus, minus;

        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.eachCartItemName);
            name_category = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            image_productItem = itemView.findViewById(R.id.cart_item_img);
            price = itemView.findViewById(R.id.eachCartItemPriceTv);
            quantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            plus = itemView.findViewById(R.id.cartItem_add_quantity);
            minus = itemView.findViewById(R.id.cart_item_minus_quantity);
            delete = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            layout = itemView.findViewById(R.id.cartitem_layout);
        }
    }
}
