package com.androidproject.employeemanagementsystem.model.employee;

import com.androidproject.employeemanagementsystem.model.vehicle.Vehicle;

public abstract class Employee {

    private String name;
    private int age;
    private Vehicle vehicle;

    public Employee()
    {

    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    public abstract float calEarnings();
    {

    }

    public void printMyData()
    {

    }

}

