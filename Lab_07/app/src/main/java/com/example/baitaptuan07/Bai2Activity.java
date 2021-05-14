package com.example.baitaptuan07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Bai2Activity extends AppCompatActivity {
    ListView listView;
    Button btnSave,btnCancel;
    EditText etPlace;
    List<Place> listPlaces;
    PlaceAdapter adapter;
    AppDatabase appDatabase;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);
        appDatabase = AppDatabase.getInMemoryDatabase(this);
        anhXa();
        adapter = new PlaceAdapter(this,R.layout.item_place,R.layout.activity_bai2,listPlaces);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = new Place();
                place.setName(etPlace.getText().toString());
                appDatabase.placeDao().insertPlace(place);
                listPlaces.clear();
                for(Place i : appDatabase.placeDao().findAllPlace()) {
                    listPlaces.add(i);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(context,"Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void anhXa(){
        listView = (ListView) findViewById(R.id.listView);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etPlace = (EditText) findViewById(R.id.etPlace);
        context= this;
        listPlaces = new ArrayList<>();
        appDatabase.placeDao().insertPlace(new Place(123,"Đà Lạt"));
        appDatabase.placeDao().insertPlace(new Place(124,"Đà Nẵng"));
        appDatabase.placeDao().insertPlace(new Place(125,"Hà Giang"));
        listPlaces= appDatabase.placeDao().findAllPlace();

    }

}