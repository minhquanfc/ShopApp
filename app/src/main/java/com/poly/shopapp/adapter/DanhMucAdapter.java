package com.poly.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.poly.shopapp.R;
import com.poly.shopapp.ShowActivity;
import com.poly.shopapp.model.Danhmuc;

import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucHolder> {
    Context context;
    List<Danhmuc> danhmucList;

    public DanhMucAdapter(Context context, List<Danhmuc> danhmucList) {
        this.context = context;
        this.danhmucList = danhmucList;
    }

    @NonNull
    @Override
    public DanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danh_muc,parent,false);
        return new DanhMucHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucHolder holder, int position) {
        Danhmuc danhmuc = danhmucList.get(position);
        holder.tv_ten_danhmuc.setText(danhmuc.getTen());
        Glide.with(holder.anh_danhmuc).load(danhmuc.getAnh()).into(holder.anh_danhmuc);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("type",danhmucList.get(position).getLoai());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhmucList.size();
    }
}
