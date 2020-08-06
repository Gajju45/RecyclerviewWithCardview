package com.example.recyclerviewwithcardview;

import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapter extends RecyclerView.Adapter<myViewHolder> implements Filterable {

    //1
    ArrayList<Model> data;
    ArrayList<Model> backup;
    //2 create constructer
    Context context;

    public myAdapter(ArrayList<Model> data, Context context) {
        this.data = data;
        this.context = context;
        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //3LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false);//create blank box
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, int position) {
        //4 binding

        final Model temp = data.get(position);

        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());
        holder.img.setImageResource(data.get(position).getImagName());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("imagename", temp.getImagName());
                intent.putExtra("header", temp.getHeader());
                intent.putExtra("detail", temp.getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        //background thread
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Model> filterdata = new ArrayList<>();

            if (keyword.toString().isEmpty())
                filterdata.addAll(backup);
            else
                {
                for (Model obj : backup)  //model ka object obj m chla jaaye
                {
                    if (obj.getHeader().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filterdata.add(obj);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            return results;
        }


        @Override//Main ui thread
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            data.clear();
            data.addAll((ArrayList<Model>)results.values);
            notifyDataSetChanged();//ui thread m data lejane ka kaam

        }
    };
}
