package com.example.ppapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class MessageRemoveFragment extends Fragment {
    private static final String TAG = "SQLite";
    Button btnYes,btnCancel;
    TextView txtMessage;
    Context context;
    HoaDon hoaDon;
    HoaDonDAO hoaDonDAO;
    String yeuCau;
    @SuppressLint("ValidFragment")
    public MessageRemoveFragment(Context context, HoaDon hoaDon,String yeuCau) {
        this.context = context;
        this.hoaDon=hoaDon;
        this.yeuCau=yeuCau;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /** Inflating the layout for this fragment **/
        hoaDonDAO = new HoaDonDAO(context);
        View v = inflater.inflate(R.layout.fragment_message_remove, null);

       // Log.i(TAG, "MyDatabaseHelper.OnCreateFragment ... : "+hoaDon.toString());
        btnYes = (Button) v.findViewById(R.id.btnYes);
        btnCancel = (Button) v.findViewById(R.id.btnCancel);
        txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        if(yeuCau.equalsIgnoreCase("Chuyển Pending")){
            txtMessage.setText("Bạn có chắc chắn muốn chuyển Pending?");
        }
        if(yeuCau.equalsIgnoreCase("Reset App")){
            txtMessage.setText("Bạn có chắc chắn muốn xóa hết dữ liệu?");
        }

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yeuCau.equalsIgnoreCase("Xóa hóa đơn")){
                    Log.i(TAG, "MyDatabaseHelper.OnCreateFragment ... : "+hoaDon.toString());
                    hoaDonDAO.deleteHoaDon(hoaDon);
                    context.startActivity(new Intent(context,ListHoaDonActivity.class));
                }
                if(yeuCau.equalsIgnoreCase("Chuyển Pending")){
                   // Log.i(TAG, "MyDatabaseHelper.OnCreateFragment ... : "+hoaDon.toString());
                    hoaDonDAO.chuyenPending();
                    context.startActivity(new Intent(context,TrangChuActivity.class));
                }
                if(yeuCau.equalsIgnoreCase("Reset App")){
                    // Log.i(TAG, "MyDatabaseHelper.OnCreateFragment ... : "+hoaDon.toString());
                    hoaDonDAO.resetApp();
                    context.startActivity(new Intent(context,ListHoaDonActivity.class));
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yeuCau.equalsIgnoreCase("Xóa hóa đơn")){
                    Intent intent = new Intent(context,HoaDonActivity.class);
                    intent.putExtra("key_id",hoaDon.getId()+"");
                    context.startActivity(intent);
                }
                if(yeuCau.equalsIgnoreCase("Chuyển Pending")){
                    Intent intent = new Intent(context,TrangChuActivity.class);

                    context.startActivity(intent);
                }
                if(yeuCau.equalsIgnoreCase("Reset App")){
                    Intent intent = new Intent(context,ListHoaDonActivity.class);

                    context.startActivity(intent);
                }

            }
        });

        return v;
    }
}