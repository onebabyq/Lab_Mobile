package com.example.ppapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class AddHoaDonActivity extends AppCompatActivity {
    List<String> listString,listString2;
    Spinner spinner,spinner2;
    ArrayAdapter<String> adapter,adapter2;
    HoaDonDAO hoaDonDAO;
    Button btnThem,btnChange,btnScan;
    ImageView btnUpDate,btnDownDate,btnUpCode,btnDownCode;
    EditText etDate,etMaDon;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        configActionBar();
        hoaDonDAO = new HoaDonDAO(this);
        Spinner spinner  = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2  = (Spinner) findViewById(R.id.spinner2);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnChange = (Button) findViewById(R.id.btnChange);
        btnScan = (Button) findViewById(R.id.btnScan);
        btnUpDate = (ImageView) findViewById(R.id.btnUpDate);
        btnDownDate = (ImageView) findViewById(R.id.btnDownDate);
        btnUpCode = (ImageView) findViewById(R.id.btnUpCode);
        btnDownCode = (ImageView) findViewById(R.id.btnDownCode);
        etDate = (EditText) findViewById(R.id.etDate);
        etMaDon = (EditText) findViewById(R.id.etMaDon);
        listString = new ArrayList<>();
        listString2 = new ArrayList<>();

        listString.add("Chưa xuất");
        listString.add("Đã xuất");
        listString.add("Đơn hủy");

        listString2.add("Trong ngày");
        listString2.add("Pending cũ");
        listString2.add("Pending mới");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listString2);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listString);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon("210"+etDate.getText().toString()+"000"+etMaDon.getText().toString());
                hd.setTrangThai(spinner2.getSelectedItem().toString());
                hd.setLoaiHoaDon(spinner.getSelectedItem().toString());
                int viTriTrang =0;
                if(spinner.getSelectedItem().toString().equalsIgnoreCase("Trong ngày")){
                    viTriTrang = hoaDonDAO.getHoaDonCountByLoaiDonCoDonHuy(spinner.getSelectedItem().toString())/12+1;
                }
                else {
                    viTriTrang = hoaDonDAO.getHoaDonCountByLoaiDonCoDonHuy(spinner.getSelectedItem().toString())/15+1;
                }

                hd.setViTriTrang(viTriTrang);
                hoaDonDAO.addHoaDon(hd);
                Toast.makeText(AddHoaDonActivity.this,"Them thanh cong: "+hd.getMaHoaDon(),Toast.LENGTH_SHORT).show();
                if(flag==0){
                    btnThem.setBackgroundResource(R.drawable.btn_color);
                    flag=1;
                }
                else {
                    btnThem.setBackgroundResource(R.drawable.btn_color_yellow);
                    flag=0;
                }

            }
        });
        btnUpCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str =  etMaDon.getText().toString();
                int a = Integer.parseInt(str) +1;
                str = String.valueOf(a);
                if(str.length()==1){
                    str = "00"+str;
                }
                if(str.length()==2){
                    str = "0"+str;
                }
                etMaDon.setText(str);
            }
        });
        btnDownCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str =  etMaDon.getText().toString();
                int a = Integer.parseInt(str) -1;
                str = String.valueOf(a);
                if(str.length()==1){
                    str = "00"+str;
                }
                if(str.length()==2){
                    str = "0"+str;
                }
                etMaDon.setText(str);
            }
        });
        btnUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                String str = etDate.getText().toString();
                String str23 = str.substring(1,3);
                if(str23.equalsIgnoreCase("31")){
                    num = Integer.parseInt(str)+100;
                    num = (num/100)*100 +1;
                    str = String.valueOf(num);
                }
                else {
                    num = Integer.parseInt(str) + 1;
                    str = String.valueOf(num);
                }

                etDate.setText(str);
            }
        });
        btnDownDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num;
                String str = etDate.getText().toString();
                String str23 = str.substring(1,3);
                if(str23.equalsIgnoreCase("01")){
                    num = Integer.parseInt(str)-100;
                    num = (num/100)*100 +31;
                    str = String.valueOf(num);
                }
                else {
                    num = Integer.parseInt(str) - 1;
                    str = String.valueOf(num);
                }

                etDate.setText(str);
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner2.getSelectedItem().toString().equalsIgnoreCase("Đã xuất"))
                    spinner2.setSelection(0);
                else spinner2.setSelection(1);
            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // we need to create the object

                // of IntentIntegrator class

                // which is the class of QR library

                IntentIntegrator intentIntegrator = new IntentIntegrator(AddHoaDonActivity.this);

                intentIntegrator.setPrompt("Scan a barcode or QR Code");

                intentIntegrator.setOrientationLocked(true);

                intentIntegrator.initiateScan();
            }
        });

    }
    public void configActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Tìm kiếm"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.hide(); //Ẩn ActionBar nếu muốn
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        // if the intentResult is null then

        // toast a message as "cancelled"

        if (intentResult != null) {

            if (intentResult.getContents() == null) {

                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();

            } else {

                // if the intentResult is not null we'll set

                // the content and format of scan message
                String maScan = intentResult.getContents();

                String str3to6 = maScan.substring(3,6);
                String str9to12 = maScan.substring(9,12);
                etDate.setText(str3to6);
                etMaDon.setText(str9to12);
                //messageText.setText();

                //messageFormat.setText(intentResult.getFormatName());

            }

        } else {

            super.onActivityResult(requestCode, resultCode, data);

        }

    }
}