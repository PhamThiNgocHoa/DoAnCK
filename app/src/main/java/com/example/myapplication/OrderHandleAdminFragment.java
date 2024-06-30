package com.example.myapplication;

import static com.example.myapplication.OrderSuccessAdminFragment.ARG_PARAM1;
import static com.example.myapplication.OrderSuccessAdminFragment.ARG_PARAM2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.activity.OrderDetailListActivity;
import com.example.myapplication.adapter.OrderAdapter;
import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderHandleAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class OrderHandleAdminFragment extends Fragment {

    private OrderAdapter adapterOrderList;
    private RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public OrderHandleAdminFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        // Load lại dữ liệu ở đây
        getOrders();
    }

    public static OrderHandleAdminFragment newInstance(String param1, String param2) {
        OrderHandleAdminFragment fragment = new OrderHandleAdminFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_handle_admin, container, false);
        recyclerView = rootView.findViewById(R.id.view_in_fragment_handle_order);
        getOrders();
        return rootView;
    }

    private void getOrders() {
        OrderService orderService = RetrofitClient.getOrderService();
        orderService.getOrdersByStatus("Đang xử lý").enqueue(new Callback<List<OrderResponseDTO>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderResponseDTO> orders = response.body();
                    setupRecyclerView(orders);
                    adapterOrderList.notifyDataSetChanged();
                } else {
                    Log.e("Response null", "Response null");
                }
            }

            @Override
            public void onFailure(Call<List<OrderResponseDTO>> call, Throwable throwable) {
                handleOrderLoadError(throwable);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setupRecyclerView(List<OrderResponseDTO> orders) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapterOrderList = new OrderAdapter((ArrayList<OrderResponseDTO>) orders);
        adapterOrderList.setConfirmOrder(this::confirmOrder);
        adapterOrderList.setOnOrderClickListener(this::handleOrderClick);
        recyclerView.setAdapter(adapterOrderList);
    }

    private void handleOrderClick(OrderResponseDTO order) {
        Intent intent = new Intent(getContext(), OrderDetailListActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }

    private void confirmOrder(OrderResponseDTO order) {
        OrderService orderService = RetrofitClient.getOrderService();
        orderService.changeOrderStatus("Xác nhận thành công", order.getId()).enqueue(new Callback<Void>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("Xác nhận thành công", "Xác nhận thành công");
                getOrders();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.e("Xác nhận khoong thành công", "Xác nhận khoong thành công");
            }
        });
    }

    private void handleOrderLoadError(Throwable throwable) {
        Log.e("OrderHandleAdminFragment", "Response not successful: " + throwable.getMessage());
    }
}
