package com.androidproject.employeemanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidproject.employeemanagementsystem.SingletonClass;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.FullTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.Intern;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.FixedBasedPartTime;
import com.androidproject.employeemanagementsystem.model.user.User;
import com.androidproject.employeemanagementsystem.model.vehicle.Vehicle;

import java.util.ArrayList;

public class DBEmployee {

    private Context context;
    private DBHelper dbHelper;

    public DBEmployee(Context context) {
        this.context = context;
    }


    public void insertEmployee(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = getContentValuesObject(employee);
        database.insert("tblPayroll", null, values);
        database.close();
    }

    public void updateUser(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = getContentValuesObject(employee);

        //edit this
        // TODO
        database.update("tblPayroll",  values, "employeeId" + "=?", new String[]{String.valueOf(employee.getEmployeeId())});
        database.close();

    }


    public void deleteEmployee(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // TODO
        database.delete("tblPayroll", "employeeId" + "=?", new String[]{String.valueOf(employee.getEmployeeId())});
        database.close();

    }

    public ArrayList<Employee> getAllUser(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query("tblPayroll",
                null,
                null,
                null,
                null,
                null,
                null);

        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    /*
                     " employeeId INTEGER PRIMARY KEY ," + 1
                " fullName TEXT default null," + 2
                " birthDate INTEGER default null," + 3
                " employeeType INTEGER default null," + 4
                " rate FLOAT default null," + 5
                " hoursWorked FLOAT default null," + 6
                " fixedAmount FLOAT default null," + 7
                " commissionPercent FLOAT default null," + 8
                " schoolName TEXT default null," + 9
                " salary FLOAT default null," + 10
                " bonus FLOAT default null," + 11
                " totalPay FLOAT default null," + 12
                " vehicleType INTEGER default null," + 13
                " make TEXT default null," + 14
                " plate TEXT default null," + 15
                " vehicleColour TEXT default null," + 16
                " manufacturingYear INTEGER default null," + 17
                " isInsurance BOOLEAN default null)";

                     */
                   // if (Vehicle != null)
                    if (employee instanceof CommissionBasedPartTime){

                        employee = new CommissionBasedPartTime(cursor.getString(1), cursor.getInt(2), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(8));
                    }
                    if (employee instanceof FixedBasedPartTime){

                        employee = new FixedBasedPartTime(cursor.getString(1), cursor.getInt(2), cursor.getFloat(5), cursor.getFloat(6), cursor.getFloat(7));
                    }
                    if (employee instanceof Intern){

                        employee = new Intern(cursor.getString(0), cursor.getInt(1), cursor.getString(9));
                    }
                    if (employee instanceof FullTime){

                        employee = new FullTime(cursor.getString(1), cursor.getInt(2), cursor.getFloat(10), cursor.getFloat(11));
                    }

                }
            }
        }
        database.close();

        return employeeArrayList;
    }


    private ContentValues getContentValuesObject(Employee employee){

        ContentValues values =  new ContentValues();
        values.put("fullName", employee.getName());
        values.put("birthDate", employee.getCalBirthYear());
        values.put("employeeType", employee.getEmployee());
        Log.d("ContentValues", "0");

        if (employee.getVehicle() != null){
            values.put("make", employee.getVehicle().getCompany());
            values.put("plate", employee.getVehicle().getPlate());
            values.put("vehicleColour", employee.getVehicle().getColour());
            values.put("manufacturingYear", employee.getVehicle().getYear());
            Log.d("ContentValues", "vehicle");
        }
        if (employee instanceof CommissionBasedPartTime){
            CommissionBasedPartTime com = (CommissionBasedPartTime) employee;
            values.put("hoursWorked", com.getHoursWorked());
            values.put("rate", com.getRate());
            values.put("commissionPercent", com.getCommissionPercentage());
            values.put("totalPay", com.calEarnings());
            Log.d("ContentValues", "com");
        }
        if (employee instanceof FixedBasedPartTime){
            FixedBasedPartTime fix = (FixedBasedPartTime) employee;
            values.put("hoursWorked", fix.getHoursWorked());
            values.put("rate", fix.getRate());
            values.put("fixedAmount", fix.getFixedAmount());
            values.put("totalPay", fix.calEarnings());
            Log.d("ContentValues", "fix");
        }
        if (employee instanceof Intern){
            Intern intern = (Intern) employee;
            values.put("schoolName", intern.getSchoolName());
            values.put("totalPay", intern.calEarnings());
            Log.d("ContentValues", intern.getSchoolName());
        }
        if (employee instanceof FullTime){
            FullTime fullTime = (FullTime) employee;
            values.put("salary", fullTime.getSalary());
            values.put("bonus", fullTime.getBonus());
            values.put("totalPay", fullTime.calEarnings());
            Log.d("ContentValues", "Fulltime");
        }
        return values;
    }

}
