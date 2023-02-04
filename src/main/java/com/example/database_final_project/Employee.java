/*Rami*/

package com.example.database_final_project;


public class Employee extends Person {
    private String empID;
    private String password;

    public Employee(String empID, int ssn, String firstName, String secondName, String thirdName, String fourthName, int phone_1, int phone_2, String date, String password) {
        super(ssn, firstName, secondName, thirdName, fourthName
                , phone_1, phone_2, date);
        this.empID = empID;
        this.password = password;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "empID='" + empID + '\'' +
                ", password='" + password + '\'' +
                "} " + super.toString();
    }
}
