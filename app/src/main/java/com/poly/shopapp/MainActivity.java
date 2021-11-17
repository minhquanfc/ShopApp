package com.poly.shopapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.shopapp.adapter.DanhMucAdapter;
import com.poly.shopapp.adapter.ShopAdapter;
import com.poly.shopapp.adapter.SlidePhotoAdapter;
import com.poly.shopapp.model.Danhmuc;
import com.poly.shopapp.model.Shop;
import com.poly.shopapp.model.SlidePhoto;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView1;
    ProgressBar progressBar;
    TextView tv_xemthem;
    List<Shop> shopList;
    //lay du lieu tu cloud
    FirebaseFirestore db;
    ShopAdapter adapter;
    //lay du lieu danhmuc
    DanhMucAdapter danhMucAdapter;
    List<Danhmuc> danhmucList;
    //slide anh
    ViewPager viewPager;
    private CircleIndicator circleIndicator;
    List<SlidePhoto> mlistphoto;
    private Handler handler = new Handler();
    private Runnable runnable;

    //
    ImageView imgDrawer,imgGiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcview);
        recyclerView1 = findViewById(R.id.rcview1);
        tv_xemthem=findViewById(R.id.tv_xemthem);
        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        progressBar = findViewById(R.id.probar);
        imgDrawer = findViewById(R.id.drawerbar);
        imgGiohang = findViewById(R.id.giohang);
        progressBar.setVisibility(View.VISIBLE);

        //hien thi anh slide
        mlistphoto= getListPhoto();
        SlidePhotoAdapter photoAdapter = new SlidePhotoAdapter(getListPhoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        //auto slide
        autoslidePhoto();

        //lay du lieu tu rt database
        db = FirebaseFirestore.getInstance();
        shopList = new ArrayList<>();
        adapter = new ShopAdapter(MainActivity.this,shopList);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        getDataSanPham();

        //lay du lieu danh muc
        danhmucList = new ArrayList<>();
        danhMucAdapter = new DanhMucAdapter(MainActivity.this,danhmucList);
        recyclerView1.setAdapter(danhMucAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        getDatadanhmuc();
        //xem them
        tv_xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        //gio hang
        imgGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDataSanPham() {
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

    private void getDatadanhmuc() {
        db.collection("danh_muc")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Danhmuc danhmuc = document.toObject(Danhmuc.class);
                                danhmucList.add(danhmuc);
                                danhMucAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private List<SlidePhoto> getListPhoto(){
        List<SlidePhoto> photoList = new ArrayList<>();
        photoList.add(new SlidePhoto(R.drawable.anhslide1));
        photoList.add(new SlidePhoto(R.drawable.anhslide2));
        photoList.add(new SlidePhoto(R.drawable.anhslide3));
        photoList.add(new SlidePhoto(R.drawable.anhslide4));
        photoList.add(new SlidePhoto(R.drawable.anhslide5));
        return  photoList;
    }
    private void autoslidePhoto(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem()==mlistphoto.size()-1) {
                    viewPager.setCurrentItem(0);
                }else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        };
        handler.postDelayed(runnable,2000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.searchview);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}