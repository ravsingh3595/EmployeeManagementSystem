package com.androidproject.employeemanagementsystem.model.employee.employeeType;

import com.androidproject.employeemanagementsystem.model.employee.Employee;

import java.io.Serializable;

public abstract class PartTime extends Employee implements Serializable {

    private double rate;
    private double hoursWorked;

    public PartTime()
    {
        super();
    }

    public PartTime(String name, int age, double rate, double hoursWorked) {
        super(name, age);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public abstract double calEarnings();

    @Override
    public void printMyData()
    {

    }
}

