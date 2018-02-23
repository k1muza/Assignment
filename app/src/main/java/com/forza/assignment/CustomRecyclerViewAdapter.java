package com.forza.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * Created by k1muza on 18/04/2016.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> items;
    private Context context;
    private int lastPosition;

    public CustomRecyclerViewAdapter(List<Item> items) {
        this.items = items;
        lastPosition = items.size() - 1;
    }

    public void add(Item item){
        items.add(item);
    }
    public void addTop(Item item){
        items.add(0, item);
    }
    public void remove(Item item){
        items.remove(item);

    }

    public void remove(int position) { //removes the row
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void empty(){
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        this.context = viewGroup.getContext().getApplicationContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        return items.get(position).getViewHolder(items.get(position).getView(mInflater, viewGroup));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        items.get(position).configureViewHolder(holder);
        setAnimation(holder.itemView, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View view, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setDuration(400);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

