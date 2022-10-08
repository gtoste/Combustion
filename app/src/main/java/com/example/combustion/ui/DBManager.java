package com.example.combustion.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.combustion.DatabaseHelper;

import java.util.ArrayList;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Fueling fueling) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.fuel_refueled, fueling.getFuel_refueled());
        contentValue.put(DatabaseHelper.odometer, fueling.getOdometer());
        contentValue.put(DatabaseHelper.cost_per_liter, fueling.getCost_per_liter());
        contentValue.put(DatabaseHelper.station, fueling.getStation());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public ArrayList<Fueling> fetchAll() {
        Cursor cursor = database.rawQuery("SELECT * FROM FUEL", null);
        ArrayList<Fueling> fuelings = new ArrayList<>();
        if (cursor != null) {
            if(cursor.moveToFirst())
            {
                do {
                    fuelings.add(new Fueling(cursor.getInt(1),
                            cursor.getDouble(2),
                            cursor.getString(3),
                            cursor.getDouble(4)));
                } while (cursor.moveToNext());
            }

        }
        cursor.close();
        return fuelings;
    }
}
