package com.example.combustion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "FUEL";

    // Table columns
    public static final String _ID = "_id";
    public static final String odometer = "odometer";
    public static final String fuel_refueled = "fuel_refueled";
    public static final String station = "station";
    public static final String cost_per_liter = "cost_per_liter";

    // Database Information
    static final String DB_NAME = "COMBUSTION.DB";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + odometer + " INTEGER NOT NULL, "
            + fuel_refueled + " REAL,"
            + station + " TEXT, "
            + cost_per_liter + " REAL"
            +");";

    // database version
    static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
