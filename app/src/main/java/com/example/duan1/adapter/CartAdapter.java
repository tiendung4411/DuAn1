package com.example.duan1.adapter;

import android.content.Context;
import android.util.Log;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private final ArrayList<Mon> monList;
    private final HashMap<Integer, Integer> quantities;

    public CartAdapter(ArrayList<Mon> monList, HashMap<Integer, Integer> quantities) {
        this.monList = monList;
        this.quantities = quantities;
       // Log.d("CartAdapter", "Mon List size: " + monList.size());
      //  Log.d("CartAdapter", "Quantities size: " + quantities.size());
        for (Mon mon : monList) {
            Log.d("CartAdapter", "Mon ID: " + mon.getIdMon() + ", Name: " + mon.getTenMon());
        }
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            Log.d("CartAdapter", "Quantity - Mon ID: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mon mon = monList.get(position);
        int quantity = quantities.get(mon.getIdMon());

        // Set the views with the appropriate data
        holder.nameTextView.setText(mon.getTenMon());

        String formattedNumber = formatNumberWithDots(mon.getGia());
        holder.priceTextView.setText(formattedNumber+" VND");
        holder.quantityTextView.setText(String.valueOf(quantity));
        Picasso.get().load(mon.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return monList.size();
    }

    public void addMon(Mon mon) {
        monList.add(mon);
        notifyDataSetChanged();
    }
    public static String formatNumberWithDots(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        return numberFormat.format(number);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final TextView quantityTextView;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.ten1);
            priceTextView = itemView.findViewById(R.id.tien1);
            quantityTextView = itemView.findViewById(R.id.soLuong);
            imageView = itemView.findViewById(R.id.pic);

        }
        private void updateTotalAmount(int totalAmount) {
            String formattedAmount = AppUtils.formatNumberWithDots(totalAmount);
            priceTextView.setText(formattedAmount);
        }

    }

}
