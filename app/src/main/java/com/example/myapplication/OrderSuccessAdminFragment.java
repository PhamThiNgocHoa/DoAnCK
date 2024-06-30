package com.example.myapplication;

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
 * Use the {@link OrderSuccessAdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderSuccessAdminFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter adapterOrderList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_PARAM1 = "param1";
    static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @SuppressLint("NotifyDataSetChanged")
    public OrderSuccessAdminFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        // Load lại dữ liệu ở đây
        getOrders();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderSuccessAdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderSuccessAdminFragment newInstance(String param1, String param2) {
        OrderSuccessAdminFragment fragment = new OrderSuccessAdminFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_success_admin, container, false);
        recyclerView = rootView.findViewById(R.id.view_in_fragment_success_order);
        getOrders();
        return rootView;
    }

    private void getOrders() {
        OrderService orderService = RetrofitClient.getOrderService();
        orderService.getOrdersByStatus("Xác nhận thành công").enqueue(new Callback<List<OrderResponseDTO>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<OrderResponseDTO>> call, Response<List<OrderResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<OrderResponseDTO> orders = response.body();
                    setupRecyclerView(orders);
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


    private void setupRecyclerView(List<OrderResponseDTO> orders) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapterOrderList = new OrderAdapter((ArrayList<OrderResponseDTO>) orders);
        adapterOrderList.setOnOrderClickListener(this::handleOrderClick);
        recyclerView.setAdapter(adapterOrderList);
    }

    private void handleOrderClick(OrderResponseDTO order) {
        Intent intent = new Intent(getContext(), OrderDetailListActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }

    private void handleOrderLoadError(Throwable throwable) {
        Log.e("OrderHandleAdminFragment", "Response not successful: " + throwable.getMessage());
    }
}