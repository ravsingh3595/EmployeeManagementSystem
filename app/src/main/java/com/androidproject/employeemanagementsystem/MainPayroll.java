package com.androidproject.employeemanagementsystem;



import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.FullTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.Intern;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.CommissionBasedPartTime;
import com.androidproject.employeemanagementsystem.model.employee.employeeType.partTime.FixedBasedPartTime;
import com.androidproject.employeemanagementsystem.model.vehicle.Car;
import com.androidproject.employeemanagementsystem.model.vehicle.Motorcycle;
import com.androidproject.employeemanagementsystem.util.pdf.GenerateEmployeeDetailsPDF;

import java.io.*;
import java.util.ArrayList;

public class MainPayroll {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    //    static int count = 0;
    static float a = 0;


    public static void main(String[] arg) {


        InputStreamReader isr1 = new InputStreamReader(System.in);
        BufferedReader br1 = new BufferedReader(isr1);
        GenerateEmployeeDetailsPDF pdf = new GenerateEmployeeDetailsPDF();
        boolean cont = true;
        String repeat = "no";
        String veh = null;
        String employmentType = null;
        String output = "no";
        System.out.println("Employee Database System");

        int count = 0;
        do{
            count++;

            System.out.print("Employee Name: ");
            try {
                String empName = br1.readLine();
                System.out.print("Age: ");
                String sEmpAge = br1.readLine();                                             //sEmpAge = String of empAge
                int empAge = Integer.parseInt(sEmpAge);

                do {
                    System.out.print("Select your Employment type," +
                            " Choose any option from the following " +
                            "\na. Commission Based PartTime" +
                            "\nb. Fixed Based PartTime" +
                            "\nc. Intern" +
                            "\nd. FullTime\n");

                    employmentType = br.readLine();
                    switch (employmentType) {

                        case "a":
                        case "A":

                            System.out.println("Commission Based PartTime Employee");

                            System.out.println("Enter your hourly pay in CAD:");
                            String sPayC = br1.readLine();                                       //sPayC = String of hourly pay for Commission
                            float fPayC = Float.parseFloat(sPayC);                              //fPayC = float value of sPay for Commission


                            System.out.println("Enter Number of hours worked: ");
                            String sHourWorkedC = br1.readLine();                                //sHourWorkedC = String of hours worked for Commission
                            float fHourWorkedC = Float.parseFloat(sHourWorkedC);                //fHourWorkedC = float value of sHourWorked for Commission

                            System.out.println("Enter the Commission: ");
                            String sCommission = br1.readLine();                                //sCommission = String of Commission
                            float fCommission = Float.parseFloat(sCommission);                 //fCommission = float value of Commission

                            CommissionBasedPartTime empCom = new CommissionBasedPartTime();

                            System.out.println("What type of Vehicle you drive," +
                                    " Choose any option from the following " +
                                    "\na. Car" +
                                    "\nb. Motorcycle" +
                                    "\nc. No Vehicle\n");

                            veh = br1.readLine();

                            if (veh.equals("a") || veh.equals("A")) {
                                empCom.setVehicle(new Car());
                                MainPayroll.setCarDetails(empCom);
                            }
                            if(veh.equals("b") || veh.equals("B")) {
                                empCom.setVehicle(new Motorcycle());
                                MainPayroll.setMotorcycleDetails(empCom);
                            }
                            else if(veh.equals("c") || veh.equals("C"))
                            {
                                System.out.println("Employee has no vehicle");
                            }

                            //Assigning values
                            empCom.setName(empName);
                            empCom.setAge(empAge);
                            empCom.setRate(fPayC);
                            empCom.setHoursWorked(fHourWorkedC);
                            empCom.setCommissionPercentage(fCommission);

                            SingletonClass.getInstance().arrayListEmployee.add(empCom);
                            pdf.generateEmployeeDetailsPDF(empCom);

                            cont = false;
                            break;

                        case "b":
                        case "B":

                            System.out.println("Fixed Based PartTime Employee");
                            FixedBasedPartTime empFix = new FixedBasedPartTime();

                            System.out.println("Enter your hourly pay in CAD:");
                            String sPayF = br.readLine();                                    //sPayF = String of hourly pay for Fixed Based
                            float fPayF = Float.parseFloat(sPayF);                           //fPayF = float value of sPay for Fixed based

                            System.out.println("Enter Number of hours worked: ");
                            String sHourWorkedF = br.readLine();                             //sHourWorkedF = String of hours worked for Fixed Based
                            float fHourWorkedF = Float.parseFloat(sHourWorkedF);             //fHourWorkedF = float value of sHourWorked for Fixed Based

                            System.out.println("Enter your Fixed pay in CAD: ");
                            String sFixedPay = br.readLine();                                //sFixedPay = String of Fixed Based
                            float fFixedPay = Float.parseFloat(sFixedPay);                   //fFixedPay = float value of Fixed Based

                            System.out.println("What type of Vehicle you drive," +
                                    " Choose any option from the following " +
                                    "\na. Car" +
                                    "\nb. Motorcycle" +
                                    "\nc. No Vehicle\n");

                            veh = br1.readLine();

                            if (veh.equals("a") || veh.equals("A")) {
                                empFix.setVehicle(new Car());
                                MainPayroll.setCarDetails(empFix);
                            }
                            if(veh.equals("b") || veh.equals("B")) {
                                empFix.setVehicle(new Motorcycle());
                                MainPayroll.setMotorcycleDetails(empFix);
                            }
                            else if(veh.equals("c") || veh.equals("C"))
                            {
                                System.out.println("Employee has no vehicle");
                            }

                            //Assigning values
                            empFix.setName(empName);
                            empFix.setAge(empAge);
                            empFix.setRate(fPayF);
                            empFix.setHoursWorked(fHourWorkedF);
                            empFix.setFixedAmount(fFixedPay);
                            SingletonClass.getInstance().arrayListEmployee.add(empFix);
                            pdf.generateEmployeeDetailsPDF(empFix);

                            cont = false;
                            break;

                        case "c":
                        case "C":

                            System.out.println("Intern");
                            Intern empIntern = new Intern();

                            System.out.println("Enter your School Name: ");
                            String sSchoolName = br.readLine();                              //sSchoolName = String of School Name

                            //Assigning values

                            empIntern.setName(empName);
                            empIntern.setAge(empAge);
                            empIntern.setSchoolName(sSchoolName);

                            System.out.println("What type of Vehicle you drive," +
                                    " Choose any option from the following " +
                                    "\na. Car" +
                                    "\nb. Motorcycle" +
                                    "\nc. No Vehicle\n");
                            veh = br1.readLine();

                            if (veh.equals("a") || veh.equals("A")) {
                                empIntern.setVehicle(new Car());
                                MainPayroll.setCarDetails(empIntern);
                            }
                            if(veh.equals("b") || veh.equals("B")) {
                                empIntern.setVehicle(new Motorcycle());
                                MainPayroll.setMotorcycleDetails(empIntern);
                            }
                            else if(veh.equals("c") || veh.equals("C"))
                            {
                                System.out.println("Employee has no vehicle");
                            }

                            //Assigning values

                            empIntern.setName(empName);
                            empIntern.setAge(empAge);
                            empIntern.setSchoolName(sSchoolName);
                            SingletonClass.getInstance().arrayListEmployee.add(empIntern);
                            pdf.generateEmployeeDetailsPDF(empIntern);

                            cont = false;
                            break;

                        case "d":
                        case "D":

                            System.out.println("FullTime Employee");
                            FullTime empFullTime = new FullTime();

                            System.out.println("Enter your Salary in CAD per week: ");
                            String sSalary = br.readLine();                                 //sSalary = String of Salary
                            float fSalary = Float.parseFloat(sSalary);                      //fSalary = float value of sSalary

                            System.out.println("Enter the Bonus you got in CAD:");
                            String sBonus = br.readLine();                                  //sBonus = String of Bonus
                            float fBonus = Float.parseFloat(sBonus);                        //fBonus = float value of sBonus

                            System.out.println("What type of Vehicle you drive," +
                                    " Choose any option from the following " +
                                    "\na. Car" +
                                    "\nb. Motorcycle" +
                                    "\nc. No Vehicle\n");
                            veh = br1.readLine();

                            if (veh.equals("a") || veh.equals("A")) {
                                empFullTime.setVehicle(new Car());
                                MainPayroll.setCarDetails(empFullTime);
                            }
                            if(veh.equals("b") || veh.equals("B")) {
                                empFullTime.setVehicle(new Motorcycle());
                                MainPayroll.setMotorcycleDetails(empFullTime);
                            }
                            else if(veh.equals("c") || veh.equals("C"))
                            {
                                System.out.println("Employee has no vehicle");
                            }

                            //Assigning values
                            empFullTime.setName(empName);
                            empFullTime.setAge(empAge);
                            empFullTime.setSalary(fSalary);
                            empFullTime.setBonus(fBonus);
                            SingletonClass.getInstance().arrayListEmployee.add(empFullTime);
                            pdf.generateEmployeeDetailsPDF(empFullTime);

                            cont = false;
                            break;

                        default:
                            System.out.println("Incorrect Input");
                            cont = true;
                            break;
                    }
                }while (cont);


                System.out.println("Do you want to add more data? yes/no");
                repeat = br1.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            a = a+ SingletonClass.getInstance().getEmployeeByIndex(count-1).calEarnings();
        }
        while(repeat.equals("yes") || repeat.equals("Yes") || repeat.equals("y") || repeat.equals("Y"));

        System.out.println("Want to print the details of last updated employee? yes/no");
        try {
            output = br1.readLine();

            if (output.equals("yes") || output.equals("Yes") || output.equals("YES") || output.equals("y"))
            {
                Employee e = SingletonClass.getInstance().getEmployeeByIndex(count-1);
                e.printMyData();
                if (veh.equals("a") || veh.equals("A") || veh.equals("b") || veh.equals("B"))
                {
                    e.getVehicle().printMyData();
                }else if (veh.equals("c") || veh.equals("C"))
                {
                    System.out.println("Employee has no Vehicle");
                }


//                System.out.println("Total Earning of all employees: " + a );

            } else {
                System.out.println("Thank You! Bye");

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        pdf.setBackToTopLink(a);

    }

    public static void setCarDetails(Employee emp) throws IOException{
        System.out.println("You have a Car");

        System.out.println("Enter the Brand of the car you drive ");
        String sBrandC = br.readLine();                                                //sSalaryC = String of Brand for car
        System.out.println("What is your Car's Plate Number? ");
        String sPlateC = br.readLine();                                                //sSalaryC = String of plate for car
        System.out.println("What is the colour of your car? ");
        String sColourC = br.readLine();                                               //sColourC = String of colour for car
        System.out.println("What is the manufacturing year of your car? ");
        String sYearC = br.readLine();                                                 //sYearC = String of plate for car
        int iYearC = Integer.parseInt(sYearC);                                         //iYearC = integer of sYearC for car
        System.out.println("What is the Storage capacity of your car in litres? ");
        String sCapacityC = br.readLine();                                             //sCapacityC = String of Capacity for car
        float fCapacityC = Float.parseFloat(sCapacityC);                               //fCapacityC = float of sCapacityC for car
        System.out.println("What is the seat count of your car? ");
        String sSeatC = br.readLine();                                                 //sSeatC = String of Seat Count for car
        int iSeatC = Integer.parseInt(sSeatC);                                         //iSeatC = integer of sSeatC for car
        emp.getVehicle().setCompany(sBrandC);
        emp.getVehicle().setPlate(sPlateC);
        emp.getVehicle().setColour(sColourC);
        emp.getVehicle().setYear(iYearC);
        ((Car) emp.getVehicle()).setStorageCapacity(fCapacityC);
        ((Car) emp.getVehicle()).setSeatCount(iSeatC);
    }

    public static void setMotorcycleDetails(Employee emp) throws  IOException{
        System.out.println("You have a MotorCycle");

        System.out.println("Enter the Brand of the MotorCycle you drive ");
        String sBrandM = br.readLine();                                               //sSalaryM = String of Brand for motorcycle
        System.out.println("What is your MotorCycle's Plate Number? ");
        String sPlateM = br.readLine();                                               //sSalaryM = String of plate for motorcycle
        System.out.println("What is the colour of your MotorCycle? ");
        String sColourM = br.readLine();                                              //sColourM = String of colour for motorcycle
        System.out.println("What is the manufacturing year of your MotorCycle? ");
        String sYearM = br.readLine();                                                //sYearM = String of plate for motorcycle
        int iYearM = Integer.parseInt(sYearM);                                        //iYearM = integer of sYear for motorcycle
        System.out.println("What is the CC MotorCycle you drive?");
        String sPowerM = br.readLine();                                              //sPowerM = String of Horse-Power for motorcycle
        float fPowerM = Float.parseFloat(sPowerM);                                   //fPowerM = float of sPowerM for motorcycle
        System.out.println("What is the Top-speed of your MotorCycle in Km/hr? ");
        String sSpeedM = br.readLine();                                              //sSpeedM = String of Top-Speed for motorcycle
        float fSpeedM = Float.parseFloat(sSpeedM);

        emp.getVehicle().setCompany(sBrandM);
        emp.getVehicle().setPlate(sPlateM);
        emp.getVehicle().setColour(sColourM);
        emp.getVehicle().setYear(iYearM);
        ((Motorcycle) emp.getVehicle()).setEnginePower(fPowerM);
        ((Motorcycle) emp.getVehicle()).setTopSpeed(fSpeedM);


    }

}
/*
        for (int i = 0; i < arrayListEmployee.size() ; i++) {
            System.out.println(arrayListEmployee.get(i).printMyData());
        }
import java.util.ArrayList;

    */

