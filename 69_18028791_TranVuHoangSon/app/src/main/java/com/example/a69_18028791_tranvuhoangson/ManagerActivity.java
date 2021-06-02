package com.example.a69_18028791_tranvuhoangson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {
    Button btnShowInfomation,btnAddProduct,btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        btnShowInfomation = (Button) findViewById(R.id.btnShowInfomation);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        btnShowInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerActivity.this,ShowInfoActivity.class));
            }
        });

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagerActivity.this,AddProductActivity.class));
            }
        });
    }
}