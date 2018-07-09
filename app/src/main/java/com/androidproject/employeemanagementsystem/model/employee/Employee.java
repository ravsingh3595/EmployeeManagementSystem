package com.androidproject.employeemanagementsystem.model.employee;

import android.os.Parcel;
import android.os.Parcelable;

import com.androidproject.employeemanagementsystem.model.vehicle.Vehicle;

public class Employee implements Parcelable{

    private String name;
    private int age;
    private Vehicle vehicle;
    private Employee employee;

    public Employee() { }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected Employee(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCalBirthYear(int age)
    {
        this.age = age;
    }

    public int getCalBirthYear()
    {
        return (2018-age);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



    public float calEarnings(){
        return  0.0F;
    }

    public void printMyData()
    {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}

