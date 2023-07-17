package com.example.duan1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView ImvPic;
    public TextView TvName;
    public TextView TvGia;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        ImvPic = itemView.findViewById(R.id.ImvPic);
        TvName = itemView.findViewById(R.id.TvName);
        TvGia = itemView.findViewById(R.id.TvGia);

    }
}
