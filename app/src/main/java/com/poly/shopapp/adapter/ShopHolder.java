package com.poly.shopapp.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.shopapp.R;

public class ShopHolder extends RecyclerView.ViewHolder {
    ImageView anh;
    TextView tv_tensp,tv_gia;
    CardView click;
    public ShopHolder(@NonNull View itemView) {
        super(itemView);
        anh = itemView.findViewById(R.id.anh);
        tv_tensp = itemView.findViewById(R.id.tv_tensp);
        tv_gia = itemView.findViewById(R.id.tv_gia);
        click = itemView.findViewById(R.id.click);
    }
}
