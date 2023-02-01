package com.example.database_final_project;

import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Comp333DP$";
    public static Connection conn;
    public static PreparedStatement preparedStatement;
    public static Statement statement;


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet LoginQuery(String enteredUsername, String enteredPassword) throws SQLException {
        /*prepareStatement to avoid sql injection*/
        conn = DBConnection.getConnection();

        preparedStatement = conn.prepareStatement("select * from employee where emp_id=? and password =?");
        preparedStatement.setString(1, enteredUsername);
        preparedStatement.setString(2, enteredPassword);

        return preparedStatement.executeQuery();

    }

    public static ResultSet searchClient(String idNumber) throws SQLException {
        conn = DBConnection.getConnection();

        preparedStatement = conn.prepareStatement("select * from client where ssn=?");
        preparedStatement.setString(1, idNumber);

        return preparedStatement.executeQuery();
    }

    public static void insertClient(String idNumber, String firstName, String secondName, String thirdName, String lastName, DatePicker dateOfBirth, String phoneOne, String phoneTwo, byte[] idImage, byte[] drivingImage, int numberOfPhone) throws SQLException {
        conn = DBConnection.getConnection();

        preparedStatement = conn.prepareStatement("insert into client (ssn,first_name, second_name, third_name, fourth_name,dob,phone_1,phone_2,ssn_image,driving_license) values (?,?,?,?,?,?,?,?,?,?)");

        preparedStatement.setString(1, idNumber);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, secondName);
        preparedStatement.setString(4, thirdName);
        preparedStatement.setString(5, lastName);
        preparedStatement.setString(6, String.valueOf(Util.formatterDate(dateOfBirth)));
        preparedStatement.setString(7, phoneOne);
        if (numberOfPhone == 1) {
            preparedStatement.setString(8, "0");
        } else {
            preparedStatement.setString(8, phoneTwo);

        }
        preparedStatement.setString(9, Arrays.toString(idImage));
        preparedStatement.setString(10, Arrays.toString(drivingImage));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
    }

    public static void DeleteClientInsurance() throws SQLException {

    }

    public static Record searchClientInsurance(String clientId, String insuranceId) throws SQLException {
        conn = DBConnection.getConnection();


        preparedStatement = conn.prepareStatement("select C.first_name as 'Name',C.ssn as 'Client ID' ,I.car_id as 'Car ID',R.model as 'Car Model'," +
                "I.ins_id as 'Insurance ID' ,I.insutance_type as 'Insurance Type'" +
                "from client C,insurance as I,car  R where  I.client_id= C.ssn and R.client_id= C.ssn and C.ssn=? and I.ins_id=?");

        preparedStatement.setString(1, clientId);
        preparedStatement.setString(2, insuranceId);
        ResultSet s = preparedStatement.executeQuery();

        Record record = new Record();

        if (s.next()) {
            // Create a new list for each row of results

            record.clientName = s.getString(1);
            record.clientId = s.getString(2);
            record.carID = s.getString(3);
            record.carModel = s.getString(4);
            record.insuranceId = s.getString(5);
            if (s.getString(6).equals("A")) {
                record.insuranceType = "Shamel";
            } else {
                record.insuranceType = "Mandatory";
            }

            // Add the row to the data list
        }
        return record;

    }

    public static ArrayList<Record> searchClientToDelete(String clientId) throws SQLException {
        conn = DBConnection.getConnection();

        ArrayList<Record> data = new ArrayList<>();
        preparedStatement = conn.prepareStatement("Select  C.first_name as 'Name',C.ssn as 'Client ID' ,I.car_id as 'Car ID',R.model as 'Car Model'," + "I.ins_id as 'Insurance ID' ,I.insutance_type as 'Insurance Type'" +
                "from client C,insurance I,car R  where C.ssn=? and I.client_id=C.ssn and R.client_id= C.ssn and R.car_id=I.car_id");
        preparedStatement.setString(1, clientId);
        ResultSet s = preparedStatement.executeQuery();

        while (s.next()) {
            // Create a new list for each row of results
            Record record = new Record();

            record.clientName = s.getString(1);
            record.clientId = s.getString(2);
            record.carID = s.getString(3);
            record.carModel = s.getString(4);
            record.insuranceId = s.getString(5);
            if (s.getString(6).equals("A")) {
                record.insuranceType = "Shamel";
            } else {
                record.insuranceType = "Mandatory";
            }
            data.add(record);
        }

        return data;

    }


    public static void deleteInsurance(String clientId, String insuranceId, String carId, int numberOfRecords) throws SQLException {
        conn = DBConnection.getConnection();
        if (numberOfRecords == 1) {
            statement = conn.createStatement();
            statement.executeUpdate("delete from client where ssn=" + clientId);
        } else if (numberOfRecords > 1) {
            //TODO delete statement when the client have more than one car
        }
    }
}

