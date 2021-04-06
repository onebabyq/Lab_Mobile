package com.example.baitaptuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btn_chonmau;
    ImageView phone_img;
    int LAUNCH_SECOND_ACTIVITY = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bt_3a);

        btn_chonmau = (Button) findViewById(R.id.btn_chonmau);
        phone_img = (ImageView) findViewById(R.id.imageView);
        btn_chonmau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChonMauActivity.class);
                startActivityForResult(intent,LAUNCH_SECOND_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                String color_choose=data.getStringExtra("color_choose");
                if(color_choose.equalsIgnoreCase("Red"))
                    phone_img.setImageResource(R.drawable.red);
                if(color_choose.equalsIgnoreCase("Live"))
                    phone_img.setImageResource(R.drawable.vsmart_live);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}