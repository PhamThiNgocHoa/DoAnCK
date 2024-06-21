package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;

import java.util.ArrayList;
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private ArrayList<CustomerResponseDTO> items;
    private OnCustomerActionListener onCustomerActionListener;

    public CustomerAdapter(ArrayList<CustomerResponseDTO> items, OnCustomerActionListener listener) {
        this.items = items;
        this.onCustomerActionListener = listener;
    }

    public interface OnCustomerActionListener {
        void onEdit(CustomerResponseDTO customer);
        void onDelete(CustomerResponseDTO customer);
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list_customer, parent, false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        holder.username.setText(items.get(position).getUsername());
        holder.email.setText(String.valueOf(items.get(position).getEmail()));
        holder.phone.setText(String.valueOf(items.get(position).getPhone()));
        holder.edit.setOnClickListener(v -> onCustomerActionListener.onEdit(items.get(position)));
        holder.delete.setOnClickListener(v -> onCustomerActionListener.onDelete(items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, email, phone;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            email = itemView.findViewById(R.id.email);
            phone = itemView.findViewById(R.id.phone);
            edit = itemView.findViewById(R.id.btn_edit);
            delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}

