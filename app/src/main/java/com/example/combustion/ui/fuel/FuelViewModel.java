package com.example.combustion.ui.fuel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.combustion.DatabaseHelper;

import java.util.ArrayList;

public class FuelViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> carsMutableLiveData;

    public MutableLiveData<ArrayList<String>> getCarsMutableLiveData(Context context){
        if(carsMutableLiveData == null)
        {
            carsMutableLiveData = new MutableLiveData<>();
            loadCars(context);
        }
        return carsMutableLiveData;
    }

    private void loadCars(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<String> cars = db.getAllCars();
        carsMutableLiveData = new MutableLiveData<ArrayList<String>>(cars);
    }
}