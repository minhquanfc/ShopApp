package com.poly.shopapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.shopapp.adapter.GioHangAdapter;
import com.poly.shopapp.model.Danhmuc;
import com.poly.shopapp.model.GioHang;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {

    TextView tv_tongtien;
    RecyclerView recyclerView;
    Button btndathang;
    List<GioHang> gioHangList;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        tv_tongtien=findViewById(R.id.tv_tongtien1);
        recyclerView =findViewById(R.id.rcview_giohang);
        btndathang=findViewById(R.id.btndathang);
        db=FirebaseFirestore.getInstance();
        gioHangList = new ArrayList<>();
        GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this,gioHangList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GioHangActivity.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        db.collection("Giohang")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                GioHang gioHang = document.toObject(GioHang.class);
                                gioHangList.add(gioHang);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("Tongtien"));
    }
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int bill = intent.getIntExtra("tong",0);
            tv_tongtien.setText(bill+"VND");
        }
    };
}