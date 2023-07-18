package com.example.duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ViewHolder.SanPhamViewHolder;
import com.example.duan1.model.SanPham;

import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamViewHolder> {
    Context context;
    ArrayList<SanPham> listsp;

    public SanPhamAdapter(Context context, ArrayList<SanPham> listsp) {
        this.context = context;
        this.listsp = listsp;
    }

    @NonNull
    @Override
    public SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SanPhamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamViewHolder holder, int position) {
        holder.name.setText(listsp.get(position).getName());
        holder.tax.setText(listsp.get(position).getTax());
        holder.pic.setImageResource(listsp.get(position).getPic());
    }

    @Override
    public int getItemCount() {
        return listsp.size();
    }
}
