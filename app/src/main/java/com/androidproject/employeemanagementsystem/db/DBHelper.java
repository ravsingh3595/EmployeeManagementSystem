package com.androidproject.employeemanagementsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME  = "dbPayroll";
    private static final int DB_VERSION = 1;
    private static final String TAG = DBHelper.class.getCanonicalName();

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d(TAG, "onCreate Called");

        String payrollTable = "CREATE TABLE tblPayroll(" +
                " employeeId INTEGER PRIMARY KEY ," +
                " fullName TEXT default null," +
                " birthDate INTEGER default null," +
                " employeeType TEXT default null," +
                " rate DOUBLE default null," +
                " hoursWorked DOUBLE default null," +
                " fixedAmount DOUBLE default null," +
                " commissionPercent DOUBLE default null," +
                " schoolName TEXT default null," +
                " salary DOUBLE default null," +
                " bonus DOUBLE default null," +
                " totalPay DOUBLE default null," +
                " vehicleType TEXT default null," +
                " make TEXT default null," +
                " plate TEXT default null," +
                " vehicleColour TEXT default null," +
                " manufacturingYear INTEGER default null," +
                " isInsurance BOOLEAN default null)";

        sqLiteDatabase.execSQL(payrollTable);

        Log.d(TAG, "onCreate Success for Payroll");

        String userTable = "CREATE TABLE " + DBUser.TABLE_USER + "(" +
                " userId INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DBUser.USER_FULL_NAME + " TEXT default null,"
                + DBUser.USER_EMAIL + " TEXT default null,"
                + DBUser.USER_PASSWORD + " TEXT default null,"
                + DBUser.USER_PICTURE + " BLOB default null,"
                + DBUser.USER_BIRTH_DATE + " TEXT default null,"
                + DBUser.USER_ADDRESS + " TEXT default null,"
                + DBUser.USER_CITY + " TEXT default null,"
                + DBUser.USER_PROVINCE + " TEXT default null,"
                + DBUser.USER_COUNTRY + " TEXT default null,"
                + DBUser.USER_LATITUDE + " DOUBLE default null,"
                + DBUser.USER_LONGITUDE + " DOUBLE default null)";

        sqLiteDatabase.execSQL(userTable);

        Log.d(TAG, "onCreate Success for User");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblPayroll");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBUser.TABLE_USER);
        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
