package com.example.database_final_project;

public class Employee {
    public Employee() {

    }

    private String empID;
    private int ssn;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String fourthName;
    private int phone_1;
    private String date;
    private String password;

    public Employee(String empID, int ssn, String firstName, String secondName, String thirdName, String fourthName, int phone_1, String date, String password) {
        this.empID = empID;
        this.ssn = ssn;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.fourthName = fourthName;
        this.phone_1 = phone_1;
        this.date = date;
        this.password = password;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getFourthName() {
        return fourthName;
    }

    public void setFourthName(String fourthName) {
        this.fourthName = fourthName;
    }

    public int getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(int phone_1) {
        this.phone_1 = phone_1;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", fourthName='" + fourthName + '\'' +
                ", phone_1=" + phone_1 +
                ", date='" + date + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
