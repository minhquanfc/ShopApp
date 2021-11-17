package com.poly.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText ed_email,ed_pass;
    Button btnlogin;
    private FirebaseAuth mAuth;
    TextView tvdangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_pass = findViewById(R.id.ed_pass);
        ed_email =findViewById(R.id.ed_email);
        btnlogin =findViewById(R.id.btnlogin);
        tvdangky =findViewById(R.id.tv_dangky);
        mAuth = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        tvdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Dangky.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String mail = ed_email.getText().toString();
        String pass = ed_pass.getText().toString();
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Login ko thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}