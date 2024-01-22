package com.example.duan1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.adapter.CartAdapter;
import com.example.duan1.model.Mon;
import com.example.duan1.service.APIService;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    TextView txtTongSoLuong, txtTongTien, txtViTriBan;
    Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtTongSoLuong = findViewById(R.id.tongSoLuong); // Assuming the IDs are correct
        txtTongTien = findViewById(R.id.tongTien);
        txtViTriBan = findViewById(R.id.viTriBan);
        confirmButton = findViewById(R.id.confirm_button);

        // Initialize the total quantity and total price as 0
        txtTongSoLuong.setText("0 items");
        txtTongTien.setText("0 VND");

        // Get the selected items from SharedOrderData
        HashMap<Integer, Integer> selectedItems = SharedOrderData.getInstance().getAllQuantities();

        // Create and set the adapter
        ArrayList<Mon> monList = new ArrayList<>();
        cartAdapter = new CartAdapter(monList, selectedItems);
        recyclerView.setAdapter(cartAdapter);

        // Fetch Mon details for each selected item with quantity > 0
        for (Map.Entry<Integer, Integer> entry : selectedItems.entrySet()) {
            if (entry.getValue() > 0) {
                fetchMonDetails(entry.getKey(), entry.getValue());
            }
        }

        // Set click listener for confirm button to create bill
        confirmButton.setOnClickListener(v -> createBill());
    }

    private void fetchMonDetails(int idMon, int quantity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<ArrayList<Mon>> call = apiService.getMonById(idMon);

        call.enqueue(new Callback<ArrayList<Mon>>() {
            @Override
            public void onResponse(Call<ArrayList<Mon>> call, Response<ArrayList<Mon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Mon mon = response.body().get(0);
                    if (quantity > 0) {
                        Log.d("CartActivity", "Fetched Mon: " + mon.toString());
                        cartAdapter.addMon(mon);
                        updateTotalInfo(mon, quantity);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mon>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error fetching Mon details: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNotification() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SharedTableData sharedTableData = SharedTableData.getInstance();
        int idBan = sharedTableData.getIdBan();

        Map<String, Object> payload = new HashMap<>();
        payload.put("to", "YOUR_FCM_TOKEN_HERE");

        Map<String, String> notificationData = new HashMap<>();
        notificationData.put("title", "Order has been made");
        notificationData.put("body", "Pending from: Table " + idBan);
        payload.put("notification", notificationData);
        payload.put("idBan", idBan); // Include the idBan

        APIService apiService = retrofit.create(APIService.class);
        Call<Void> call = apiService.sendNotificationWithIdBan(payload);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Notification", "Notification sent successfully");
                } else {
                    Log.e("Notification", "Failed to send notification");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Notification", "Error sending notification", t);
            }
        });
    }

    private void updateTotalInfo(Mon mon, int quantity) {
        int tongSoLuong = Integer.parseInt(txtTongSoLuong.getText().toString().split(" ")[0]); // Assuming it has a format like "5 items"
        double tongTien = Double.parseDouble(txtTongTien.getText().toString().replace(".", "").split(" ")[0]); // Assuming it has a format like "32.000 VND"

        tongSoLuong += quantity;
        tongTien += quantity * mon.getGia();
        txtViTriBan.setText("Bàn: "+ SharedTableData.getInstance().getIdBan());
        txtTongSoLuong.setText(tongSoLuong+" "); // You can change "items" to the desired unit

        String formattedNumber = formatNumberWithDots((int)tongTien);
        txtTongTien.setText(formattedNumber + " VND"); // You can change "VND" to the desired currency symbol
    }

    public static String formatNumberWithDots(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        return numberFormat.format(number);
    }

    private void createBill() {
        sendNotification();
        HashMap<Integer, Integer> allQuantities = SharedOrderData.getInstance().getAllQuantities();
        HashMap<Integer, Integer> quantitiesToCreateBill = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : allQuantities.entrySet()) {
            if (entry.getValue() > 0) {
                quantitiesToCreateBill.put(entry.getKey(), entry.getValue());
            }
        }

        HashMap<String, Object> payload = new HashMap<>();
        payload.put("idBan", SharedTableData.getInstance().getIdBan());
        payload.put("selectedItems", quantitiesToCreateBill);
        Log.d("CartActivity", "Payload: " + payload.toString());

        Log.d("CartActivity", "Quantities to create bill: " + quantitiesToCreateBill.toString());
        if (quantitiesToCreateBill.isEmpty()) {
            Toast.makeText(CartActivity.this, "No items to create bill!", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Void> call = apiService.createBill(payload);


        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(CartActivity.this, "Bill created successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("CartActivity", "Failed to create bill. Response code: " + response.code() + ", Response body: " + response.errorBody());
                    Toast.makeText(CartActivity.this, "Failed to create bill!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error creating bill: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        startActivity(new Intent(CartActivity.this, Screensaver.class));
    }

}


