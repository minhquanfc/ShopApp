package com.poly.shopapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.shopapp.adapter.ShopAdapter;
import com.poly.shopapp.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    List<Shop> shopList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ShopAdapter adapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        recyclerView = findViewById(R.id.rc_view_sanpham);
        progressBar=findViewById(R.id.probar_sanpham);
        db=FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        progressBar.setVisibility(View.VISIBLE);
        shopList=new ArrayList<>();
        adapter = new ShopAdapter(ShowActivity.this,shopList);
        recyclerView.setAdapter(adapter);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(ShowActivity.this,2);
        recyclerView.setLayoutManager(linearLayoutManager);
        //xem them
        if (type == null){
            db.collection("List_sanpham")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shop shop = document.toObject(Shop.class);
                                    shopList.add(shop);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        //hien thi danh sach
        if (type !=null && type.equalsIgnoreCase("dienthoai")){
            db.collection("List_sanpham").whereEqualTo("loai","dienthoai")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shop shop = document.toObject(Shop.class);
                                    shopList.add(shop);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        if (type !=null && type.equalsIgnoreCase("maytinh")){
            db.collection("List_sanpham").whereEqualTo("loai","maytinh")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shop shop = document.toObject(Shop.class);
                                    shopList.add(shop);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        if (type !=null && type.equalsIgnoreCase("dongho")){
            db.collection("List_sanpham").whereEqualTo("loai","dongho")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shop shop = document.toObject(Shop.class);
                                    shopList.add(shop);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        if (type !=null && type.equalsIgnoreCase("mayanh")){
            db.collection("List_sanpham").whereEqualTo("loai","mayanh")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Shop shop = document.toObject(Shop.class);
                                    shopList.add(shop);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
    }
}