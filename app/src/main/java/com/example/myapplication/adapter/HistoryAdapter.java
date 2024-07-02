package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
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
        private TextView customer, price, orderDate, address, phone, receiver, viewDetailsTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer = itemView.findViewById(R.id.customer_name);
            price = itemView.findViewById(R.id.price);
            orderDate = itemView.findViewById(R.id.order_date);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            receiver = itemView.findViewById(R.id.fullname);
            viewDetailsTextView = itemView.findViewById(R.id.viewDetailsTextView);

        }

        @SuppressLint("SetTextI18n")
        public void bind(OrderResponseDTO order) {

            customer.setText("Customer: " + order.getCustomerDTO().getFullname());
            price.setText("Price: " + String.valueOf(order.getTotalAmount()));
            orderDate.setText("Order Date: " + order.getOrderDate());
            address.setText("Address: " + order.getAddress());
            phone.setText("Phone: " + order.getNumberPhone());
            receiver.setText("Receiver: " + order.getReceiver());

            // Xử lý khi nhấn vào Xem chi tiết đơn hàng
            viewDetailsTextView.setOnClickListener(v -> {
                // Xử lý khi nhấn vào Xem chi tiết đơn hàng
                Intent intent = new Intent(context, DetailOrder.class);
                intent.putExtra("order", order); // Ví dụ truyền ID đơn hàng
                context.startActivity(intent);
            });
        }
    }
}
