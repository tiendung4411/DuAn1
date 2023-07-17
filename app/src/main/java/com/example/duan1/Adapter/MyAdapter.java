package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Model.SanPham;
import com.example.duan1.MyViewHolder;
import com.example.duan1.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;

    ArrayList<SanPham> listsp;

    public MyAdapter(Context context, ArrayList<SanPham> listsp) {
        this.context = context;
        this.listsp = listsp;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvName.setText(listsp.get(position).getTvName());
        holder.TvGia.setText(listsp.get(position).getTvGia());
        holder.ImvPic.setImageResource(listsp.get(position).getImvPic());

    }

    @Override
    public int getItemCount() {
        return listsp.size();
    }
}
