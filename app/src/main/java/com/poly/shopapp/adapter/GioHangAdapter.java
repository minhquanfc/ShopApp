package com.poly.shopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.poly.shopapp.R;
import com.poly.shopapp.model.GioHang;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangHolder> {
    Context context;
    List<GioHang> gioHangList;
    int tongtien =0;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public GioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.giohang_item,parent,false);
        return new GioHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.tv_tensp1.setText("Tên: "+gioHang.getTenSanpham());
        holder.tv_giasp1.setText("Giá: "+String.valueOf(gioHang.getGiaSanpham()));
        holder.tv_ngaymua.setText("Ngày: "+String.valueOf(gioHang.getNgay()));
        holder.tv_soluongsp.setText("Số lượng: "+gioHang.getSoLuong());
        holder.tv_tongtiensp.setText("Tổng tiền: "+String.valueOf(gioHang.getTongTien()));

        //intent tong tien
        tongtien = tongtien + gioHangList.get(position).getTongTien();
        Intent intent = new Intent("Tongtien");
        intent.putExtra("tong",tongtien);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        //xoa
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }
}
