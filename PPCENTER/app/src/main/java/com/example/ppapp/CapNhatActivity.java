package com.example.ppapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CapNhatActivity extends AppCompatActivity {
    Spinner spinner,spinnerLoaiHoaDon;
    ArrayAdapter<String> adapter,adapter2;
    List<String> listString,listString2;
    EditText etMaDonHang,etViTriTrang,etTenShipper;
    Button btnSave;
    TextView txtId;
    HoaDon hoaDon= new HoaDon();
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat);
        hoaDonDAO = new HoaDonDAO(this);

        etMaDonHang = (EditText) findViewById(R.id.etMaDonHang);
        etViTriTrang = (EditText) findViewById(R.id.etViTriTrang);
        etTenShipper = (EditText) findViewById(R.id.etTenShipper);
        txtId = (TextView) findViewById(R.id.txtId);
        btnSave = (Button) findViewById(R.id.btnSave);



        Intent intent = getIntent();
        int id = intent.getIntExtra("key_id",1);
        hoaDon = hoaDonDAO.getHoaDon(id);


        if(hoaDon.getMaHoaDon()!=null)
            etMaDonHang.setText(hoaDon.getMaHoaDon());
        if(hoaDon.getViTriTrang()!=0)
            etViTriTrang.setText(hoaDon.getViTriTrang()+"");
        if(hoaDon.getTenShipper()!=null)
            etTenShipper.setText(hoaDon.getTenShipper());
        txtId.setText(id+"");

        listString = new ArrayList<>();
        listString2 = new ArrayList<>();
        Spinner spinner  = (Spinner) findViewById(R.id.cbxTrangThai);
        Spinner spinnerLoaiHoaDon  = (Spinner) findViewById(R.id.cbxLoaiHoaDon);

        if(hoaDon.getTrangThai().equalsIgnoreCase("Đã xuất")){
            listString.add("Đã xuất");
            listString.add("Chưa xuất");
            listString.add("Đơn hủy");
        }
        if(hoaDon.getTrangThai().equalsIgnoreCase("Chưa xuất")){
            listString.add("Chưa xuất");
            listString.add("Đã xuất");
            listString.add("Đơn hủy");
        }
        if(hoaDon.getTrangThai().equalsIgnoreCase("Đơn hủy")){
            listString.add("Đơn hủy");
            listString.add("Chưa xuất");
            listString.add("Đã xuất");
        }

        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Trong ngày")){
            listString2.add("Trong ngày");
            listString2.add("Pending cũ");
            listString2.add("Pending mới");
        }
        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Pending cũ")){
            listString2.add("Pending cũ");
            listString2.add("Trong ngày");
            listString2.add("Pending mới");
        }
        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Pending mới")){
            listString2.add("Pending mới");
            listString2.add("Pending cũ");
            listString2.add("Trong ngày");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listString);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listString2);
        spinner.setAdapter(adapter);
        spinnerLoaiHoaDon.setAdapter(adapter2);

        configActionBar();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hoaDon.setMaHoaDon(etMaDonHang.getText().toString());
                hoaDon.setTrangThai(spinner.getSelectedItem().toString());
                hoaDon.setViTriTrang(Integer.parseInt(etViTriTrang.getText().toString()));
                hoaDon.setTenShipper(etTenShipper.getText().toString());
                hoaDon.setLoaiHoaDon(spinnerLoaiHoaDon.getSelectedItem().toString());
                hoaDonDAO.updateHoaDon(hoaDon);
                Toast.makeText(CapNhatActivity.this,"UPDATE SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CapNhatActivity.this,HoaDonActivity.class);
                intent.putExtra("key_id",hoaDon.getId());
                startActivity(intent);

            }
        });
    }
    public void configActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Tìm kiếm"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
             actionBar.hide(); //Ẩn ActionBar nếu muốn
    }
}