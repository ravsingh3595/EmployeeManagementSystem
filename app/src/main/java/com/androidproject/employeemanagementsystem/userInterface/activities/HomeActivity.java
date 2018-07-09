package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.db.DBHelper;
import com.androidproject.employeemanagementsystem.userInterface.fragments.AddEmployeeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//
//        DBHelper mDBHelper = new DBHelper(this);
//        SQLiteDatabase mSQLiteDatabase = mDBHelper.getWritableDatabase();

//        AddEmployeeFragment frag = new AddEmployeeFragment();
//        FragmentManager manager= getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.addEmployee, frag, "thirdFragment");
//        transaction.commit();


    }
}
