package com.example.combustion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    DatabaseHelper sqLiteDatabase;
    public DatabaseManager(Context context) {
        sqLiteDatabase = new DatabaseHelper(context);
    }

    public boolean addFuelRecord(int odometer, double fuelRefueled, String station, double cost_per_liter, String car, String date){
        SQLiteDatabase db = sqLiteDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_ODOMETER, odometer);
        contentValues.put(DatabaseHelper.COLUMN_FUEL_REFUELED, fuelRefueled);
        contentValues.put(DatabaseHelper.COLUMN_STATION, station);
        contentValues.put(DatabaseHelper.COLUMN_COST_PER_LITER, cost_per_liter);
        contentValues.put(DatabaseHelper.COLUMN_CAR, car);
        contentValues.put(DatabaseHelper.COLUMN_DATE, date);

        long result = db.insert(DatabaseHelper.TABLE_NAME_FUEL_HISTORY, null, contentValues);
        db.close();
        return result != -1;
    }
}
