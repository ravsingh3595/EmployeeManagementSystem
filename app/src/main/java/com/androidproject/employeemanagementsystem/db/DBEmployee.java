package com.androidproject.employeemanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.androidproject.employeemanagementsystem.SingletonClass;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.user.User;

public class DBEmployee {

    private Context context;
    private DBHelper dbHelper;

    public DBEmployee(Context context) {
        this.context = context;
    }

    public void insertUser(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /*
        String payrollTable = "CREATE TABLE tblPayroll(" +
                " employeeId INTEGER PRIMARY KEY ," +
                " fullName TEXT default null," +
                " birthDate INTEGER default null," +
                " employeeType INTEGER default null," +
                " rate FLOAT default null," +
                " hoursWorked FLOAT default null," +
                " fixedAmount FLOAT default null," +
                " commissionPercent FLOAT default null," +
                " schoolName TEXT default null," +
                " salary FLOAT default null," +
                " bonus FLOAT default null," +
                " totalPay FLOAT default null," +
                " vehicleType INTEGER default null," +
                " make TEXT default null," +
                " plate TEXT default null," +
                " vehicleColour TEXT default null," +
                " manufacturingYear INTEGER default null," +
                " isInsurance BOOLEAN default null)";
         */
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullName", employee.getName());
        contentValues.put("birthDate", employee.getAge());
        /*
            if(employeeType == 1, 2, 3, 4)
            type 1 = commissionbased partTime
            type 2 = fixed based parttime
            type 3 = intern
            type 4 = full time

         */
    //      if(employee.getEmployee() ==))
//        contentValues.put("employeeType", user.getPassword());

        database.insert("tblPayroll", null, contentValues);
        database.close();
    }
}
