package com.example.ppapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.RecyclerViewHolder>{
    Context context;

    private List<HoaDon> listHoaDon = new ArrayList<>();

    public HoaDonAdapter(Context context, List<HoaDon> listHoaDon) {
        this.context = context;
        this.listHoaDon = listHoaDon;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_item, parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        HoaDon hoaDon = listHoaDon.get(position);
        holder.txtMaDonHang.setText(hoaDon.getMaHoaDon());
        holder.txtTrangThai.setText(hoaDon.getTrangThai());
        holder.txtViTriTrang.setText(hoaDon.getViTriTrang()+"");
        holder.txtLoaiHoaDon.setText(hoaDon.getLoaiHoaDon());
        holder.txtId.setText(hoaDon.getId()+"");

        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Trong ngày")){
            holder.itemView.setBackgroundResource(R.drawable.btn_color_yellow);
            holder.txtId.setTextColor(Color.parseColor("#FAE451"));
        }
        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Pending cũ")){
            holder.itemView.setBackgroundResource(R.drawable.btn_color);
            holder.txtId.setTextColor(Color.parseColor("#C1BEFA"));
        }
        if(hoaDon.getLoaiHoaDon().equalsIgnoreCase("Pending mới")){
            holder.itemView.setBackgroundResource(R.drawable.item_line_bg);
            holder.txtId.setTextColor(Color.parseColor("#81E6D6"));
        }
        // bắt sự kiện khi kích vào LinearLayout
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.design_default_color_primary));
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClick(Integer.parseInt(holder.txtId.getText().toString()));
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaDonHang;
        TextView txtTrangThai;
        TextView txtLoaiHoaDon;
        TextView txtViTriTrang;
        TextView txtId;
        public RecyclerViewHolder(View itemView) {
            super(itemView);

            txtMaDonHang = (TextView) itemView.findViewById(R.id.txtMaDonHang);
            txtTrangThai = (TextView) itemView.findViewById(R.id.txtTrangThai);
            txtViTriTrang = (TextView) itemView.findViewById(R.id.txtViTriTrang);
            txtLoaiHoaDon = (TextView) itemView.findViewById(R.id.txtLoaiHoaDon2);
            txtId = (TextView) itemView.findViewById(R.id.txtId);
        }
    }
    public interface OnItemClickedListener {
        void onItemClick(int id);
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
