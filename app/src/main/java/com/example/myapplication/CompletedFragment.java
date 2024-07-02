package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.HistoryAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.CustomerResponseDTO;
import com.example.myapplication.network.dto.response.OrderResponseDTO;
import com.example.myapplication.ultil.SharedPrefManager;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private CustomerResponseDTO saveCustomer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        // Khởi tạo adapter và set cho RecyclerView
        historyAdapter = new HistoryAdapter(getContext());
        recyclerView.setAdapter(historyAdapter);
        saveCustomer = SharedPrefManager.getCustomer(requireContext().getApplicationContext());
        // Gọi API để lấy danh sách đơn hàng đã hoàn thành
        fetchOrders();

        return view;
    }

    private void fetchOrders() {
        OrderService orderService = RetrofitClient.getRetrofitInstance().create(OrderService.class);
        Call<List<OrderResponseDTO>> call = orderService.getOrdersByStatusAndCustomerId(saveCustomer.getId(), "Xác nhận thành công");
        call.enqueue(new Callback<List<OrderResponseDTO>>() {
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful()) {
                    List<OrderResponseDTO> orders = response.body();
                    if (orders != null && !orders.isEmpty()) {
                        historyAdapter.setData(orders);
                        Log.d("ProcessingFragment", "Fetch orders success. Number of orders: " + orders.size());
                        for (OrderResponseDTO order : orders) {
                            Log.d("ProcessingFragment", "Order details: " + new Gson().toJson(order));
                        }
                    }
                } else {
                    Log.e("ProcessingFragment", "Failed to fetch orders. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable t) {
                Log.e("ProcessingFragment", "Fetch orders failed: " + t.getMessage());
                // Xử lý khi gọi API thất bại
            }
        });
    }
}