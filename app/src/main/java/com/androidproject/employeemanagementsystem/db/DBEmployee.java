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
import com.androidproject.employeemanagementsystem.model.vehicle.Car;
import com.androidproject.employeemanagementsystem.model.vehicle.Motorcycle;
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


    public ArrayList<Employee> getAllUser(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Employee employee = null;
        Vehicle vehicle = null;
//
//        Cursor cursor = database.query("tblPayroll",
//                null,
//                null,
//                null,
//                null,
//                null,
//                null);

        String selectQuery = "SELECT * FROM tblPayroll";
        Cursor cursor =database.rawQuery(selectQuery, null);
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    Log.d("DBEmployeeCheck", cursor.getString(1));

                        if (employee instanceof CommissionBasedPartTime) {
                            CommissionBasedPartTime com = (CommissionBasedPartTime) employee;
                            com.setName(cursor.getString(1));
                            com.setCalBirthYear(cursor.getInt(2));
                            com.setEmployee(cursor.getString(3));
                            com.setRate(cursor.getDouble(4));
                            com.setHoursWorked(cursor.getDouble(5));
                            com.setCommissionPercentage(cursor.getDouble(7));
                            if (com.getVehicleType() != null) {
                                if (com.getVehicleType().equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));
                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    com.setVehicle(car);
                                    com.setVehicleType(cursor.getString(12));
                                }
                                else if (com.getVehicleType().equals("motor")){
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    com.setVehicle(motorcycle);
                                    com.setVehicleType(cursor.getString(12));
                                }

                            }
                            employee = com;
                        }
                        if (employee instanceof FixedBasedPartTime) {
                            FixedBasedPartTime fix = (FixedBasedPartTime) employee;
                            fix.setName(cursor.getString(1));
                            fix.setCalBirthYear(cursor.getInt(2));
                            fix.setEmployee(cursor.getString(3));
                            fix.setRate(cursor.getDouble(4));
                            fix.setHoursWorked(cursor.getDouble(5));
                            fix.setFixedAmount(cursor.getDouble(6));
                            //fix.setVehicle(vehicle);
                            if (fix.getVehicleType() != null) {
                                if (fix.getVehicleType().equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));
                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    fix.setVehicle(car);
                                    fix.setVehicleType(cursor.getString(12));
                                }
                                else if (fix.getVehicleType().equals("motor")){
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    fix.setVehicle(motorcycle);
                                    fix.setVehicleType(cursor.getString(12));
                                }

                            }
                            employee = fix;
                        }
                        if (employee instanceof Intern) {
                            Intern intern = (Intern) employee;
                            intern.setName(cursor.getString(1));
                            intern.setCalBirthYear(cursor.getInt(2));
                            intern.setEmployee(cursor.getString(3));
                            intern.setSchoolName(cursor.getString(8));
                            intern.setVehicle(vehicle);
                            if (intern.getVehicleType() != null) {
                                if (intern.getVehicleType().equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));
                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    intern.setVehicle(car);
                                    intern.setVehicleType(cursor.getString(12));
                                }
                                else if (intern.getVehicleType().equals("motor")){
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    intern.setVehicle(motorcycle);
                                    intern.setVehicleType(cursor.getString(12));
                                }

                            }
                            employee = intern;
                        }
                        if (employee instanceof FullTime) {
                            FullTime fullTime = (FullTime) employee;
                            fullTime.setName(cursor.getString(1));
                            fullTime.setCalBirthYear(cursor.getInt(2));
                            fullTime.setEmployee(cursor.getString(3));
                            fullTime.setSalary(cursor.getDouble(9));
                            fullTime.setBonus(cursor.getDouble(10));
                            fullTime.setVehicle(vehicle);
                            if (fullTime.getVehicleType() != null) {
                                if (fullTime.getVehicleType().equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));
                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    fullTime.setVehicle(car);
                                    fullTime.setVehicleType(cursor.getString(12));
                                }
                                else if (fullTime.getVehicleType().equals("motor")){
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    fullTime.setVehicle(motorcycle);
                                    fullTime.setVehicleType(cursor.getString(12));
                                }

                            }
                            employee = fullTime;
                        }
                    }
                    employeeArrayList.add(employee);
                }
            }

        database.close();

        return employeeArrayList;
    }


    private ContentValues getContentValuesObject(Employee employee){

        ContentValues values =  new ContentValues();
        Log.d("ContentValues", "0");


        if (employee instanceof CommissionBasedPartTime){
            CommissionBasedPartTime com = (CommissionBasedPartTime) employee;
            values.put("fullName", com.getName());
            values.put("birthDate", com.getCalBirthYear());
            values.put("employeeType", com.getEmployee());
            values.put("vehicleType", com.getVehicleType());
            values.put("hoursWorked", com.getHoursWorked());
            values.put("rate", com.getRate());
            values.put("commissionPercent", com.getCommissionPercentage());
            values.put("totalPay", com.calEarnings());
            if (com.getVehicleType() != null){
                if (com.getVehicleType().equals("car")) {
                    Car car = new Car();
                    values.put("make", car.getCompany());
                    values.put("plate", car.getPlate());
                    values.put("vehicleColour", car.getColour());
                    values.put("manufacturingYear", car.getYear());
                    Log.d("ContentValues", "car");
                }
                else if (com.getVehicleType().equals("motor")){
                    Motorcycle motorcycle = new Motorcycle();
                    values.put("make", motorcycle.getCompany());
                    values.put("plate", motorcycle.getPlate());
                    values.put("vehicleColour", motorcycle.getColour());
                    values.put("manufacturingYear", motorcycle.getYear());
                    Log.d("ContentValues", "motor");
                }
            }
            Log.d("ContentValues", "com");
        }
        if (employee instanceof FixedBasedPartTime){
            FixedBasedPartTime fix = (FixedBasedPartTime) employee;
            values.put("fullName", fix.getName());
            values.put("birthDate", fix.getCalBirthYear());
            values.put("employeeType", fix.getEmployee());
            values.put("vehicleType", fix.getVehicleType());
            values.put("hoursWorked", fix.getHoursWorked());
            values.put("rate", fix.getRate());
            values.put("fixedAmount", fix.getFixedAmount());
            values.put("totalPay", fix.calEarnings());
            Log.d("ContentValues", "fix");
            if (fix.getVehicleType() != null){
                if (fix.getVehicleType().equals("car")) {
                    Car car = new Car();
                    values.put("make", car.getCompany());
                    values.put("plate", car.getPlate());
                    values.put("vehicleColour", car.getColour());
                    values.put("manufacturingYear", car.getYear());
                    Log.d("ContentValues", "car");
                }
                else if (fix.getVehicleType().equals("motor")){
                    Motorcycle motorcycle = new Motorcycle();
                    values.put("make", motorcycle.getCompany());
                    values.put("plate", motorcycle.getPlate());
                    values.put("vehicleColour", motorcycle.getColour());
                    values.put("manufacturingYear", motorcycle.getYear());
                    Log.d("ContentValues", "motor");
                }
            }
        }
        if (employee instanceof Intern){
            Intern intern = (Intern) employee;
            values.put("fullName", intern.getName());
            values.put("birthDate", intern.getCalBirthYear());
            values.put("employeeType", intern.getEmployee());
            values.put("vehicleType", intern.getVehicleType());
            values.put("schoolName", intern.getSchoolName());
            values.put("totalPay", intern.calEarnings());
            if (intern.getVehicleType() != null){
                if (intern.getVehicleType().equals("car")) {
                    Car car = new Car();
                    values.put("make", car.getCompany());
                    values.put("plate", car.getPlate());
                    values.put("vehicleColour", car.getColour());
                    values.put("manufacturingYear", car.getYear());
                    Log.d("ContentValues", "car");
                }
                else if (intern.getVehicleType().equals("motor")){
                    Motorcycle motorcycle = new Motorcycle();
                    values.put("make", motorcycle.getCompany());
                    values.put("plate", motorcycle.getPlate());
                    values.put("vehicleColour", motorcycle.getColour());
                    values.put("manufacturingYear", motorcycle.getYear());
                    Log.d("ContentValues", "motor");
                }
            }
            Log.d("ContentValues", intern.getSchoolName());
        }
        if (employee instanceof FullTime){
            FullTime fullTime = (FullTime) employee;
            values.put("fullName", fullTime.getName());
            values.put("birthDate", fullTime.getCalBirthYear());
            values.put("employeeType", fullTime.getEmployee());
            values.put("vehicleType", fullTime.getVehicleType());

            values.put("salary", fullTime.getSalary());
            values.put("bonus", fullTime.getBonus());
            values.put("totalPay", fullTime.calEarnings());
            if (fullTime.getVehicleType() != null){
                if (fullTime.getVehicleType().equals("car")) {
                    Car car = new Car();
                    values.put("make", car.getCompany());
                    values.put("plate", car.getPlate());
                    values.put("vehicleColour", car.getColour());
                    values.put("manufacturingYear", car.getYear());
                    Log.d("ContentValues", "car");
                }
                else if (fullTime.getVehicleType().equals("motor")){
                    Motorcycle motorcycle = new Motorcycle();
                    values.put("make", motorcycle.getCompany());
                    values.put("plate", motorcycle.getPlate());
                    values.put("vehicleColour", motorcycle.getColour());
                    values.put("manufacturingYear", motorcycle.getYear());
                    Log.d("ContentValues", "motor");
                }
            }
            Log.d("ContentValues", "Fulltime");
        }
        return values;
    }

}
