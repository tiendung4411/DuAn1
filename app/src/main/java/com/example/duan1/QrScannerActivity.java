package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1.model.Ban;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QrScannerActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton scanBtn;
    ImageSlider imageSlider;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mh_hainut);
        imageSlider =    findViewById(R.id.imgSlider1);
        // referencing and initializing the button and textviews
        scanBtn = findViewById(R.id.btnQR);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
//
        slideModels.add(new SlideModel(R.drawable.img_1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        // adding listener to the button
     //   scanBtn.setOnClickListener(this);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ;
                startActivity(new Intent(QrScannerActivity.this, MenuScreenActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setCaptureActivity(CustomScannerActivity.class); // set custom scanner activity
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                  try {
                    String qrContent = intentResult.getContents();
                    JSONObject jsonObject = new JSONObject(qrContent);
                    int idBan = jsonObject.getInt("idBan");
                    int viTri = jsonObject.getInt("viTri");

                    // Set the idBan and viTri in SharedTableData
                    SharedTableData sharedTableData = SharedTableData.getInstance();
                    sharedTableData.setIdBan(idBan);
                    sharedTableData.setViTri(viTri);

                    startActivity(new Intent(QrScannerActivity.this, MenuScreenActivity.class));


                } catch (JSONException e) {
                    Toast.makeText(this, "Invalid QR code format", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
