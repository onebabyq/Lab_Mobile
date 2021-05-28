package com.example.baitapthuongki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<User> listUser;

    public UserAdapter(Context context, int layout, ArrayList<User> listUser) {
        this.context = context;
        this.layout = layout;
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.line_user, parent, false);
        }

        User user = listUser.get(position);
        ((TextView) convertView.findViewById(R.id.txtId))
                .setText(user.getId()+"");
        ((TextView) convertView.findViewById(R.id.txtName2))
                .setText(user.getName());
        ((TextView) convertView.findViewById(R.id.txtAge2))
                .setText(user.getAge()+"");
        return convertView;
    }


}
