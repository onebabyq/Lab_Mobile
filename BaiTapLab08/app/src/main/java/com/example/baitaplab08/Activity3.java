package com.example.baitaplab08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity3 extends AppCompatActivity {
    Button btn_register;
    TextView txtLogin;
    EditText edtYourName,edtEmail,edtPassword2,edtPassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        btn_register = (Button) findViewById(R.id.btn_register);
        txtLogin = (TextView) findViewById(R.id.txtDoNotHave);
        edtYourName = (EditText) findViewById(R.id.edtYourName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword2 = (EditText) findViewById(R.id.edtPassword2);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        auth = FirebaseAuth.getInstance();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity3.this,Activity2.class));
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword2.getText().toString();
                String pass2 = edtPassword.getText().toString();
                createAccount(email,pass,pass2);
            }
        });
    }

    public void createAccount(String email,String password,String repassword){
        if(password.equals(repassword)){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Activity3.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Activity3.this, "Dang ky khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Activity3.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Activity3.this, Activity4.class));
                    }
                }
            });
        }
        else Toast.makeText(Activity3.this, "Mat khau khong trung khop", Toast.LENGTH_SHORT).show();
    }
}