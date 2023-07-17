package com.example.duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.Model.Cart;
import com.example.duan1.MyViewCart;
import com.example.duan1.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<MyViewCart> {

    Context context;
    ArrayList<Cart> listCart;

    public CartAdapter(Context context, ArrayList<Cart> listCart) {
        this.context = context;
        this.listCart = listCart;
    }
    @NonNull
    @Override
    public MyViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new MyViewCart(LayoutInflater.from(context).inflate(R.layout.item_cartview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewCart holder, int position) {
        holder.TvName.setText(listCart.get(position).getTvName());
        holder.TvsoLuong.setText(listCart.get(position).getTvsoLuong());
        holder.TvTien.setText(listCart.get(position).getTvTien());
        holder.TvtongTien.setText(listCart.get(position).getTvtongTien());
        holder.caffe.setImageResource(listCart.get(position).getCaffe());
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }
}
