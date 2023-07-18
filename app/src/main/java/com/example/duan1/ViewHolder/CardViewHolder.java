package com.example.duan1.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;

public class CardViewHolder extends RecyclerView.ViewHolder{
    public TextView name, sl, tien, tongtien;
    public ImageView img, img1,img2;

    public CardViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        sl = itemView.findViewById(R.id.txtsl);
        tien = itemView.findViewById(R.id.tien);
        tongtien = itemView.findViewById(R.id.tongtien);
        img = itemView.findViewById(R.id.pic);
        img1 = itemView.findViewById(R.id.ibCong);
        img2 = itemView.findViewById(R.id.ibTru);
    }
}
