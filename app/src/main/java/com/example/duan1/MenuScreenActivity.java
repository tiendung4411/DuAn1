package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.adapter.SanPhamAdapter;
import com.example.duan1.model.SanPham;

import java.util.ArrayList;

public class MenuScreenActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton btnBack;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        //recyclerview+ search
        recyclerView = findViewById(R.id.recyclerview);
        SearchView svsp = findViewById(R.id.svSp);
        Button tiectuc = findViewById(R.id.tieptuc);

        tiectuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuScreenActivity.this, CartActivity.class));
            }
        });

        //back button
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MenuScreenActivity.this,OrderScreenActivity.class);
//                startActivity(intent);
                finish();
            }
        });

        //next button
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(OrderScreenActivity.this,MenuScreenActivity.class);
//                startActivity(intent);
//            }
//        });

        //list danh sach sp+adapter
        ArrayList<SanPham> listsp = new ArrayList<>();
        listsp.add(new SanPham("pesi", "15000$", R.mipmap.img));
        listsp.add(new SanPham("canva", "30000$", R.mipmap.img));
        listsp.add(new SanPham("sinh to", "15000$", R.mipmap.img));
        listsp.add(new SanPham("nuoc dua", "15000$", R.mipmap.img));
        listsp.add(new SanPham("pesi", "15000$", R.mipmap.img));
        listsp.add(new SanPham("canva", "30000$", R.mipmap.img));
        listsp.add(new SanPham("sinh to", "15000$", R.mipmap.img));
        listsp.add(new SanPham("nuoc dua", "15000$", R.mipmap.img));
        listsp.add(new SanPham("pesi", "15000$", R.mipmap.img));
        listsp.add(new SanPham("canva", "30000$", R.mipmap.img));
        listsp.add(new SanPham("sinh to", "15000$", R.mipmap.img));
        listsp.add(new SanPham("nuoc dua", "15000$", R.mipmap.img));
        loadData(listsp);

        //search
        svsp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<SanPham> listsv = new ArrayList<>();
                for (SanPham sanPham : listsp){
                    if (sanPham.getName().toLowerCase().contains(newText.toLowerCase())
                        || sanPham.getTax().toLowerCase().contains(newText.toLowerCase())){
                        listsv.add(sanPham);
                    }
                }
                loadData(listsv);
                return false;
            }
        });
    }

    public void loadData(ArrayList<SanPham> list){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SanPhamAdapter(getApplicationContext(),list));
    }

}
