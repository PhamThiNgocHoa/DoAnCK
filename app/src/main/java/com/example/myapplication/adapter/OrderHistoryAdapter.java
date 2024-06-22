package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailOrder;
import com.example.myapplication.R;
import com.example.myapplication.activity.DetailedActivity;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {
    private List<OrderResponseDTO> orderResponseDTOS;
    Context context;
    public OrderHistoryAdapter(ArrayList<OrderResponseDTO> orderResponseDTOS) {
        this.orderResponseDTOS = orderResponseDTOS;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        context = parent.getContext();
        return new OrderHistoryAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderResponseDTO order = orderResponseDTOS.get(position);
        holder.idOrderTextView.setText(String.valueOf(order.getId()));
        holder.dateTextView.setText(order.getOrderDate().toString());
        holder.totalAmountTextView.setText(formatCurrency(order.getTotalAmount()));
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailOrder.class);
                intent.putExtra("id", order.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderResponseDTOS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idOrderTextView;
        TextView dateTextView;
        TextView totalAmountTextView;
        TextView detailTextView;
        TextView detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrderTextView = itemView.findViewById(R.id.idOrder);
            dateTextView = itemView.findViewById(R.id.date);
            totalAmountTextView = itemView.findViewById(R.id.total);
            detailTextView = itemView.findViewById(R.id.detail);
            detail = itemView.findViewById(R.id.detail);
        }
    }
    private String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("#,### VNĐ");
        return formatter.format(amount * 1000);
    }
}

