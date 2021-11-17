package com.poly.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.shopapp.model.User;

public class Dangky extends AppCompatActivity {
    EditText mail,pass;
    Button btndangky;
    private FirebaseAuth mAuth;
    TextView tvdangnhap,sdt,diachi;
    FirebaseDatabase database;
    DatabaseReference reference;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        pass = findViewById(R.id.ed_pass1);
        mail =findViewById(R.id.ed_email1);
        btndangky =findViewById(R.id.btndangky);
        tvdangnhap =findViewById(R.id.tv_dangnhap);
        sdt =findViewById(R.id.ed_sdt);
        diachi =findViewById(R.id.ed_diachi);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky();
            }
        });
    }

    private void dangky() {
        String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String sdt1 = sdt.getText().toString().trim();
        String diachi1 = diachi.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(email,password,sdt1,diachi1);
                    database.getReference("User").child(mAuth.getCurrentUser().getUid()).setValue(user);//lay theo id hien tai
//                    database.getReference("User").child(sdt1).setValue(user); //lay id theo sdt
                    Toast.makeText(Dangky.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Dangky.this,Login.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Dangky.this, "Dang ky ko thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}