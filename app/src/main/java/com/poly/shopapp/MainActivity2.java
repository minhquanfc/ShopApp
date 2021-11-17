package com.poly.shopapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.poly.shopapp.model.Shop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_ten1, tv_gia1, tv_mota1, tv_soluong;
    ImageView anh;
    ProgressBar progressBar;
    Button btnmuangay;
    ImageView btncong, btntru;
    int soluong = 1;
    int tonggia = 0;

    FirebaseFirestore db;
    FirebaseAuth auth;
    List<Shop> shopList;
    Shop shop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anh = findViewById(R.id.anh1);
        tv_ten1 = findViewById(R.id.tv_ten1);
        tv_gia1 = findViewById(R.id.tv_gia1);
        tv_mota1 = findViewById(R.id.tv_mota1);
        progressBar = findViewById(R.id.probar2);
        btnmuangay = findViewById(R.id.btnmuangay);
        progressBar.setVisibility(View.VISIBLE);
        shopList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        btncong = findViewById(R.id.btncong);
        btntru = findViewById(R.id.btntru);
        tv_soluong = findViewById(R.id.tv_soluong);

        final Object obj = getIntent().getSerializableExtra("chitiet");
        if (obj instanceof Shop) {
            shop = (Shop) obj;
        }

        if (shop != null) {
            Glide.with(MainActivity2.this).load(shop.getAnh()).into(anh);
            tv_ten1.setText(shop.getTen());
            tv_gia1.setText(String.valueOf(shop.getGia()));
            tv_mota1.setText(shop.getMota());
            progressBar.setVisibility(View.GONE);

            tonggia = shop.getGia() * soluong;
        }

        btnmuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thoigian, ngay;
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                ngay = sdf.format(calendar.getTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM:mm:ss a");
                thoigian = simpleDateFormat.format(calendar.getTime());

                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("tenSanpham", tv_ten1.getText().toString());
                cartMap.put("giaSanpham", Integer.valueOf(tv_gia1.getText().toString()));
                cartMap.put("thoiGian", thoigian);
                cartMap.put("ngay", ngay);
                cartMap.put("soLuong", tv_soluong.getText().toString());
                cartMap.put("tongTien", tonggia);

                db.collection("Giohang") // user nao mua document(auth.getCurrentUser().getUid()).collection("User")
                        .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(MainActivity2.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong < 10) {
                    soluong++;
                    tv_soluong.setText(String.valueOf(soluong));
                    tonggia = shop.getGia() * soluong;
                }
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong > 1) {
                    soluong--;
                    tv_soluong.setText(String.valueOf(soluong));
                }
            }
        });
    }
}