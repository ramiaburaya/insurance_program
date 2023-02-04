package com.example.database_final_project;

public abstract class Person {
    private int ssn;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String fourthName;
    private int phone_1;
    private int phone_2;

    private String date;

    public Person(int ssn, String firstName, String secondName, String thirdName, String fourthName, int phone_1, int phone_2, String date) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.fourthName = fourthName;
        this.phone_1 = phone_1;
        this.phone_2 = phone_2;
        this.date = date;
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

    public int getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(int phone_2) {
        this.phone_2 = phone_2;
    }

    public Person( ) {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", fourthName='" + fourthName + '\'' +
                ", phone_1=" + phone_1 +
                ", phone_2=" + phone_2 +
                ", date='" + date + '\'' +
                '}';
    }
}
