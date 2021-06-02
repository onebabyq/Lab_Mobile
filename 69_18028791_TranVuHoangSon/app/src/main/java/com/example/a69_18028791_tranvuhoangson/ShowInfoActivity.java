package com.example.a69_18028791_tranvuhoangson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowInfoActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    RecyclerView recyclerView;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        listProduct= new ArrayList<>();
        adapter = new ProductAdapter(ShowInfoActivity.this,listProduct);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        createUserList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void createUserList() {
        getProducts();
    }
    public void getProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://60b6dd1717d1dc0017b88669.mockapi.io/products";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        Gson gson = new Gson();
                        listProduct.add(gson.fromJson(String.valueOf(object), Product.class));
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(ShowInfoActivity.this, "Error with JSON Array Object", Toast.LENGTH_SHORT).show());

        queue.add(jsonArrayRequest);
    }

}