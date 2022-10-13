package com.example.combustion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StatisticsAdapter extends ArrayAdapter<StatisticModel> {

    public StatisticsAdapter(@NonNull Context context, ArrayList<StatisticModel> statisticModelArrayList) {
        super(context, 0, statisticModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_card, parent, false);
        }
        StatisticModel statisticModel = getItem(position);

        TextView card_title = listitemView.findViewById(R.id.card_title);
        TextView card_content = listitemView.findViewById(R.id.card_content);
        ImageView card_imageview = listitemView.findViewById(R.id.card_imageview);

        card_title.setText(statisticModel.getTitle());
        card_content.setText(statisticModel.getContent());
        card_imageview.setImageResource(statisticModel.getImage_id());
        return listitemView;
    }
}
