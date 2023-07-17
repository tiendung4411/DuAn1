package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duan1.Model.SanPham;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Button btnTiepTuc = findViewById(R.id.btnTiepTuc);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, CartActivity.class));
            }
        });

        ArrayList<SanPham> listsp = new ArrayList<>();
        listsp.add(new SanPham(R.drawable.caffe, "caffe", "35.000$"));
        listsp.add(new SanPham(R.drawable.coffeeden, "coffeeden", "15.000$"));
        listsp.add(new SanPham(R.drawable.coffeesua, "caffesua", "15.000$"));
        listsp.add(new SanPham(R.drawable.coffee, "coffee", "15.000$"));

    }
}