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

    public ArrayList<Employee> getAllUser(Employee employee,Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Vehicle vehicle = null;

        ArrayList<Employee> employeeArrayList;

        String selectQuery = "SELECT * FROM tblPayroll";
        Cursor cursor =database.rawQuery(selectQuery, null);
            employeeArrayList = new ArrayList<>();
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {


                        Log.d("DBEmployeeCheck row count: ", String.valueOf(cursor.getCount()));
                        try {
                            if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("Commission based")) {
                                CommissionBasedPartTime com = new CommissionBasedPartTime();
                                com.setName(cursor.getString(1));
                                com.setCalBirthYear(cursor.getInt(2));
                                com.setEmployee(cursor.getString(3));
                                com.setRate(cursor.getDouble(4));
                                com.setHoursWorked(cursor.getDouble(5));
                                com.setCommissionPercentage(cursor.getDouble(7));
                                Log.d("DBEmployeeCheck", cursor.getString(1));


                                if (com.getVehicleType() != null)
                                {
                                    /*
                                    if (com.getVehicleType().equals("car")) {
                                        Car car = new Car();
                                        car.setCompany(cursor.getString(13));
                                        car.setPlate(cursor.getString(14));
                                        car.setColour(cursor.getString(15));
                                        car.setYear(cursor.getInt(16));
                                        vehicle = car;
                                        com.setVehicle(vehicle);
                                        com.setVehicleType(cursor.getString(12));
                                    } else if (com.getVehicleType().equals("motor")) {
                                        Motorcycle motorcycle = new Motorcycle();
                                        motorcycle.setCompany(cursor.getString(13));
                                        motorcycle.setPlate(cursor.getString(14));
                                        motorcycle.setColour(cursor.getString(15));
                                        motorcycle.setYear(cursor.getInt(16));
                                        vehicle = motorcycle;
                                        com.setVehicle(vehicle);
                                        com.setVehicleType(cursor.getString(12));
                                    }*/

                                    if (cursor.getString(cursor.getColumnIndex("vehicleType")).equals("car")){
                                        Car car = new Car();
                                        car.setCompany(cursor.getString(13));
                                        car.setPlate(cursor.getString(14));

                                        car.setColour(cursor.getString(15));
                                        car.setYear(cursor.getInt(16));
                                        vehicle = car;
                                        com.setVehicle(vehicle);
                                        com.setVehicleType(cursor.getString(12));
                                    } else if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("motor")) {
                                        Motorcycle motorcycle = new Motorcycle();
                                        motorcycle.setCompany(cursor.getString(13));
                                        motorcycle.setPlate(cursor.getString(14));
                                        motorcycle.setColour(cursor.getString(15));
                                        motorcycle.setYear(cursor.getInt(16));
                                        vehicle = motorcycle;
                                        com.setVehicle(vehicle);
                                        com.setVehicleType(cursor.getString(12));
                                    }

                                }
                                employee = com;
                                employeeArrayList.add(employee);
                            }
                            if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("Fixed based")) {
                                FixedBasedPartTime fix = new FixedBasedPartTime();
                                fix.setName(cursor.getString(1));
                                fix.setCalBirthYear(cursor.getInt(2));
                                fix.setEmployee(cursor.getString(3));
                                fix.setRate(cursor.getDouble(4));
                                fix.setHoursWorked(cursor.getDouble(5));
                                fix.setFixedAmount(cursor.getDouble(6));
//                                fix.setVehicle(vehicle);
                                Log.d("DBEmployeeCheck", cursor.getString(1));

                                /*
                                if (fix.getVehicleType() != null) {
                                    if (fix.getVehicleType().equals("car")) {
                                        Car car = new Car();
                                        car.setCompany(cursor.getString(13));
                                        car.setPlate(cursor.getString(14));
                                        car.setColour(cursor.getString(15));
                                        car.setYear(cursor.getInt(16));
                                        vehicle = car;
                                        fix.setVehicle(vehicle);
                                        fix.setVehicleType(cursor.getString(12));
                                    } else if (fix.getVehicleType().equals("motor")) {
                                        Motorcycle motorcycle = new Motorcycle();
                                        motorcycle.setCompany(cursor.getString(13));
                                        motorcycle.setPlate(cursor.getString(14));
                                        motorcycle.setColour(cursor.getString(15));
                                        motorcycle.setYear(cursor.getInt(16));
                                        vehicle = motorcycle;
                                        fix.setVehicle(vehicle);
                                        fix.setVehicleType(cursor.getString(12));
                                    }

                                }*/

                                if (cursor.getString(cursor.getColumnIndex("vehicleType")).equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));

                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    fix.setVehicle(vehicle);
                                    fix.setVehicleType(cursor.getString(12));
                                } else if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("motor")) {
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    fix.setVehicle(vehicle);
                                    fix.setVehicleType(cursor.getString(12));
                                }

                                employee = fix;
                                employeeArrayList.add(employee);
                            }
                            if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("Intern")) {
                                Intern intern = new Intern();
                                intern.setName(cursor.getString(1));
                                intern.setCalBirthYear(cursor.getInt(2));
                                intern.setEmployee(cursor.getString(3));
                                intern.setSchoolName(cursor.getString(8));
//                               intern.setVehicle(vehicle);
                                if (cursor.getString(cursor.getColumnIndex("vehicleType")).equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));

                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    intern.setVehicle(vehicle);
                                    intern.setVehicleType(cursor.getString(12));
                                } else if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("motor")) {
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    intern.setVehicle(vehicle);
                                    intern.setVehicleType(cursor.getString(12));
                                }

                                /*
                                Log.d("DBEmployeeCheck3", cursor.getString(1));
                                if (intern.getVehicleType() != null) {
                                    if (intern.getVehicleType().equals("car")) {
                                        Car car = new Car();
                                        car.setCompany(cursor.getString(13));
                                        car.setPlate(cursor.getString(14));

                                        car.setColour(cursor.getString(15));
                                        car.setYear(cursor.getInt(16));
                                        vehicle = car;
                                        intern.setVehicle(vehicle);
                                        intern.setVehicleType(cursor.getString(12));
                                    } else if (intern.getVehicleType().equals("motor")) {
                                        Motorcycle motorcycle = new Motorcycle();
                                        motorcycle.setCompany(cursor.getString(13));
                                        motorcycle.setPlate(cursor.getString(14));
                                        motorcycle.setColour(cursor.getString(15));
                                        motorcycle.setYear(cursor.getInt(16));
                                        vehicle = motorcycle;
                                        intern.setVehicle(vehicle);
                                        intern.setVehicleType(cursor.getString(12));
                                    }

                                }*/
                                employee = intern;
                                employeeArrayList.add(employee);
                            }
                            if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("Fulltime")) {
                                FullTime fullTime = new FullTime();
                                fullTime.setName(cursor.getString(1));
                                fullTime.setCalBirthYear(cursor.getInt(2));
                                fullTime.setEmployee(cursor.getString(3));
                                fullTime.setSalary(cursor.getDouble(9));
                                fullTime.setBonus(cursor.getDouble(10));
//                                fullTime.setVehicle(vehicle);
                                Log.d("DBEmployeeCheck", cursor.getString(1));

                                if (cursor.getString(cursor.getColumnIndex("vehicleType")).equals("car")){
                                    Car car = new Car();
                                    car.setCompany(cursor.getString(13));
                                    car.setPlate(cursor.getString(14));

                                    car.setColour(cursor.getString(15));
                                    car.setYear(cursor.getInt(16));
                                    vehicle = car;
                                    fullTime.setVehicle(vehicle);
                                    fullTime.setVehicleType(cursor.getString(12));
                                } else if (cursor.getString(cursor.getColumnIndex("employeeType")).equals("motor")) {
                                    Motorcycle motorcycle = new Motorcycle();
                                    motorcycle.setCompany(cursor.getString(13));
                                    motorcycle.setPlate(cursor.getString(14));
                                    motorcycle.setColour(cursor.getString(15));
                                    motorcycle.setYear(cursor.getInt(16));
                                    vehicle = motorcycle;
                                    fullTime.setVehicle(vehicle);
                                    fullTime.setVehicleType(cursor.getString(12));
                                }

                                /*
                                if (fullTime.getVehicleType() != null) {
                                    if (fullTime.getVehicleType().equals("car")) {
                                        Car car = new Car();
                                        car.setCompany(cursor.getString(13));
                                        car.setPlate(cursor.getString(14));
                                        car.setColour(cursor.getString(15));
                                        car.setYear(cursor.getInt(16));
                                        vehicle = car;
                                        fullTime.setVehicle(vehicle);
                                        fullTime.setVehicleType(cursor.getString(12));
                                    } else if (fullTime.getVehicleType().equals("motor")) {
                                        Motorcycle motorcycle = new Motorcycle();
                                        motorcycle.setCompany(cursor.getString(13));
                                        motorcycle.setPlate(cursor.getString(14));
                                        motorcycle.setColour(cursor.getString(15));
                                        motorcycle.setYear(cursor.getInt(16));
                                        vehicle = motorcycle;
                                        fullTime.setVehicle(vehicle);
                                        fullTime.setVehicleType(cursor.getString(12));
                                    }
                                }*/
                                employee = fullTime;
                                employeeArrayList.add(employee);
                            }
                        } catch (NullPointerException a) {
                            Log.d("DBEmployeeCheck", "In catch");
                        }
                    }
                    Log.d("DBEmployeeCheck", "Added to employee list");
//                    employeeArrayList.add(employee);
                }
            }
        database.close();
        return employeeArrayList;
    }


    private ContentValues getContentValuesObject(Employee employee){

        ContentValues values =  new ContentValues();

        if (employee.getVehicle() != null){
            values.put("make", employee.getVehicle().getCompany());
            values.put("plate", employee.getVehicle().getPlate());
            values.put("vehicleColour", employee.getVehicle().getColour());
            values.put("manufacturingYear", employee.getVehicle().getYear());
            Log.d("ContentValues", "car");
        }
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

        }
        if (employee instanceof Intern){
            Intern intern = (Intern) employee;
            values.put("fullName", intern.getName());
            values.put("birthDate", intern.getCalBirthYear());
            values.put("employeeType", intern.getEmployee());
            values.put("vehicleType", intern.getVehicleType());
            values.put("schoolName", intern.getSchoolName());
            values.put("totalPay", intern.calEarnings());

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
            Log.d("ContentValues", "Fulltime");
            }
        return values;
    }
}