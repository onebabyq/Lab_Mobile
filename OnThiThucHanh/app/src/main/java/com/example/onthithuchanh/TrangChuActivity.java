package com.example.onthithuchanh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrangChuActivity extends AppCompatActivity {
    ArrayList<User> listUser;
    UserAdapter adapter;
    RecyclerView recyclerView;
    Button btnAdd,btnEdit,btnDel;
    EditText etName,etAge;
    TextView txtIdHidden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        btnAdd = (Button)findViewById(R.id.btnAdd) ;
        btnEdit = (Button)findViewById(R.id.btnEdit) ;
        btnDel = (Button)findViewById(R.id.btnDel) ;
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        txtIdHidden = (TextView) findViewById(R.id.txtIdHidden);

        listUser= new ArrayList<>();
        adapter = new UserAdapter(this,listUser,etName,etAge,txtIdHidden);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        createUserList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = etName.getText().toString();
                String age = etAge.getText().toString();
                PostApi(name,age);
                adapter.notifyDataSetChanged();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtIdHidden.getText().toString());
                String name = etName.getText().toString();
                int age  = Integer.parseInt(etAge.getText().toString());
                User user = new User(id,name,age);
                PutApi(user);
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void createUserList(){
//        listUser.add(new User(1,"Son",21));
//        listUser.add(new User(2,"Nam",22));
//        listUser.add(new User(3,"Ngoc",22));
        getUsers();
    }
    public void getUsers() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://60b689b617d1dc0017b88028.mockapi.io/User";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        Gson gson = new Gson();
                        listUser.add(gson.fromJson(String.valueOf(object), User.class));
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> Toast.makeText(TrangChuActivity.this, "Error with JSON Array Object", Toast.LENGTH_SHORT).show());

        queue.add(jsonArrayRequest);
    }
    private void PostApi(String name, String age){
        String url = "https://60b689b617d1dc0017b88028.mockapi.io/User";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(TrangChuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TrangChuActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String>
                        params = new HashMap<>();
                params.put("name", name);
                params.put("age", age);

                listUser.clear();
                getUsers();

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void PutApi(User user){
        String url = "https://60b689b617d1dc0017b88028.mockapi.io/User";
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + user.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TrangChuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrangChuActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("age", user.getAge()+"");
                listUser.clear();
                getUsers();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + 37, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(TrangChuActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrangChuActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
/*
  RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://itunes.apple.com/search?term=michael+jackson";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    int resultCount = response.optInt("resultCount");
                    if (resultCount > 0) {
                        Gson gson = new Gson();
                        JSONArray jsonArray = response.optJSONArray("results");
                        if (jsonArray != null) {
                            SongInfo[] songs = gson.fromJson(jsonArray.toString(), SongInfo[].class);
                            if (songs != null && songs.length > 0) {
                                for (SongInfo song : songs) {
                                    Log.i("LOG", song.trackViewUrl);
                                }
                            }
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
*/