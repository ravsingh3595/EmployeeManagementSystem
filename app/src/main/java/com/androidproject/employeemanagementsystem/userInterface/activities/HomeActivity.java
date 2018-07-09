package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBHelper;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        DBHelper mDBHelper = new DBHelper(this);
        SQLiteDatabase mSQLiteDatabase = mDBHelper.getWritableDatabase();



    }
}
