package com.androidproject.employeemanagementsystem;

import com.androidproject.employeemanagementsystem.model.employee.Employee;

import java.util.ArrayList;

public class SingletonClass {
    private static SingletonClass myObj;
    /**
     * Create private constructor
     */

    ArrayList<Employee> arrayListEmployee = new ArrayList<>();

    private SingletonClass() {
    }

    /**
     * Create a static method to get instance.
     *
     * @return
     */
    public static SingletonClass getInstance() {
        if (myObj == null) {
            myObj = new SingletonClass();
        }
        return myObj;
    }

    public void addEmployee(Employee e) {
        arrayListEmployee.add(e);
    }

    public Employee getEmployeeByIndex(int index) {
        if (index < arrayListEmployee.size()) {
            return arrayListEmployee.get(index);
        }
        return null;
    }

    public ArrayList<Employee> getEmployees() {
        if (arrayListEmployee.size() > 0) {
            return arrayListEmployee;
        }
        return null;
    }
}
