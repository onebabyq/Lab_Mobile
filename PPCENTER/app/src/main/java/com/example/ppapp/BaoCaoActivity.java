package com.example.ppapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaoCaoActivity extends AppCompatActivity {
    private static final String TAG = "SQLite";
    List<String> listString,listStringToAddAdapter;
    List<String> listString2,listStringToAddAdapter2;
    List<String> listString3,listStringToAddAdapter3;
    ListView listViewTongDonKiem,listViewTongDonXuat,listViewTongDonGui;
    ArrayAdapter<String> adapter,adapter2,adapter3;
    HoaDonDAO hoaDonDAO;
    TextView txtTongDonKiem,txtTongDonXuat,txtTongDonGui,txtTongDonTra,txtNgayGio;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_cao);
        hoaDonDAO = new HoaDonDAO(this);
        listViewTongDonKiem =  (ListView) findViewById(R.id.listViewTongDonKiem);
        listViewTongDonXuat =  (ListView) findViewById(R.id.listViewTongDonXuat);
        listViewTongDonGui =  (ListView) findViewById(R.id.listViewTongDonGui);
        txtTongDonKiem =  (TextView) findViewById(R.id.txtTongDonKiem);
        txtTongDonXuat =  (TextView) findViewById(R.id.txtTongDonXuat);
        txtTongDonGui =  (TextView) findViewById(R.id.txtTongDonGui);
        txtTongDonTra =  (TextView) findViewById(R.id.txtTongDonTra);
        txtNgayGio =  (TextView) findViewById(R.id.txtNgayGio);
        scrollView =  (ScrollView) findViewById(R.id.scrollView);
        listString = new ArrayList<>();
        listString2 = new ArrayList<>();
        listString3 = new ArrayList<>();
        listStringToAddAdapter= new ArrayList<>();
        listStringToAddAdapter2= new ArrayList<>();
        listStringToAddAdapter3= new ArrayList<>();
        //Don kiem
        txtTongDonKiem.setText(hoaDonDAO.getHoaDonCountByLoaiDon("Trong ngày")+"");
        listString.addAll(hoaDonDAO.phanLoaiTheoNgay("Trong ngày"));
        for(String i : listString){
            Log.i(TAG, "CodeDate:  "+i );
            String line = hoaDonDAO.getCountHoaDonByDate(i,"Trong ngày")+" đơn của ngày "+i;
            listStringToAddAdapter.add(line);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listStringToAddAdapter);
        listViewTongDonKiem.setAdapter(adapter);
        setScrollToListView(listViewTongDonKiem);


        //Don xuat
       txtTongDonXuat.setText(hoaDonDAO.getHoaDonCountByLoaiDonDaXuat()+"");
        listString2.addAll(hoaDonDAO.phanLoaiTheoNgayDaXuat());

        Log.i(TAG, "CodeDate Size:  "+listString2.size() );
        for(String i : listString2){
            Log.i(TAG, "CodeDate DA XUAT:  "+i );
            String line = hoaDonDAO.getCountHoaDonByDateDaXuat(i)+" đơn của ngày "+i;
            listStringToAddAdapter2.add(line);
        }

        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listStringToAddAdapter2);
        listViewTongDonXuat.setAdapter(adapter2);
        setScrollToListView(listViewTongDonXuat);

        //Don gui
        txtTongDonGui.setText(hoaDonDAO.getHoaDonCountByLoaiDon("Pending mới")+"");
        listString3.addAll(hoaDonDAO.phanLoaiTheoNgay("Pending mới"));

        Log.i(TAG, "CodeDate Size:  "+listString3.size() );
        for(String i : listString3){
            Log.i(TAG, "CodeDate DA GUI:  "+i );
            String line = hoaDonDAO.getCountHoaDonByDate(i,"Pending mới")+" đơn của ngày "+i;
            listStringToAddAdapter3.add(line);
        }

        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listStringToAddAdapter3);
        listViewTongDonGui.setAdapter(adapter3);
        setScrollToListView(listViewTongDonGui);




        txtTongDonTra.setText(hoaDonDAO.getCountDonHuy()+"");
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        txtNgayGio.setText(currentDate);
    }


    public void setScrollToListView(ListView listView){
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

}