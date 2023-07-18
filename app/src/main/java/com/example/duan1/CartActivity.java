package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duan1.adapter.CartAdapter;
import com.example.duan1.model.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        ArrayList<Cart> listCart = new ArrayList<>();
        listCart.add(new Cart("Pesi", "1", "15000$","15000$", R.mipmap.img));
        listCart.add(new Cart("canva", "3", "15000$","45000$", R.mipmap.img));
        listCart.add(new Cart("nuoc dua", "4", "15000$","60000$", R.mipmap.img));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CartAdapter(getApplicationContext(), listCart));
    }
}