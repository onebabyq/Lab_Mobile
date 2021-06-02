package com.example.a69_18028791_tranvuhoangson;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddProductActivity extends AppCompatActivity {
    EditText etTypeAdd,etPriceAdd,etCountryAdd;
    Button btnCreate, btnBack2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etTypeAdd = (EditText) findViewById(R.id.etTypeAdd);
        etPriceAdd = (EditText) findViewById(R.id.etPriceAdd);
        etCountryAdd = (EditText) findViewById(R.id.etCountryAdd);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnBack2 = (Button) findViewById(R.id.btnBack2);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = etTypeAdd.getText().toString();
                String price = etPriceAdd.getText().toString();
                String country = etCountryAdd.getText().toString();
                PostApi(type,price,country);
            }
        });


    }
    private void PostApi(String type, String price, String country){
        String url = "https://60b6dd1717d1dc0017b88669.mockapi.io/products";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddProductActivity.this, "Successfully", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddProductActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String>
                        params = new HashMap<>();
                params.put("type", type);
                params.put("price", price);
                params.put("country", country);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}