package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderScreenActivity extends AppCompatActivity {
    ImageView btnBack,btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mh_khachhang_thongtin);
        btnBack=(ImageView)findViewById(R.id.btnBack);
        btnNext=(ImageView)findViewById(R.id.btnNext);
        //back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(OrderScreenActivity.this,QrScannerActivity.class);
//                startActivity(intent);
                    finish();
            }
        });

        //next button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderScreenActivity.this,MenuScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
