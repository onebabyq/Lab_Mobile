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

public class Activity2 extends AppCompatActivity {
    TextView txtDoNotHave;
    Button ban_signing;
    EditText edtEmail,edtPassword;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        txtDoNotHave = (TextView) findViewById(R.id.txtDoNotHave);
        ban_signing = (Button) findViewById(R.id.btn_signin);
        edtEmail = (EditText) findViewById(R.id.edtEmailLogin);
        edtPassword = (EditText) findViewById(R.id.edtPasswordLogin);

        edtEmail.setText("hoangson3239@gmail.com");
        edtPassword.setText("1234567");

        auth = FirebaseAuth.getInstance();
        ban_signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                Toast.makeText(Activity2.this,"Email: "+ email+" / Pass:" +pass,Toast.LENGTH_SHORT).show();
                checkLogin(email,pass);

            }
        });
        txtDoNotHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, Activity3.class));
            }
        });

        txtDoNotHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity2.this, Activity3.class));
            }
        });
    }
    public void checkLogin(String email,String pass){
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Activity2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    if(pass.length()<6){
                        edtPassword.setError("Password qua ngan, toi thieu phai 6 ki tu");
                        //Toast.makeText(Activity2.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(Activity2.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Activity2.this,"Login Susscessfully", Toast.LENGTH_SHORT).show();;
                    startActivity(new Intent(Activity2.this, Activity4.class));
                }
            }
        });
    }

}