package com.example.duan1;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;


import androidx.appcompat.app.AppCompatActivity;


public class LoadingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mh_loading);
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000); // Set the duration of the animation in milliseconds (1 second in this example)
        animation.setFillAfter(true);
        View rootView = getWindow().getDecorView().getRootView();
        rootView.startAnimation(animation);

        // Delay execution and start the next activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this,QrScannerActivity.class));
                finish();
            }
        }, 3000);
    }


}

