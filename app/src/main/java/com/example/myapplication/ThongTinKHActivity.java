package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.network.OrderService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.OrderDetailRequestDTO;
import com.example.myapplication.network.dto.request.OrderRequestDTO;
import com.example.myapplication.network.dto.response.CartItemResponseDTO;
import com.example.myapplication.network.dto.response.CartResponseDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongTinKHActivity extends AppCompatActivity {
    private CartResponseDTO cartResponseDTO;
    private OrderService orderService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_khactivity);
        ImageView back = findViewById(R.id.back);
        Button xacNhan = findViewById(R.id.XacnhanButton);
        EditText name, phone, address;
        name = findViewById(R.id.n_cus);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cart")) {
            cartResponseDTO = (CartResponseDTO) intent.getSerializableExtra("cart");
        }
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ThongTinKHActivity.this, CartActivity.class);
//                startActivity(intent);
//            }
//        });
        xacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ThongTinKHActivity.this, "CLick vao xac nhan", Toast.LENGTH_SHORT).show();
                if(cartResponseDTO!=null){
                    OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
                    orderRequestDTO.setReceiver(name.getText().toString());
                    orderRequestDTO.setAddress(address.getText().toString());
                    orderRequestDTO.setNumberPhone(phone.getText().toString());
                    orderRequestDTO.setCustomerId(cartResponseDTO.getCustomerId());
                    orderRequestDTO.setTotalAmount(cartResponseDTO.getPrice());
                    orderRequestDTO.setStatus("Success");
                    List<OrderDetailRequestDTO> orderDetailRequestDTOS = new ArrayList<>();
                    for (CartItemResponseDTO cartItemResponseDTO: cartResponseDTO.getCartItems()){
                        orderDetailRequestDTOS.add(new OrderDetailRequestDTO(cartItemResponseDTO.getProductId().getId(), cartItemResponseDTO.getQuantity()));
                    }
                    orderRequestDTO.setOrderDetails(orderDetailRequestDTOS);
                    orderService = RetrofitClient.getOrderService();
                    orderService.saveOrder(orderRequestDTO).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if (response.isSuccessful()) {
                                Log.e("CoursesListActivity", "Response success");
                                Toast.makeText(ThongTinKHActivity.this, "Chúc mừng bạn đặt hàng thành công" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ThongTinKHActivity.this, ProductActivity.class);
                                startActivity(intent);

                            } else {
                                Log.e("CoursesListActivity", "Response not successful");
                                Toast.makeText(ThongTinKHActivity.this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {
                            Toast.makeText(ThongTinKHActivity.this, "Failed API", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


    }
    public void saveOrder(OrderRequestDTO orderRequestDTO){


    }
}
