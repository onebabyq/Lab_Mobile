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
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HoaDonActivity extends AppCompatActivity {
    List<HoaDon> listHoaDon;
    HoaDonAdapter adapter;
    HoaDonDAO hoaDonDAO;
    TextView txtMaDon,txtTrangThai,txtViTriTrang,txtNgayLap,txtTenShipper,txtLoaiHoaDon,txtId;
    Button btnXuatKho,btnPending,btnXoaDon,btnCapNhatDon;
    HoaDon hoaDon;
    int idHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        configActionBar();


        txtMaDon = (TextView) findViewById(R.id.txtMaDon);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThai);
        txtViTriTrang = (TextView) findViewById(R.id.txtViTriTrang);
        txtNgayLap = (TextView) findViewById(R.id.txtNgayLap);
        txtTenShipper = (TextView) findViewById(R.id.txtTenShipper);
        txtLoaiHoaDon = (TextView) findViewById(R.id.txtLoaiHoaDon);
        txtId = (TextView) findViewById(R.id.txtId);
        btnXuatKho = (Button) findViewById(R.id.btnXuatKho);
        btnPending = (Button) findViewById(R.id.btnPending);
        btnXoaDon = (Button) findViewById(R.id.btnXoaDon);
        btnCapNhatDon = (Button) findViewById(R.id.btnCapNhatDon);
        btnXoaDon = (Button) findViewById(R.id.btnXoaDon);


        Intent intent = getIntent();
        idHoaDon = intent.getIntExtra("key_id",1);

        txtId.setText(idHoaDon+"");

        hoaDonDAO= new HoaDonDAO(this);
        hoaDon = hoaDonDAO.getHoaDon(idHoaDon);

        if(hoaDon.getTrangThai().equalsIgnoreCase("???? xu???t"))
            btnXuatKho.setText("Mang h??ng v???");

        txtMaDon.setText(hoaDon.getMaHoaDon());
        txtTrangThai.setText(hoaDon.getTrangThai());
        txtViTriTrang.setText(hoaDon.getViTriTrang()+"");
        txtTenShipper.setText(hoaDon.getTenShipper());
        txtNgayLap.setText(hoaDon.getNgayLap());
        txtLoaiHoaDon.setText(hoaDon.getLoaiHoaDon());

        listHoaDon = new ArrayList<>();

        adapter = new HoaDonAdapter(HoaDonActivity.this,listHoaDon);

        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDon.setTrangThai("????n h???y");
                txtTrangThai.setText("????n h???y");
                hoaDonDAO.updateHoaDon(hoaDon);
                Toast.makeText(HoaDonActivity.this,"Pending th??nh c??ng", Toast.LENGTH_SHORT).show();
            }
        });
        btnCapNhatDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HoaDonActivity.this,CapNhatActivity.class);
                intent1.putExtra("key_id",hoaDon.getId());


                startActivity(intent1);
            }
        });

        btnXuatKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnXuatKho.getText().toString().equalsIgnoreCase("XU???T KHO")){
                    hoaDon.setTrangThai("???? xu???t");
                    hoaDonDAO.updateHoaDon(hoaDon);
                    txtTrangThai.setText("???? xu???t");
                    btnXuatKho.setText("MANG H??NG V???");
                }
                else {
                    hoaDon.setTrangThai("Ch??a xu???t");
                    hoaDonDAO.updateHoaDon(hoaDon);
                    txtTrangThai.setText("Ch??a xu???t");
                    btnXuatKho.setText("XU???T KHO");
                }
            }
        });
        btnXoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MessageRemoveFragment messageRemoveFragment = new MessageRemoveFragment(HoaDonActivity.this,hoaDon,"X??a h??a ????n");
                fragmentTransaction.add(R.id.listFragment,messageRemoveFragment,"fragment");
                fragmentTransaction.commit();
                //hoaDonDAO.deleteHoaDon(hoaDon);

            }
        });

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                startActivity(new Intent(HoaDonActivity.this,ListHoaDonActivity.class));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    public void configActionBar(){
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("????n h??ng"); //Thi???t l???p ti??u ????? n???u mu???n
        String title = actionBar.getTitle().toString(); //L???y ti??u ????? n???u mu???n
        //     actionBar.hide(); //???n ActionBar n???u mu???n
    }

}