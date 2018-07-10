package com.androidproject.employeemanagementsystem.model.employee.employeeType;

import com.androidproject.employeemanagementsystem.model.employee.Employee;

import java.io.Serializable;

public class FullTime extends Employee implements Serializable {

    private double salary;
    private double bonus;

    public FullTime()
    {
        super();
    }

    public FullTime(String name, int age, double salary, double bonus) {
        super(name, age);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double calEarnings()
    {
        return (getSalary() + getBonus());
    }

    @Override
    public void printMyData()
    {
        System.out.println("Name:           " + getName());
        System.out.println("Year of Birth:  " + getCalBirthYear());
        System.out.println("Employee is FullTime");
        System.out.println("\tSalary:         " +"C$"+ getSalary());
        System.out.println("\tBonus:          " +"C$"+getBonus());
        System.out.println("\tEarnings:       " +"C$"+ calEarnings());
    }
}
