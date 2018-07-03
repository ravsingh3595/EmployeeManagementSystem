package com.androidproject.employeemanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME  = "dbPayroll";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        //Log.d("DB", "onCreate Called");
        sqLiteDatabase.execSQL("CREATE TABLE employee("
                + " sid INTEGER PRIMARY KEY,"
                + " snm TEXT)");
        Log.d("DB", "onCreate Success");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        // Log.d("DB", "onUpgrade Called");
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblStudent");

        // Create tables again
        onCreate(sqLiteDatabase);

    }
}
