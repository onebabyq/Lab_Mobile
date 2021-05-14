package com.example.baitaptuan07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnSave,btnRemove;
    EditText etName;
    ListView listView;
    List<String> listUser;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button) findViewById(R.id.button);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        etName = (EditText) findViewById(R.id.etName);

        listView = (ListView) findViewById(R.id.listView);
        AppDatabase appDatabase = AppDatabase.getInMemoryDatabase(this);
        listUser = new ArrayList<>();
        for(User i : appDatabase.userDao().findAllUser()){
            listUser.add(i.getName());
        }
        //listUser.add("Son abc");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listUser);
        listView.setAdapter(adapter);

        context= this;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(etName.getText().toString());
                appDatabase.userDao().insertUser(user);
                Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show();
                //listUser.add("abc");
                listUser.clear();
                for(User i : appDatabase.userDao().findAllUser()){
                    listUser.add(i.getName());
                }
                adapter.notifyDataSetChanged();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(etName.getText().toString());
                appDatabase.userDao().deleteUserByName(user.getName());
                Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                //listUser.add("abc");
                listUser.clear();
                for(User i : appDatabase.userDao().findAllUser()){
                    listUser.add(i.getName());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}