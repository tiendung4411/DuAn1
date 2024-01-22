package com.example.duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.adapter.MonAdapter;
import com.example.duan1.model.Mon;
import com.example.duan1.service.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoffeeFragment extends Fragment {
    ArrayList<Mon> list;

    SearchView sv;
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coffee, container, false);

        list = new ArrayList<>();
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Call<ArrayList<Mon>> response =    service.GetDS();

        // get danh sach dieu kien
        service.GetDSDK(1).enqueue(new Callback<ArrayList<Mon>>() {
            @Override
            public void onResponse(Call<ArrayList<Mon>> call, Response<ArrayList<Mon>> response) {
                ArrayList<Mon> list = response.body();
                Mon mon1 = list.get(0);
                Toast.makeText(getContext(),"ImgUrl:"+mon1.getMoTa(), Toast.LENGTH_SHORT).show();
                recycler = (view).findViewById(R.id.CoffeeRecyclerView);
                sv = (view).findViewById(R.id.sv);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recycler.setLayoutManager(manager);

                loadData(list);

                //sv
                sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        ArrayList<Mon> list1 = new ArrayList<>();
                        for (Mon mon : list){
                            if (mon.getTenMon().toLowerCase().contains(newText.toLowerCase())){
                                list1.add(mon);
                            }
                        }
                        loadData(list1);
                        return false;
                    }
                });
            }

            private  void loadData(ArrayList<Mon> list2){
                MonAdapter adapter = new MonAdapter( list2,getContext());
                recycler.setAdapter(adapter);
            }


            @Override
            public void onFailure(Call<ArrayList<Mon>> call, Throwable t) {

            }
        });
        return view;

    }

}