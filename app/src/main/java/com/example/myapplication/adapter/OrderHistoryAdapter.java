package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {
    private List<OrderResponseDTO> orderResponseDTOS;

    public OrderHistoryAdapter(List<OrderResponseDTO> orderResponseDTOS) {
        this.orderResponseDTOS = orderResponseDTOS;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderResponseDTO order = orderResponseDTOS.get(position);
        holder.idOrderTextView.setText("Order ID: " + order.getId());
        holder.dateTextView.setText("Date: " + order.getOrderDate());
        holder.totalAmountTextView.setText("Amount: " + order.getTotalAmount() + " VND");
    }

    @Override
    public int getItemCount() {
        return orderResponseDTOS.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView idOrderTextView;
        TextView dateTextView;
        TextView totalAmountTextView;
        TextView detailTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrderTextView = itemView.findViewById(R.id.idOrder);
            dateTextView = itemView.findViewById(R.id.date);
            totalAmountTextView = itemView.findViewById(R.id.totalAmount);
            detailTextView = itemView.findViewById(R.id.detail);
        }
    }
}

