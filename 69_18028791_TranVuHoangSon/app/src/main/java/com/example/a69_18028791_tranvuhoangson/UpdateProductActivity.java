package com.example.a69_18028791_tranvuhoangson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateProductActivity extends AppCompatActivity {
    Button btnUpdate,btnBack3;
    EditText etTypeUp,etPriceUp,etCountryUp;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnBack3 = (Button) findViewById(R.id.btnBack3);
        etTypeUp = (EditText) findViewById(R.id.etTypeUp);
        etPriceUp = (EditText) findViewById(R.id.etPriceUp);
        etCountryUp = (EditText) findViewById(R.id.etCountryUp);

        Intent intent = getIntent();
        id = intent.getIntExtra("key_id",0);
        String type = intent.getStringExtra("key_type");
        int price = intent.getIntExtra("key_price",0);
        String country = intent.getStringExtra("key_country");

       etTypeUp.setText(type);
        etPriceUp.setText(price+"");
        etCountryUp.setText(country);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type2=etTypeUp.getText().toString();
                String country2=etCountryUp.getText().toString();
                int price2=Integer.parseInt(etPriceUp.getText().toString());

                PutApi(new Product(id,type2,price2,country2));
            }
        });
    }
    private void PutApi(Product product){
        String url = "https://60b6dd1717d1dc0017b88669.mockapi.io/products";

        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(UpdateProductActivity.this, product.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateProductActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("type", product.getType());
                params.put("price", product.getPrice()+"");
                params.put("country", product.getCountry()+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}