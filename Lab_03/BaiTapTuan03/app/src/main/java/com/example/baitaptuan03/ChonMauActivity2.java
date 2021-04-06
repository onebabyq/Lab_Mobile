package com.example.baitaptuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChonMauActivity2 extends AppCompatActivity {
    Button btn_xong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bt_3c);

        btn_xong = (Button) findViewById(R.id.btn_xong);

        btn_xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChonMauActivity2.this,MainActivity2.class));
            }
        });

    }
}