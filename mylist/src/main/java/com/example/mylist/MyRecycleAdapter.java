package com.example.mylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyHolder>{
    List<Map<String, String>> data;

    public MyRecycleAdapter(){};
    public MyRecycleAdapter(List<Map<String, String>> data) {
        this.data = data;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.list_item, parent, false);
        MyHolder vh = new MyHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.txtName.setText(data.get(position).get("name"));
        holder.txtAddr.setText(data.get(position).get("addr"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //view Holder를 만들고
    class MyHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtAddr;
        //초기화?
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddr = itemView.findViewById(R.id.txtAddr);


        }
    }
}
