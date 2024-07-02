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
import com.example.myapplication.network.CategoryService;
import com.example.myapplication.network.ImageUploadService;
import com.example.myapplication.network.RetrofitClient;
import com.example.myapplication.network.dto.request.CategoryRequestDTO;
import com.example.myapplication.network.dto.response.UrlResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryActivity extends AppCompatActivity {
    private static final int GALLERY_REQUEST_CODE = 1;
    private TextView category, image;
    private Button addButton, chooseImageButton;
    private CategoryService categoryService;
    private ImageView back;
    private ImageUploadService imageUploadService;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);
        category = findViewById(R.id.category_name);
        image = findViewById(R.id.img_info);
        addButton = findViewById(R.id.updateButton);
        back = findViewById(R.id.back);
        chooseImageButton = findViewById(R.id.btn_choose_img);

        chooseImageButton.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        });


        back.setOnClickListener(v -> {
            Intent intent = new Intent(AddCategoryActivity.this, CategoryListActivity.class);
            startActivity(intent);
        });
        addButton.setOnClickListener(v -> {
            CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO(category.getText().toString(), image.getText().toString());
            addCategory(categoryRequestDTO);
        });
    }

    private void addCategory(CategoryRequestDTO requestDTO) {
        categoryService = RetrofitClient.getCategoryService();
        categoryService.addCategory(requestDTO).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Toast.makeText(AddCategoryActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCategoryActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                Toast.makeText(AddCategoryActivity.this, "Thêm Khong Thành Công", Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(AddCategoryActivity.this, "Upload Error: " + response.code(), Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(AddCategoryActivity.this, "Không gọi được API", Toast.LENGTH_SHORT).show();
                                Log.e("Error", "Error: " + throwable.getMessage());
                            }
                        });

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(AddCategoryActivity.this, "Không thể đọc tệp ảnh", Toast.LENGTH_SHORT).show();
                        Log.e("File Error", "IOException: " + e.getMessage());
                    }
                } else {
                    Toast.makeText(AddCategoryActivity.this, "Không thể chọn tệp ảnh", Toast.LENGTH_SHORT).show();
                    Log.e("URI Error", "SelectedImageUri is null");
                }
            }
        }
    }
}
