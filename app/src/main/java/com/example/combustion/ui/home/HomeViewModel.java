package com.example.combustion.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.combustion.DatabaseHelper;
import com.example.combustion.Statistics;
import com.example.combustion.ui.Fueling;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Statistics> statisticsMutableLiveData;
    private MutableLiveData<ArrayList<String>> carsMutableLiveData;

    public MutableLiveData<Statistics> getStatisticsMutableLiveData(Context context) {
        if (statisticsMutableLiveData == null) {
            statisticsMutableLiveData = new MutableLiveData<Statistics>();
            loadStatistics(context);
        }
        return statisticsMutableLiveData;
    }

    public MutableLiveData<ArrayList<String>> getCarsMutableLiveData(Context context){
        if(carsMutableLiveData == null)
        {
            carsMutableLiveData = new MutableLiveData<>();
            loadCars(context);
        }
        return carsMutableLiveData;
    }

    private void loadStatistics(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<Fueling> fuelings = db.getAllFuelings();
//        for (Fueling f: fuelings) {
//            Log.d("fff", String.valueOf(f.get_id()));
//        }
        Statistics statistics = new Statistics(fuelings);
        statisticsMutableLiveData = new MutableLiveData<Statistics>(statistics);
    }

    private void loadCars(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<String> cars = db.getAllCars();
        carsMutableLiveData = new MutableLiveData<ArrayList<String>>(cars);
    }

}