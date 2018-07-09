package com.androidproject.employeemanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androidproject.employeemanagementsystem.SingletonClass;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.FullTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.Intern;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.FixedBasedPartTime;
import com.androidproject.employeemanagementsystem.model.user.User;

import java.util.ArrayList;

public class DBEmployee {

    private Context context;
    private DBHelper dbHelper;

    public DBEmployee(Context context) {
        this.context = context;
    }


    public void insertUser(Employee employee) {
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

    public ArrayList<Employee> getAllUser() {
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
//                   Employee employee = new Employee();
//                    user.setEmail(cursor.getString(0));
//                    user.setPassword(cursor.getString(1));
//                    userArrayList.add(user);
//                    Log.d("DB", user.getEmail() + " : " + user.getPassword());
                }
            }
        }
        database.close();

        return employeeArrayList;
    }


    private ContentValues getContentValuesObject(Employee employee){

        ContentValues values =  new ContentValues();
        values.put("name", employee.getName());
        values.put("birthdate", employee.getCalBirthYear());
        values.put("employeeType", employee.getEmployee());

        if (employee.getVehicle() != null){
            values.put("make", employee.getVehicle().getCompany());
            values.put("plate", employee.getVehicle().getPlate());
            values.put("vehicleColour", employee.getVehicle().getColour());
            values.put("manufacturingYear", employee.getVehicle().getYear());
        }
        if (employee instanceof CommissionBasedPartTime){
            CommissionBasedPartTime com = new CommissionBasedPartTime();
            values.put("hoursWorked", com.getHoursWorked());
            values.put("rate", com.getRate());
            values.put("commissionPercent", com.getCommissionPercentage());
            values.put("totalPay", com.calEarnings());
        }
        if (employee instanceof FixedBasedPartTime){
            FixedBasedPartTime fix = new FixedBasedPartTime();
            values.put("hoursWorked", fix.getHoursWorked());
            values.put("rate", fix.getRate());
            values.put("fixedAmount", fix.getFixedAmount());
            values.put("totalPay", fix.calEarnings());
        }
        if (employee instanceof Intern){
            Intern intern = new Intern();
            values.put("schoolName", intern.getSchoolName());
            values.put("totalPay", intern.calEarnings());
        }
        if (employee instanceof FullTime){
            FullTime fullTime = new FullTime();
            values.put("salary", fullTime.getSalary());
            values.put("bonus", fullTime.getBonus());
            values.put("totalPay", fullTime.calEarnings());
        }

        return values;
    }

}

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
