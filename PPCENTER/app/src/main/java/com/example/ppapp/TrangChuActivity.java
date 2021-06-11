package com.example.ppapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.MenuItemCompat;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class TrangChuActivity extends AppCompatActivity {
    Button btnTimKiem,btnBaoCao,btnDangXuat,btnThemHoaDon,btnChuyenPending;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        configActionBar();

        hoaDonDAO = new HoaDonDAO(this);
        btnTimKiem = (Button) findViewById(R.id.btnTimKiem);
        btnBaoCao = (Button) findViewById(R.id.btnBaoCao);
        btnDangXuat = (Button) findViewById(R.id.btnDangXuat);
        btnThemHoaDon = (Button) findViewById(R.id.btnThemHoaDon);
        btnChuyenPending = (Button) findViewById(R.id.btnChuyenPending);
        btnThemHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrangChuActivity.this,AddHoaDonActivity.class));
            }
        });

        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrangChuActivity.this,ListHoaDonActivity.class));
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
               System.exit(0);
            }
        });
        btnBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrangChuActivity.this,BaoCaoActivity.class));
            }
        });
        btnChuyenPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(TrangChuActivity.this,BaoCaoActivity.class));
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MessageRemoveFragment messageRemoveFragment = new MessageRemoveFragment(TrangChuActivity.this,new HoaDon(),"Chuyển Pending");
                fragmentTransaction.add(R.id.listFragment,messageRemoveFragment,"fragment");
                fragmentTransaction.commit();
                //hoaDonDAO.chuyenPending();
                //Toast.makeText(TrangChuActivity.this,"Chuyển Pending thành công",Toast.LENGTH_SHORT).show();
            }
        });
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
               Toast.makeText(TrangChuActivity.this,"Không thể trở lại",Toast.LENGTH_SHORT).show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    }
    public void configActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Trang chủ"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
   //     actionBar.hide(); //Ẩn ActionBar nếu muốn
    }

}