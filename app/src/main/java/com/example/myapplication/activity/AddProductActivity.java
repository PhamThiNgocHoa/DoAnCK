package com.example.myapplication.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.ImageUploadService;
import com.example.myapplication.network.ProductService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.ProductRequestDTO;
import com.example.myapplication.network.dto.response.UrlResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    private TextView category, name, price, image, detail;
    private Button addButton;
    private ProductService productService;
    private ImageView back;
    private Button btnSelectImage;
    private ImageUploadService imageUploadService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        category = findViewById(R.id.category_name);
        name = findViewById(R.id.fullname_info);
        price = findViewById(R.id.price_info);
        detail = findViewById(R.id.detail);
        addButton = findViewById(R.id.updateButton);
        image = findViewById(R.id.image_url);
        btnSelectImage = findViewById(R.id.choose_image_btn);
        back = findViewById(R.id.back);

        btnSelectImage.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(AddProductActivity.this, ProductListActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            ProductRequestDTO requestDTO = new ProductRequestDTO(
                    name.getText().toString(),
                    image.getText().toString(),
                    Integer.parseInt(price.getText().toString()),
                    category.getText().toString(),
                    detail.getText().toString()
            );
            addProduct(requestDTO);
        });
    }

    private void addProduct(ProductRequestDTO requestDTO) {
        productService = RetrofitClient.getProductService();
        productService.addProduct(requestDTO).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddProductActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddProductActivity.this, ProductListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddProductActivity.this, "Lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("Response Error", "Response code: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                Toast.makeText(AddProductActivity.this, "Thêm Không Thành Công", Toast.LENGTH_SHORT).show();
                Log.e("Error", "Error: " + throwable.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        File tempFile = new File(getCacheDir(), "temp_image.jpg");
                        OutputStream outputStream = Files.newOutputStream(tempFile.toPath());
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = inputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, length);
                        }
                        inputStream.close();
                        outputStream.close();

                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), tempFile);
                        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("file", tempFile.getName(), requestBody);

                        imageUploadService = RetrofitClient.getImageUpload();
                        imageUploadService.uploadImage(imagePart).enqueue(new Callback<UrlResponse>() {
                            @Override
                            public void onResponse(Call<UrlResponse> call, Response<UrlResponse> response) {
                                if (response.isSuccessful()) {
                                    UrlResponse responseBody = response.body();
                                    String imageUrl = responseBody.getUrl();
                                    image.setText(imageUrl);
                                    Log.e("Link", "Link: " + imageUrl);
                                } else {
                                    Toast.makeText(AddProductActivity.this, "Upload Error: " + response.code(), Toast.LENGTH_SHORT).show();
                                    Log.e("Upload Error", "Response code: " + response.code() + " - " + response.message());
                                    try {
                                        Log.e("Upload Error", "Error body: " + response.errorBody().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UrlResponse> call, Throwable throwable) {
                                Toast.makeText(AddProductActivity.this, "Không gọi được API", Toast.LENGTH_SHORT).show();
                                Log.e("Error", "Error: " + throwable.getMessage());
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(AddProductActivity.this, "Không thể đọc tệp ảnh", Toast.LENGTH_SHORT).show();
                        Log.e("File Error", "IOException: " + e.getMessage());
                    }
                } else {
                    Toast.makeText(AddProductActivity.this, "Không thể chọn tệp ảnh", Toast.LENGTH_SHORT).show();
                    Log.e("URI Error", "SelectedImageUri is null");
                }
            }
        }
    }

}
