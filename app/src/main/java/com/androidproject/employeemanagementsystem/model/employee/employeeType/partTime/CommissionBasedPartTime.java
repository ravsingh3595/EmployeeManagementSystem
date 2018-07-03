package com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime;

import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.PartTime;

public class CommissionBasedPartTime extends PartTime {

    private float commissionPercentage;

    public CommissionBasedPartTime()
    {
        super();
    }

    public CommissionBasedPartTime(String name, int age, float rate, float hoursWorked, float commissionPercentage) {
        super(name, age, rate, hoursWorked);
        this.commissionPercentage = commissionPercentage;
    }

    public float getCommissionPercentage() {
        return commissionPercentage;
    }

    public void setCommissionPercentage(float commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
    }

    public float calEarnings(){
        return ((getRate()*getHoursWorked()) + ((commissionPercentage/100) *(getRate()*getHoursWorked())));
    }
    @Override
    public void printMyData()
    {
        System.out.println("Name:           " + getName());
        System.out.println("Year of Birth:  " + getCalBirthYear());
        System.out.println("Employee is PartTime/Commissioned ");
        System.out.println("\tRate per hour:  " + getRate());
        System.out.println("\tHours Worked:   " + getHoursWorked());
        System.out.println("\tCommission:     " + getCommissionPercentage() +"%");
        System.out.println("\tEarning in CAD: " + "C$" +calEarnings());

    }

}

