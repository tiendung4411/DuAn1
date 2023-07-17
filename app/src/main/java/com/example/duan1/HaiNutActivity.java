package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HaiNutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hai_nut);

        ImageButton btn_QR = findViewById(R.id.btn_QR);
        ImageButton btn_datHang = findViewById(R.id.btn_datHang);

        btn_datHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HaiNutActivity.this, MenuActivity.class));
            }
        });
    }
}