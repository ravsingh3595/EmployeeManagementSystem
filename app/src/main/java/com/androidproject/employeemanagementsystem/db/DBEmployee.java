package com.androidproject.employeemanagementsystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.WindowId;

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

    public void updateEmployee(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = getContentValuesObject(employee);

        database.update("tblPayroll",  values, "fullName" + "=?", new String[]{(employee.getName())});
        database.close();

    }


    public void deleteEmployee(Employee employee) {
        dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        database.delete("tblPayroll", "fullName" + "=?", new String[]{(employee.getName())});
        database.close();

    }

//    public ArrayList<Employee> getAllUser() {
//        dbHelper = new DBHelper(context);
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        ArrayList<Employee> employeeArrayList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM tblPayroll";
//        Cursor cursor =database.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()){
//            do {
//                 /*
//                     " employeeId INTEGER PRIMARY KEY ," + 1
//                " fullName TEXT default null," + 2
//                " birthDate INTEGER default null," + 3
//                " employeeType INTEGER default null," + 4
//                " rate FLOAT default null," + 5
//                " hoursWorked FLOAT default null," + 6
//                " fixedAmount FLOAT default null," + 7
//                " commissionPercent FLOAT default null," + 8
//                " schoolName TEXT default null," + 9
//                " salary FLOAT default null," + 10
//                " bonus FLOAT default null," + 11
//                " totalPay FLOAT default null," + 12
//                " vehicleType INTEGER default null," + 13
//                " make TEXT default null," + 14
//                " plate TEXT default null," + 15
//                " vehicleColour TEXT default null," + 16
//                " manufacturingYear INTEGER default null," + 17
//                " isInsurance BOOLEAN default null)";
//
//                     */
////                Employee employee = getPayrollObject(cursor);
//
//
//               // employeeArrayList.add(employee);
//            }while (cursor.moveToNext());
//        }
//        database.close();
//        return employeeArrayList;
//    }


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

                    employee.setName(cursor.getString(1));
                    employee.setCalBirthYear(cursor.getInt(2));
                    employee.setEmployee(cursor.getString(3));
                    if (employee.getVehicle() != null){
                        employee.getVehicle().setCompany(cursor.getString(13));
                        employee.getVehicle().setPlate(cursor.getString(14));
                        employee.getVehicle().setColour(cursor.getString(15));
                        employee.getVehicle().setYear(cursor.getInt(16));

                    }
                    if (employee instanceof CommissionBasedPartTime){
                        CommissionBasedPartTime com = (CommissionBasedPartTime) employee;
                        com.setRate(cursor.getDouble(4));
                        com.setHoursWorked(cursor.getDouble(5));
                        com.setCommissionPercentage(cursor.getDouble(7));
                        employee = com;
                    }
                    if (employee instanceof FixedBasedPartTime){
                        FixedBasedPartTime fix = (FixedBasedPartTime) employee;
                        fix.setRate(cursor.getDouble(4));
                        fix.setHoursWorked(cursor.getDouble(5));
                        fix.setFixedAmount(cursor.getDouble(6));
                        employee = fix;
                    }
                    if (employee instanceof Intern){
                        Intern intern =(Intern) employee;
                        intern.setSchoolName(cursor.getString(8));
                        employee = intern;
                    }
                    if (employee instanceof FullTime){
                        FullTime fullTime = (FullTime) employee;
                        fullTime.setSalary(cursor.getDouble(9));
                        fullTime.setBonus(cursor.getDouble(10));
                        }
                    employeeArrayList.add(employee);
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
