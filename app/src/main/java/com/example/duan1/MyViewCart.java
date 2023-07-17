package com.example.duan1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewCart extends RecyclerView.ViewHolder {
public TextView TvName, TvTien, TvsoLuong, TvtongTien;
public ImageView caffe, coffee, coffeeden;

    public MyViewCart(@NonNull View itemView) {
        super(itemView);
        TvName = itemView.findViewById(R.id.TvName);
        TvTien = itemView.findViewById(R.id.TvTien);
        TvsoLuong = itemView.findViewById(R.id.TvsoLuong);
        TvtongTien = itemView.findViewById(R.id.TvtongTien);
        caffe = itemView.findViewById(R.id.ImvPic);
        coffee = itemView.findViewById(R.id.ImbPlus);
        coffeeden = itemView.findViewById(R.id.ImbTru);

    }


}
