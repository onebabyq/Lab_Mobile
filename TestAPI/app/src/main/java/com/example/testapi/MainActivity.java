package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<User> listUser;
    UserAdapter adapter;
    RecyclerView recyclerView;
    Button btn_add;
    EditText etName,etAge;
    int idSave;
    static final String URL =  "https://60c045a7b8d3670017554a42.mockapi.io/users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        getUsers();
        adapter = new UserAdapter(MainActivity.this,listUser);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickedListener(new UserAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int id, String name, String age) {
                etName.setText(name);
                etAge.setText(age);
                btn_add.setText("SAVE");
                idSave= id;
            }

            @Override
            public void onItemClickRemove(int id) {
                DeleteApi(id);
                listUser.clear();
                getUsers();
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void anhXa(){
        btn_add = (Button) findViewById(R.id.btn_add) ;
        etName = (EditText) findViewById(R.id.etName) ;
        etAge = (EditText) findViewById(R.id.etAge) ;

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_add.getText().toString().equalsIgnoreCase("ADD")){
                    String name = etName.getText().toString();
                    String age  = etAge.getText().toString();
                    PostApi(name,age);
                    listUser.clear();
                    getUsers();
                    adapter.notifyDataSetChanged();
                }
                if(btn_add.getText().toString().equalsIgnoreCase("SAVE")){
                    String name = etName.getText().toString();
                    String age  = etAge.getText().toString();
                    PutApi(new User(idSave,name,Integer.parseInt(age)));
                    listUser.clear();
                    getUsers();
                    adapter.notifyDataSetChanged();
                }

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listUser = new ArrayList<>();


    }

    private void PutApi(User user){
        String url = URL;

        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + user.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Update thanh cong", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("name", user.getName());
                params.put("age", user.getAge()+"");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void getUsers() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = URL;
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
        }, error -> Toast.makeText(MainActivity.this, "Error with JSON Array Object", Toast.LENGTH_SHORT).show());

        queue.add(jsonArrayRequest);
    }
    private void PostApi(String name, String age){
        String url = URL;
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String>
                        params = new HashMap<>();
                params.put("name", name);
                params.put("age", age);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void DeleteApi(int id){
        String url = URL;
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }


}