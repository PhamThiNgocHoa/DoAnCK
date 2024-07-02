package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailOrder;
import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<OrderResponseDTO> orders = new ArrayList<>();
    private Context context;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<OrderResponseDTO> orders) {
        this.orders = orders;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderResponseDTO order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView productNameTextView;
        private TextView productPriceTextView;
        private TextView productDescriptionTextView;
        private TextView productQuantityTextView;
        private TextView viewDetailsTextView;
        private TextView textViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productDescriptionTextView = itemView.findViewById(R.id.productDescriptionTextView);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);
            viewDetailsTextView = itemView.findViewById(R.id.viewDetailsTextView);
            textViewStatus = itemView.findViewById(R.id.statusTextView); // Ánh xạ textViewStatus từ item_order.xml
        }

        public void bind(OrderResponseDTO order) {
            if (order.getStatus() != null && !order.getStatus().isEmpty()) {
                textViewStatus.setText(order.getStatus());
                if (order.getStatus().equals("Hoàn thành") ||    order.getStatus().equals("Đang xử lý")) {
                    textViewStatus.setVisibility(View.GONE); // Ẩn textViewStatus khi status là "Hoàn thành"
                } else {
                    textViewStatus.setVisibility(View.VISIBLE); // Hiển thị textViewStatus trong các trường hợp khác
                }
            } else {
                textViewStatus.setText("Unknown Status"); // Nếu order.getStatus() null hoặc rỗng
                textViewStatus.setVisibility(View.VISIBLE); // Hiển thị textViewStatus với status không xác định
            }
            // Xử lý khi nhấn vào Xem chi tiết đơn hàng
            viewDetailsTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý khi nhấn vào Xem chi tiết đơn hàng
                    Intent intent = new Intent(context, DetailOrder.class);
                    intent.putExtra("order_id", order.getId()); // Ví dụ truyền ID đơn hàng
                    context.startActivity(intent);
                }
            });
        }
    }
}
