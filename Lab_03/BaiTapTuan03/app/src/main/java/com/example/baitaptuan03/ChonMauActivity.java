package com.example.baitaptuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChonMauActivity extends AppCompatActivity {
    View btn_red;
    View btn_live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bt_3b);
        btn_live = (View) findViewById(R.id.view2);
        btn_red = (View) findViewById(R.id.view3);

        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                String color_choose = "Red";
                returnIntent.putExtra("color_choose",color_choose);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
        btn_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                String color_choose = "Live";
                returnIntent.putExtra("color_choose",color_choose);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}