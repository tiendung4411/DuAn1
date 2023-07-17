package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan1.Adapter.CartAdapter;
import com.example.duan1.Model.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);


        ArrayList<Cart> listCart = new ArrayList<>();
        listCart.add(new Cart("caffe", "35.000$","1", "35.000", R.drawable.caffe));
        listCart.add(new Cart("caffeden", "15.000$","1", "15.000", R.drawable.coffeeden));
        listCart.add(new Cart("caffesua", "15.000$","1", "15.000", R.drawable.coffeesua));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter(getApplicationContext(), listCart));
    }
}