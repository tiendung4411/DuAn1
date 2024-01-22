package com.example.duan1.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;

public class SanPhamViewHolder extends RecyclerView.ViewHolder{

    public ImageView pic;
    public TextView name;
    public TextView tax;
    public SanPhamViewHolder(@NonNull View itemView) {
        super(itemView);
        pic = itemView.findViewById(R.id.pic);
        name = itemView.findViewById(R.id.name);
        tax = itemView.findViewById(R.id.tax);
    }
}
