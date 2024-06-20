package com.example.myapplication.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<OrderResponseDTO> items;

    public OrderAdapter(ArrayList<OrderResponseDTO> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
