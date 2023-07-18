package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mhg extends AppCompatActivity {
    private ImageButton rectangle1,continue1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mhg);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        rectangle1 = (ImageButton) findViewById(R.id.rectangle_1);
        continue1 = (ImageButton) findViewById(R.id.continue_1);
        rectangle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mhg.this, inordering.class);
                startActivity(intent);
            }
        });
        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mhg.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
