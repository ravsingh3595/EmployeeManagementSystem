package com.androidproject.employeemanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidproject.employeemanagementsystem.model.user.User;

import java.util.ArrayList;


public class DBUser
{
    public static final String TABLE_USER = "tblUser";
    public static final String USER_FULL_NAME = "fullName";
    public static final String USER_EMAIL = "emailId";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PICTURE = "picture";
    public static final String USER_BIRTH_DATE = "birthDate";
    public static final String USER_ADDRESS = "address";
    public static final String USER_CITY = "city";
    public static final String USER_PROVINCE = "province";
    public static final String USER_COUNTRY = "country";
    public static final String USER_LATITUDE = "lat";
    public static final String USER_LONGITUDE = "long";

    private Context context;
    private DBHelper dbHelper;

    public DBUser(Context context)
    {
        this.context = context;
    }

    public void insertUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_FULL_NAME, user.getFullname());
        contentValues.put(USER_EMAIL, user.getEmail());
        contentValues.put(USER_PASSWORD, user.getPassword());

        database.insert(TABLE_USER, null, contentValues);
        database.close();

    }

    public void updateUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_FULL_NAME, user.getFullname());
        //contentValues.put(USER_PASSWORD, user.getPassword());
        //contentValues.put(USER_PICTURE, user.getPicture());
        contentValues.put(USER_BIRTH_DATE, user.getBirthDate());
        contentValues.put(USER_ADDRESS, user.getAddress());
        contentValues.put(USER_CITY, user.getCity());
        contentValues.put(USER_PROVINCE, user.getProvince());
        contentValues.put(USER_COUNTRY, user.getCountry());
        //contentValues.put(USER_LATITUDE, user.getLatitude());
        //contentValues.put(USER_LONGITUDE, user.getLongitude());
        database.update(TABLE_USER,  contentValues, USER_EMAIL + "=?", new String[]{user.getEmail()});
        database.close();
    }


    public void deleteUser(User user)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(TABLE_USER, USER_EMAIL + "=?", new String[]{user.getEmail()});
        database.close();
    }

    public ArrayList<User> getAllUser()
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(TABLE_USER,
                null,
                null,
                null,
                null,
                null,
                null);

        ArrayList<User> userArrayList = new ArrayList<>();
        if(cursor !=null)
        {
            if(cursor.getCount() > 0)
            {
                while (cursor.moveToNext())
                {
                    User user = new User();
                    user.setFullname(cursor.getString(0));
                    user.setEmail(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setPicture(cursor.getString(3));
                    user.setBirthDate(cursor.getString(4));
                    user.setAddress(cursor.getString(5));
                    user.setCity(cursor.getString(6));
                    user.setProvince(cursor.getString(7));
                    user.setCountry(cursor.getString(8));
                    user.setLatitude(cursor.getDouble(9));
                    user.setLongitude(cursor.getDouble(10));
                    userArrayList.add(user);
                }
            }
        }
        database.close();

        return userArrayList;

    }

    public boolean isValidUser(String email, String password)
    {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(TABLE_USER,
                null,
                USER_EMAIL + "=? AND " + USER_PASSWORD + "=?",
                new String[]{email, password},
                null,
                null,
                null);


        if(cursor !=null)
        {
            if(cursor.getCount() >=1 )
            {
                Log.d("DBUser inside", String.valueOf(cursor.getCount()));
                return true;

            }
        }

        Log.d("DBUser outside", String.valueOf(cursor.getCount()) );
        return false;
    }


}
