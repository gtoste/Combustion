package com.example.combustion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.combustion.ui.Fueling;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Names
    public static final String TABLE_NAME_FUEL_HISTORY = "FUEL_HISTORY";
    public static final String TABLE_NAME_CARS = "CARS";

    // FUEL_HISTORY
    public static final String _ID = "_id";
    public static final String COLUMN_ODOMETER = "odometer";
    public static final String COLUMN_FUEL_REFUELED = "fuel_refueled";
    public static final String COLUMN_STATION = "station";
    public static final String COLUMN_COST_PER_LITER = "cost_per_liter";
    public static final String COLUMN_CAR = "CAR";
    public static final String COLUMN_DATE = "date";

    //CARS
    public static final String COLUMN_CAR_NAME = "CAR_NAME";

    // Database Information
    static final String DB_NAME = "COMBUSTION.DB";

    private static final String CREATE_TABLE_FUEL_HISTORY = "create table " + TABLE_NAME_FUEL_HISTORY + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ODOMETER + " INTEGER NOT NULL, "
            + COLUMN_FUEL_REFUELED + " REAL,"
            + COLUMN_STATION + " TEXT, "
            + COLUMN_COST_PER_LITER + " REAL,"
            + COLUMN_CAR + " TEXT,"
            + COLUMN_DATE + " TEXT"
            + ");";

    private static final String CREATE_TABLE_CARS = "create table " + TABLE_NAME_CARS + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CAR_NAME + " TEXT NOT NULL)";

    // database version
    static final int DB_VERSION = 3;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_FUEL_HISTORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_CARS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CARS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FUEL_HISTORY);
        onCreate(sqLiteDatabase);
    }

    public boolean addCarRecord(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CAR_NAME, name);

        long result = db.insert(TABLE_NAME_CARS, null, contentValues);
        db.close();
        return result != -1;
    }

    public ArrayList<Fueling> getAllFuelings(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Fueling> fuelings = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME_FUEL_HISTORY;
        if(db != null){
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    int id = cursor.getInt(0);
                    int odometer = cursor.getInt(1);
                    double fuel_refueled = cursor.getDouble(2);
                    String station = cursor.getString(3);
                    double cost_per_liter = cursor.getDouble(4);
                    String car = cursor.getString(5);
                    String date = cursor.getString(6);

                    Fueling fueling = new Fueling(id, odometer, fuel_refueled, station, cost_per_liter, car, date);
                    fuelings.add(fueling);
                }
            }

            cursor.close();
            db.close();
        }
        return fuelings;
    }

    public ArrayList<String> getAllCars(){
        ArrayList<String> cars = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_CARS;

        if(db != null)
        {
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    cars.add(cursor.getString(1));
                }
            }
            cursor.close();
            db.close();
        }
        return cars;
    }
}
