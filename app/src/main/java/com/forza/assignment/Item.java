package com.forza.assignment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by k1muza on 21/02/2015.
 */
public interface Item {
    int getViewType();
    View getView(LayoutInflater inflater, ViewGroup convertView);
    RecyclerView.ViewHolder getViewHolder(View view);
    void configureViewHolder(RecyclerView.ViewHolder vh);
}


