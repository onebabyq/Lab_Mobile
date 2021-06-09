package com.example.testapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapi.R;
import com.example.testapi.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RecyclerViewHolder>{
    Context context;

    private List<User> listUser = new ArrayList<>();

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_user, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        User user = listUser.get(position);
        holder.txtId.setText(user.getId()+"");
        holder.txtName.setText(user.getName());
        holder.txtAge.setText(user.getAge()+"");




        // bắt sự kiện khi kích vào LinearLayout
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary));
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(Integer.parseInt(holder.txtId.getText().toString()),holder.txtName.getText().toString(),holder.txtAge.getText().toString());
                }
            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary));
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClickRemove(Integer.parseInt(holder.txtId.getText().toString()));
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return listUser.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtName;
        TextView txtAge;
        Button btnEdit,btnRemove;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtAge = (TextView) itemView.findViewById(R.id.txtAge2);
            txtName = (TextView) itemView.findViewById(R.id.txtName2);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
        }
    }
    public interface OnItemClickedListener {
        void onItemClick(int id,String name,String age);
        void onItemClickRemove(int id);
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
