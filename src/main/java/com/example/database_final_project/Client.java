package com.example.database_final_project;

public class Client extends Person {

    public Client(int ssn, String firstName, String secondName, String thirdName, String fourthName, int phone_1, int phone_2, String date) {
        super(ssn, firstName, secondName, thirdName, fourthName
                , phone_1, phone_2, date);
    }

    public Client() {

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
