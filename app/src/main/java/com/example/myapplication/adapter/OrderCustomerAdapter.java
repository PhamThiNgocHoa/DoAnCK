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
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderCustomerAdapter extends RecyclerView.Adapter<OrderCustomerAdapter.ViewHolder>{
    ArrayList<OrderResponseDTO> items;
    OnOrderCustomerActionListener onOrderCustomerActionListener;
    public OrderCustomerAdapter(ArrayList<OrderResponseDTO> items, OnOrderCustomerActionListener onOrderCustomerActionListener) {
        this.items = items;
        this.onOrderCustomerActionListener =onOrderCustomerActionListener;
    }

    Context context;
    public interface OnOrderCustomerActionListener {
        void onViewDetail(OrderResponseDTO orderResponseDTO);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_don_hang, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<OrderDetailResponseDTO> orderDetailResponseDTOSet = items.get(position).getOrderDetails();

        OrderDetailResponseDTO detailFirst = null;
        for (OrderDetailResponseDTO detail : orderDetailResponseDTOSet) {
            detailFirst = detail;
            break; // Dừng vòng lặp sau khi lấy được phần tử đầu tiên
        }
        if (detailFirst != null) {
            holder.name_product.setText(detailFirst.getProductResponseDTO().getName());
            holder.category.setText(detailFirst.getProductResponseDTO().getCategoryName());
        }
        holder.price.setText(String.valueOf(items.get(position).getTotalAmount()));
        holder.quantity.setText(String.valueOf(orderDetailResponseDTOSet.size()));

        Glide.with(context)
                .load(detailFirst != null ? detailFirst.getProductResponseDTO().getImg() : "")
                .into(holder.image_product);
        holder.viewDetail.setOnClickListener(v -> onOrderCustomerActionListener.onViewDetail(items.get(position)));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price, category, quantity, viewDetail;
        ImageView image_product;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price_order);
            category = itemView.findViewById(R.id.order_category);
            quantity = itemView.findViewById(R.id.order_quantity);
            viewDetail = itemView.findViewById(R.id.detail);
            image_product = itemView.findViewById(R.id.image_product_order);
            layout = itemView.findViewById(R.id.product_layout);
        }
    }
}
