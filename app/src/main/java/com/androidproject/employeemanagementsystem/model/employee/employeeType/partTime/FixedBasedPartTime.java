package com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime;

import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;

import java.io.Serializable;

public class FixedBasedPartTime extends PartTime implements Serializable {

    private float fixedAmount;

    public FixedBasedPartTime()
    {
        super();
    }

    public FixedBasedPartTime(String name, int age, float rate, float hoursWorked, float fixedAmount) {
        super(name, age, rate, hoursWorked);
        this.fixedAmount = fixedAmount;
    }

    public float getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(float fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public float calEarnings()
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

