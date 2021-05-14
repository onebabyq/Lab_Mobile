package com.example.baitaptuan07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {
    Context context;
    int layout_item,layout_parent;
    List<Place> listPlaces;
    PlaceAdapter adapter;
    public PlaceAdapter(Context context, int layout,int layout_parent, List<Place> listPlaces) {
        this.context = context;
        this.layout_item = layout;
        this.layout_parent = layout_parent;
        this.listPlaces = listPlaces;
        adapter = this;
    }

    @Override
    public int getCount() {
        return listPlaces.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        AppDatabase appDatabase =  AppDatabase.getInMemoryDatabase(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout_item,null);
        View view2 = inflater.inflate(layout_parent,null);
        EditText etPlace = (EditText) view2.findViewById(R.id.etPlace);
        TextView txtPlace = (TextView) view.findViewById(R.id.txtPlace);
        TextView txtID = (TextView) view.findViewById(R.id.txtID);
        ImageView btnEdit = (ImageView) view.findViewById(R.id.btnEdit);
        ImageView btnRemove = (ImageView) view.findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(txtID.getText().toString());
                appDatabase.placeDao().deletePlaceById(id);
                listPlaces.clear();
                for(Place i :  appDatabase.placeDao().findAllPlace()){
                    listPlaces.add(i);
                }
                adapter.notifyDataSetChanged();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = appDatabase.placeDao().findPlaceById(Integer.parseInt(txtID.getText().toString()));
                String str = etPlace.getText().toString();
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
                //place.setName(etPlace.getText().toString());
                appDatabase.placeDao().updatePlace(new Place(123,"Lạc Đà"));
                listPlaces.clear();
                for(Place i :  appDatabase.placeDao().findAllPlace()){
                    listPlaces.add(i);
                }
                adapter.notifyDataSetChanged();
            }
        });
        Place place = listPlaces.get(position);
        txtPlace.setText(place.getName());
        txtID.setText(place.getId()+"");
        return view;
    }
}
