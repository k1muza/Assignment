package com.forza.assignment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by axa on 23/2/2018.
 */

public class TeamView implements Item {

    private Team team;
    private Context context;

    public TeamView(Context context, Team team) {
        this.team = team;
        this.context = context;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup convertView) {
        return inflater.inflate(R.layout.row_team_item, convertView, false);
    }

    private class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mainText;
        public TextView subText;

        public CardViewHolder(View convertView) {
            super(convertView);
            mainText = (TextView) convertView.findViewById(R.id.main_text);
            subText = (TextView) convertView.findViewById(R.id.sub_text);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO when clicked do something
            Toast.makeText(context, "You clicked: " + team.name, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new CardViewHolder(view);
    }


    @Override
    public void configureViewHolder(RecyclerView.ViewHolder vh) {
        if(vh instanceof CardViewHolder) {
            CardViewHolder vh1 = (CardViewHolder) vh;

            vh1.mainText.setText(team.name);
            vh1.subText.setText(team.countryName);
        }
    }
}
