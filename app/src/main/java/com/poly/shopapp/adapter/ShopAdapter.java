package com.poly.shopapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poly.shopapp.MainActivity2;
import com.poly.shopapp.R;
import com.poly.shopapp.model.Shop;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {
    Context context;
    List<Shop> shopList;

    public ShopAdapter(Context context, List<Shop> shopList) {
        this.context = context;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsanpham,parent,false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, @SuppressLint("RecyclerView") int position) {
        Shop shop = shopList.get(position);
        Glide.with(holder.anh.getContext()).load(shop.getAnh()).into(holder.anh);
        holder.tv_tensp.setText(shop.getTen());
        holder.tv_gia.setText(String.valueOf(shop.getGia()));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("chitiet",shopList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }
}
