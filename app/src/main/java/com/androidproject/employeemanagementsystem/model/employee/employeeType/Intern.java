package com.androidproject.employeemanagementsystem.model.employee.employeeType;

import com.androidproject.employeemanagementsystem.model.employee.Employee;

import java.io.Serializable;

public class Intern extends Employee implements Serializable{

    private String schoolName;

    public Intern()
    {
        super();
    }

    public Intern(String name, int age, String schoolName) {
        super(name, age);
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public float calEarnings()
    {
        return 1000;
    }

    @Override
    public void printMyData()
    {
        System.out.println("Name:           " + getName());
        System.out.println("Year of Birth:  " + getCalBirthYear());
        System.out.println("Employee is Intern");
        System.out.println("\tSchoolName:     " + getSchoolName());
        System.out.println("\tEarnings:       " + "C$"+calEarnings());
    }
}
