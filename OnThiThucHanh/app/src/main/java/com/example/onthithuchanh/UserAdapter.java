package com.example.onthithuchanh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    ArrayList<User> listUser;
    EditText etName;
    EditText etAge;
    TextView txtIdHidden;
    public UserAdapter(Context context, ArrayList<User> listUser,EditText etName,EditText etAge,TextView txtIdHidden) {
        this.context = context;
        this.listUser = listUser;
        this.etAge=etAge;
        this.etName = etName;
        this.txtIdHidden = txtIdHidden;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.txtId.setText(user.getId()+"");
        holder.txtName.setText(user.getName());
        holder.txtAge.setText(user.getAge()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(user.getName());
                txtIdHidden.setText(user.getId()+"");
                etAge.setText(user.getAge()+"");
                //Toast.makeText(context,"ID: "+user.getId()+", Name: "+user.getName()+", Age: "+user.getAge(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId;
        private TextView txtName;
        private TextView txtAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
        }
    }
}
