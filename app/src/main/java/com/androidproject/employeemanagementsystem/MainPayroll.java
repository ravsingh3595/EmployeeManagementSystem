package com.androidproject.employeemanagementsystem;



import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.FullTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.Intern;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.FixedBasedPartTime;
import com.androidproject.employeemanagementsystem.model.vehicle.Car;
import com.androidproject.employeemanagementsystem.model.vehicle.Motorcycle;
import com.androidproject.employeemanagementsystem.model.vehicle.Vehicle;
import com.androidproject.employeemanagementsystem.util.pdf.GenerateEmployeeDetailsPDF;
import com.itextpdf.text.DocumentException;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MainPayroll {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    //    static int count = 0;
    static float a = 0;
    static int selectedOption;


    public int employeeType(int numberOfRecods) throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Please choose one option for employee");
        System.out.println("\n Enter 1: For Part time / Commission based employee");
        System.out.println("\n Enter 2: For Part time / Fixed based employee");
        System.out.println("\n Enter 3: For Intern");
        System.out.println("\n Enter 4: For full time employee");

        selectedOption = 0;
        do {

            try {
                selectedOption = Integer.parseInt(br.readLine()); // pass user info
                if (selectedOption >= 1 && selectedOption <= 4) {
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println(e);
            }

            System.out.print("Input must be a number between 1 and 4: ");
        } while (true);
        return selectedOption;
    }
    /* main method */
    public static void main(String[] args) throws IOException, FileNotFoundException, DocumentException {
        String name;
        int age = 0;
        String department;
        float rate;
        int hoursWorked;
        float salary;
        float bonus;
        String schoolName;
        String internshipProgram;
        int internshipPeriod;
        int commisionPer;
        float fixedAmount;
        int manufacturingYear;

        Employee employee;
        Vehicle vehicle;

        int numberOfRecods = 0;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        // check a number
        while (true) {
            try {
                System.out.println("How many employee records you want to enter?");
                numberOfRecods = Integer.parseInt(br.readLine());
                break;
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("Please enter a number only: ");
            }
        }

        SingletonClass singaltonObj = SingletonClass.getInstance();
        for (int i = 0; i < numberOfRecods; i++) {

            //int selectedOption = new MainPayroll().employeeType(numberOfRecods);T

            System.out.println("Enter employee name: ");
            name = br.readLine();

            System.out.println("Enter employee age: ");
            try {
                age = Integer.parseInt(br.readLine());
            } catch (ArithmeticException ae) {
                System.out.println(ae);
            }

            System.out.println("Enter department: ");
            department = br.readLine();

            // get vehical information
            System.out.println("Do you have vehicle? (y/n)");
            char c;

            do {
                try {
                    c = (char) br.readLine().charAt(0);
                    if (c == 'y' || c == 'n') {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e);
                }

                System.out.print("Invalid Input. Please enter y/n only: ");
            } while (true);

            if (c == 'y') {
                System.out.println("Do you have Car or Motorcycle?");
                System.out.println("Please \n select 1: For Car \n select 2: For motorcycle");
                int vehicleSelection = 0;

                do {
                    try {
                        vehicleSelection = Integer.parseInt(br.readLine());
                        if (vehicleSelection == 1 || vehicleSelection == 2) {
                            break;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println(e);
                    }

                    System.out.print("Input must be a number between 1 and 2: ");
                } while (true);



            /* ask user employee type
            1: Commission based employee
            2: Fixed/Part time employee
            3: Intern
            1: Full time employee
             */

                System.out.println("selected option:" + Integer.toString(selectedOption));
                switch (selectedOption) {
                    case 1: // commission
                        System.out.println("Enter commission percentage: ");
                        commisionPer = Integer.parseInt(br.readLine());
                        System.out.println("Enter hours worked: ");
                        hoursWorked = Integer.parseInt(br.readLine());
                        System.out.println("Enter rate: ");
                        rate = Float.parseFloat(br.readLine());
                        employee = new CommissionBasedPartTime(name, age, rate, hoursWorked, commisionPer);
                        break;

                    case 2: // fixed
                        System.out.println("Enter fixedAmount: ");
                        fixedAmount = Integer.parseInt(br.readLine());
                        System.out.println("Enter hours worked: ");
                        hoursWorked = Integer.parseInt(br.readLine());
                        System.out.println("Enter rate ");
                        rate = Float.parseFloat(br.readLine());
                        //String name, int age, float rate, float hoursWorked, float fixedAmount)
                        employee = new FixedBasedPartTime(name, age, rate, hoursWorked, fixedAmount);
                        break;

                    case 3: // intern
                        System.out.println("Enter school name: ");
                        schoolName = br.readLine();
                        System.out.println("Enter internship program: ");
                        internshipProgram = br.readLine();
                        System.out.println("Enter intership period: ");
                        internshipPeriod = Integer.parseInt(br.readLine());
                        employee = new Intern(name, age, schoolName);
                        break;

                    case 4: // full time
                        System.out.println("Enter salary: ");
                        salary = Float.parseFloat(br.readLine());
                        System.out.println("Enter bonus: ");
                        bonus = Float.parseFloat(br.readLine());
                        //String name, int age, float salary, float bonus
                        employee = new FullTime(name, age, salary, bonus);
                        break;

                    default:
                        employee = null;
                        break;

                }

                singaltonObj.addEmployee(employee);
            }//end of for loop

        }
    }
}

//    public static void setCarDetails(Employee emp) throws IOException{
//        System.out.println("You have a Car");
//
//        System.out.println("Enter the Brand of the car you drive ");
//        String sBrandC = br.readLine();                                                //sSalaryC = String of Brand for car
//        System.out.println("What is your Car's Plate Number? ");
//        String sPlateC = br.readLine();                                                //sSalaryC = String of plate for car
//        System.out.println("What is the colour of your car? ");
//        String sColourC = br.readLine();                                               //sColourC = String of colour for car
//        System.out.println("What is the manufacturing year of your car? ");
//        String sYearC = br.readLine();                                                 //sYearC = String of plate for car
//        int iYearC = Integer.parseInt(sYearC);                                         //iYearC = integer of sYearC for car
//        System.out.println("What is the Storage capacity of your car in litres? ");
//        String sCapacityC = br.readLine();                                             //sCapacityC = String of Capacity for car
//        float fCapacityC = Float.parseFloat(sCapacityC);                               //fCapacityC = float of sCapacityC for car
//        System.out.println("What is the seat count of your car? ");
//        String sSeatC = br.readLine();                                                 //sSeatC = String of Seat Count for car
//        int iSeatC = Integer.parseInt(sSeatC);                                         //iSeatC = integer of sSeatC for car
//        emp.getVehicle().setCompany(sBrandC);
//        emp.getVehicle().setPlate(sPlateC);
//        emp.getVehicle().setColour(sColourC);
//        emp.getVehicle().setYear(iYearC);
//        ((Car) emp.getVehicle()).setStorageCapacity(fCapacityC);
//        ((Car) emp.getVehicle()).setSeatCount(iSeatC);
//    }
//
//    public static void setMotorcycleDetails(Employee emp) throws  IOException{
//        System.out.println("You have a MotorCycle");
//
//        System.out.println("Enter the Brand of the MotorCycle you drive ");
//        String sBrandM = br.readLine();                                               //sSalaryM = String of Brand for motorcycle
//        System.out.println("What is your MotorCycle's Plate Number? ");
//        String sPlateM = br.readLine();                                               //sSalaryM = String of plate for motorcycle
//        System.out.println("What is the colour of your MotorCycle? ");
//        String sColourM = br.readLine();                                              //sColourM = String of colour for motorcycle
//        System.out.println("What is the manufacturing year of your MotorCycle? ");
//        String sYearM = br.readLine();                                                //sYearM = String of plate for motorcycle
//        int iYearM = Integer.parseInt(sYearM);                                        //iYearM = integer of sYear for motorcycle
//        System.out.println("What is the CC MotorCycle you drive?");
//        String sPowerM = br.readLine();                                              //sPowerM = String of Horse-Power for motorcycle
//        float fPowerM = Float.parseFloat(sPowerM);                                   //fPowerM = float of sPowerM for motorcycle
//        System.out.println("What is the Top-speed of your MotorCycle in Km/hr? ");
//        String sSpeedM = br.readLine();                                              //sSpeedM = String of Top-Speed for motorcycle
//        float fSpeedM = Float.parseFloat(sSpeedM);
//
//        emp.getVehicle().setCompany(sBrandM);
//        emp.getVehicle().setPlate(sPlateM);
//        emp.getVehicle().setColour(sColourM);
//        emp.getVehicle().setYear(iYearM);
//        ((Motorcycle) emp.getVehicle()).setEnginePower(fPowerM);
//        ((Motorcycle) emp.getVehicle()).setTopSpeed(fSpeedM);
//
//
//    }


/*
        for (int i = 0; i < arrayListEmployee.size() ; i++) {
            System.out.println(arrayListEmployee.get(i).printMyData());
        }
import java.util.ArrayList;

    */

