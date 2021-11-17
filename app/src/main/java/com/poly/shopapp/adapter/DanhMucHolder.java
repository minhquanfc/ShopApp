package com.poly.shopapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.shopapp.R;

public class DanhMucHolder extends RecyclerView.ViewHolder {
    TextView tv_ten_danhmuc;
    ImageView anh_danhmuc;
    CardView click;
    public DanhMucHolder(@NonNull View itemView) {
        super(itemView);
        tv_ten_danhmuc=itemView.findViewById(R.id.tv_ten_danhmuc);
        anh_danhmuc=itemView.findViewById(R.id.anh_danh_muc);
        click=itemView.findViewById(R.id.click_danhmuc);
    }
}
