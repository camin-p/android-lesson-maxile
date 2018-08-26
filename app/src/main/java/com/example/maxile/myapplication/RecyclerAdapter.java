package com.example.maxile.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titleTextView;
        public TextView detailTextView;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            this.imageView = (ImageView)itemView.findViewById(R.id.imageview);
            this.titleTextView = (TextView)itemView.findViewById(R.id.title_adapter);
            this.detailTextView = (TextView)itemView.findViewById(R.id.detail_adapter);
        }
    }
    private List<MainActivity.RecycleViewModel> items;
    public RecyclerAdapter(List<MainActivity.RecycleViewModel> items) {
        this.items = items;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MainActivity.RecycleViewModel sectionModel = items.get(position);
        holder.titleTextView.setText(sectionModel.title);
        holder.detailTextView.setText(sectionModel.detail);
        if(sectionModel.imgurl.equals("")){
            sectionModel.imgurl="https://via.placeholder.com/100x100";
        }
        Picasso.get().load(sectionModel.imgurl).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
