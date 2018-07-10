package com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime;

import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;

import java.io.Serializable;

public class FixedBasedPartTime extends PartTime implements Serializable {

    private double fixedAmount;

    public FixedBasedPartTime()
    {
        super();
    }

    public FixedBasedPartTime(String name, int age, double rate, double hoursWorked, double fixedAmount) {
        super(name, age, rate, hoursWorked);
        this.fixedAmount = fixedAmount;
    }

    public double getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(double fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public double calEarnings()
    {
        return ((getRate()*getHoursWorked()) + getFixedAmount() + (getRate()*getHoursWorked()));
    }

    @Override
    public void printMyData()
    {
        System.out.println("Name:           " + getName());
        System.out.println("Year of Birth:  " + getCalBirthYear());
        System.out.println("Employee is PartTime/Fixed ");
        System.out.println("\tRate per hour:       " + getRate());
        System.out.println("\tHours Worked:        " + getHoursWorked());
        System.out.println("\tFixed Amount in CAD: " + getFixedAmount());
        System.out.println("\tEarning in CAD:      " +"C$"+calEarnings());
    }
}

