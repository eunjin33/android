package com.example.mydiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DiaryAdapter extends BaseAdapter {

    List<DiaryVO> list;

    public DiaryAdapter(){}

    public DiaryAdapter(List<DiaryVO> list) {this.list=list;}

    public void setList(List<DiaryVO> list) {
        this.list = list;
    }

    @Override
    public int getCount() {return list.size();}

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtContent = view.findViewById(R.id.txtContent);
        txtTitle.setText(list.get(i).getTitle());
        txtContent.setText(list.get(i).getContent());
        return view;
    }

}
