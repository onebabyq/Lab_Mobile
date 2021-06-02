package com.example.a69_18028791_tranvuhoangson;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    ArrayList<Product> listProduct;

    FragmentManager fragmentManager;
    public ProductAdapter(Context context, ArrayList<Product> listProduct, FragmentManager fragmentManager) {
        this.context = context;
        this.listProduct = listProduct;
        this.fragmentManager = fragmentManager;
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
        Product product = listProduct.get(position);
        holder.txtId.setText(product.getId()+"");
        holder.txtType.setText(product.getType());
        holder.txtPrice.setText(product.getPrice()+"");
        holder.txtCountry.setText(product.getCountry());

        holder.btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,UpdateProductActivity.class));
                Intent intent = new Intent(context, UpdateProductActivity.class);
                intent.putExtra("key_id", product.getId());  // Truyền một String
                intent.putExtra("key_type", product.getType());                    // Truyền một Int
                intent.putExtra("key_price", product.getPrice());                 // Truyền một Boolean
                intent.putExtra("key_country", product.getCountry());                 // Truyền một Boolean
                context.startActivity(intent);
            }
        });
        holder.btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DeleteFragment hello = new DeleteFragment(context,product.getId());
                fragmentTransaction.add(R.id.listFragment,hello,"Hello");
                fragmentTransaction.commit();

            }
        });

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
        private Button btnUpdate2,btnDelete2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtType = itemView.findViewById(R.id.txtType2);
            txtPrice = itemView.findViewById(R.id.txtPrice2);
            txtCountry = itemView.findViewById(R.id.txtCountry2);
            btnUpdate2 = itemView.findViewById(R.id.btnUpdate2);
            btnDelete2 = itemView.findViewById(R.id.btnDelete2);
        }
    }

}
