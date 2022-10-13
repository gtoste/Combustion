package com.example.combustion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryDataAdapter extends ArrayAdapter<HistoryData> {

    ArrayList<HistoryData> historyDataArrayList;

    public HistoryDataAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HistoryData> historyData) {
        super(context, resource, historyData);
        this.historyDataArrayList = historyData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.history_row, parent, false);
        }
        HistoryData data = historyDataArrayList.get(position);

        TextView card_fuel = row.findViewById(R.id.fuel);
        TextView card_price = row.findViewById(R.id.price);
        TextView card_total = row.findViewById(R.id.total);
        TextView card_car = row.findViewById(R.id.car);
        TextView card_distance = row.findViewById(R.id.distance);
        TextView card_date = row.findViewById(R.id.date);

        card_fuel.setText(String.valueOf(data.getFuel()));
        card_price.setText(String.valueOf(data.getPrice()));
        card_total.setText(String.valueOf(data.getTotal()));
        card_car.setText(String.valueOf(data.getCar()));
        card_distance.setText(String.valueOf(data.getDistance()));
        card_date.setText(data.getDate());
        return row;
    }
}
