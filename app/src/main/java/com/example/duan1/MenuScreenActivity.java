package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.duan1.adapter.ViewPagerAdapter;
import com.example.duan1.model.TokenModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;

public class MenuScreenActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private ViewPager2 mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        ImageSlider imageSlider =  findViewById(R.id.imgSlider);
        FloatingActionButton orderButton = findViewById(R.id.cartBtn);
        SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        String token = sharedPreferences.getString("fcm_token", null);
        Log.d("MyTag", "Token: " + token);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the quantities from the shared data model
                Map<Integer, Integer> quantities = SharedOrderData.getInstance().getAllQuantities();

                // Log the contents of the quantities map
                Log.d("MenuActivity1", "Quantities: " + quantities.toString());

                if (quantities.isEmpty()) {
                    Toast.makeText(MenuScreenActivity.this, "Quantities are empty!", Toast.LENGTH_SHORT).show();
                    return; // If quantities are empty, exit early
                }

                // Proceeding to the CartActivity
                Intent intent = new Intent(MenuScreenActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        mNavigationView  = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.viewPager);
        setUpViewPager();

        mNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.coffeeNav:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.juiceNav:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.milkteaNav:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.dessertNav:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                MenuItem item = null;
                switch (position) {
                    case 0:
                        item = mNavigationView.getMenu().findItem(R.id.coffeeNav);
                        break;
                    case 1:
                        item = mNavigationView.getMenu().findItem(R.id.juiceNav);
                        break;
                    case 2:
                        item = mNavigationView.getMenu().findItem(R.id.milkteaNav);
                        break;
                    case 3:
                        item = mNavigationView.getMenu().findItem(R.id.dessertNav);
                        break;
                }
                if (item != null) {
                    item.setChecked(true);
                }
            }
        });
    }
}
