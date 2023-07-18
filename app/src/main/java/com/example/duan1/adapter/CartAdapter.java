package com.example.duan1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.R;
import com.example.duan1.ViewHolder.CardViewHolder;
import com.example.duan1.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CardViewHolder> {
    Context context;
    ArrayList<Cart> listCart;

    public CartAdapter(Context context, ArrayList<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.img.setImageResource(listCart.get(position).getImg());
        holder.name.setText(listCart.get(position).getName());
        holder.sl.setText(listCart.get(position).getSl());
        holder.tien.setText(listCart.get(position).getTien());
        holder.tongtien.setText(listCart.get(position).getTongtien());
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }
}
