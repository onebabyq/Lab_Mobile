package com.example.a69_18028791_tranvuhoangson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> listProduct;
    EditText etName;
    EditText etAge;
    TextView txtIdHidden;
    public ProductAdapter(Context context, ArrayList<Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
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
        Product Product = listProduct.get(position);
        holder.txtId.setText(Product.getId()+"");
        holder.txtType.setText("Type: "+Product.getType());
        holder.txtPrice.setText("Price: "+Product.getPrice()+"$");
        holder.txtCountry.setText("Country: "+Product.getCountry());

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId;
        private TextView txtType;
        private TextView txtPrice;
        private TextView txtCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtType = itemView.findViewById(R.id.txtType);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtCountry = itemView.findViewById(R.id.txtCountry);
        }
    }

}
