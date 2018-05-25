package com.example.upshottechonologies.networklibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.*;

public class GridAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<ModelClass> herolist;

    public GridAdapter(Context context, List<ModelClass> herolist) {
        this.context = context;
        this.herolist = herolist;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return herolist.size();
    }

    @Override
    public Object getItem(int position) {
        return herolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.grid_item, null);
        ImageView iv = view.findViewById(R.id.imageUrl);
        TextView name = view.findViewById(R.id.mName);
        if(herolist.get(position).getName()!=null)
            name.setText(herolist.get(position).getName());
        if(herolist.get(position).getUrl()!=null)
        {
            Glide.with(context).load(herolist.get(position).getUrl()).into(iv);
        }
        return view;
    }
}
