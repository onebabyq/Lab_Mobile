package com.example.a69_18028791_tranvuhoangson;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtRegister;
    EditText etEmail,etPassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){

            //startActivity(new Intent(MainActivity.this,TrangChuActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();
                checkLogin(email,pass);
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });


    }
    public void checkLogin(String email,String pass){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    if(pass.length()<6){
                        etPassword.setError("Password qua ngan, toi thieu phai 6 ki tu");
                    }else Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(MainActivity.this,"Login Susscessfully", Toast.LENGTH_SHORT);
                    startActivity(new Intent(MainActivity.this,ManagerActivity.class));
                }
            }
        });
    }

}