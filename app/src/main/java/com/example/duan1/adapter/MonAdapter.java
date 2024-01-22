package com.example.duan1.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.AppUtils;
import com.example.duan1.R;
import com.example.duan1.model.Mon;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import com.example.duan1.SharedOrderData;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.ViewHolder> {
    ArrayList<Mon> list;
    private Map<Integer, Integer> quantities = new HashMap<>();
    private SharedOrderData sharedOrderData = SharedOrderData.getInstance();

    public MonAdapter(ArrayList<Mon> list, Context context) {
        this.list = list != null ? list : new ArrayList<>();
        this.context = context;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_mon, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Mon mon = list.get(position);
        holder.ten.setText(mon.getTenMon());

        String formattedNumber = formatNumberWithDots((int)mon.getGia());
        holder.gia.setText(formattedNumber+" VND");
        holder.mota.setText(mon.getMoTa());
        Picasso.get().load(mon.getImgUrl()).into(holder.pic);

        final int[] quantity = {sharedOrderData.getQuantity(mon.getIdMon())};
        holder.quantity.setText(String.valueOf(quantity[0]));
        if(mon.getSoLuong()==0){
            holder.quantity.setVisibility(View.GONE);
            holder.addBtn.setVisibility(View.GONE);
            holder.addBtn.setClickable(true);
            holder.gia.setText("Hết món");
            holder.gia.setTextColor(Color.parseColor("#E3242B"));
        }
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    quantity[0]++;
                    sharedOrderData.setQuantity(mon.getIdMon(), quantity[0]);
                    holder.quantity.setText(String.valueOf(quantity[0]));
                    holder.subtractBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && quantity[0] > 0) {
                    quantity[0]--;
                    sharedOrderData.setQuantity(mon.getIdMon(), quantity[0]);
                    holder.quantity.setText(String.valueOf(quantity[0]));
                    if (quantity[0] == 0) {
                        holder.subtractBtn.setVisibility(View.GONE);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ten, mota, gia, quantity;
        ImageView pic, addBtn, subtractBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.ten);
            mota = itemView.findViewById(R.id.mota);
            gia = itemView.findViewById(R.id.gia);
            pic = itemView.findViewById(R.id.pic);
            quantity = itemView.findViewById(R.id.quantity); // Assuming you have a TextView for quantity
            addBtn = itemView.findViewById(R.id.add);       // Assuming you have an ImageView for add button
            subtractBtn = itemView.findViewById(R.id.subtract); // Assuming you have an ImageView for subtract button
            subtractBtn.setVisibility(View.GONE); // Initially hide the subtract button
        }

    }
    public static String formatNumberWithDots(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        return numberFormat.format(number);
    }
}
