package com.poly.shopapp.adapter;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.shopapp.R;
import com.poly.shopapp.model.GioHang;

import java.util.List;

public class GioHangHolder extends RecyclerView.ViewHolder {
    TextView tv_tensp1,tv_giasp1,tv_ngaymua,tv_soluongsp,tv_tongtiensp;
    Button btndel;
    public GioHangHolder(@NonNull View itemView) {
        super(itemView);
        tv_tensp1=itemView.findViewById(R.id.tv_tensp1);
        tv_giasp1=itemView.findViewById(R.id.tv_giasp1);
        tv_ngaymua=itemView.findViewById(R.id.tv_ngaymua);
        tv_soluongsp=itemView.findViewById(R.id.tv_soluongsp);
        tv_tongtiensp=itemView.findViewById(R.id.tv_tongtiensp);
        btndel=itemView.findViewById(R.id.btndel);
    }
}
