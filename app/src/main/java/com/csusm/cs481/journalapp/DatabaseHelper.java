package com.csusm.cs481.journalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "JournalEntries.db";
    public static final String TABLE_NAME = "Journal_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "DESCRIPTION";
    public static final String COL_4 = "DAY";
    public static final String COL_5 = "MONTH";
    public static final String COL_6 = "YEAR";
    public static final String COL_7 = "CATEGORY";
    //public static final String COL_8 = "LOCATION";
    //public static final String COL_8 = "DATEORDER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT,  DESCRIPTION TEXT, DAY INTEGER, MONTH INTEGER, YEAR INTEGER, CATEGORY TEXT, LOCATION TEXT/*, DATEORDER TEXT*/)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String desc, int day, int month, int year, String category, String location, int date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, desc);
        contentValues.put(COL_4, day);
        contentValues.put(COL_5, month);
        contentValues.put(COL_6, year);
        contentValues.put(COL_7, category);
        //contentValues.put(COL_8, location);
        //contentValues.put(COL_8, date);

        Log.d("DATABASEHELPER", "Adding " + title + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues); //will return -1 for false or value of column
        if (result == -1) {
            Log.d("Error adding", "Error while adding");
            return false;
        }
        Log.d("Good adding", "Should be added");

        return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //* means select all
        Cursor returnMe = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return returnMe;
    }
}
