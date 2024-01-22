package com.example.duan1;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.model.TokenModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "Main app";
    private static final String CHANNEL_ID = "101";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mh_loading);
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000); // Set the duration of the animation in milliseconds (1 second in this example)
        animation.setFillAfter(true);
        View rootView = getWindow().getDecorView().getRootView();
        rootView.startAnimation(animation);
        int idBan = 5;
        int viTri = 1;
        getToken();
        createNotificationChannel();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    String token = task.getResult();
                    TokenModel tokenModel = new TokenModel();
                    tokenModel.setToken(token);

                    // Save the token using SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fcm_token", token);
                    editor.apply();
                });
        // Set the idBan and viTri in SharedTableData
        SharedTableData sharedTableData = SharedTableData.getInstance();
        sharedTableData.setIdBan(idBan);
        sharedTableData.setViTri(viTri);
        // Delay execution and start the next activity
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingActivity.this,QrScannerActivity.class));
                finish();
            }
        }, 3000);
    }
    public void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>(){
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()) {
                    Log.d("getToken", "onComplete: Failed to get token");
                }
                String token = task.getResult();
                Log.d("getToken","onComplete:"+token);
            }


        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "firebaseNotifChannel";
            String description = "Receive Notification";
            int importance = NotificationManager.IMPORTANCE_HIGH; // Set importance to high
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            channel.setShowBadge(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

