/*Rami*/
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

    // login page
    public static ResultSet LoginQuery(String enteredUsername, String enteredPassword) throws SQLException {
        /*prepareStatement to avoid sql injection*/
        conn = DBConnection.getConnection();

        preparedStatement = conn.prepareStatement("select * from employee where emp_id=? and password =?");
        preparedStatement.setString(1, enteredUsername);
        preparedStatement.setString(2, enteredPassword);

        return preparedStatement.executeQuery();

    }

    //Create insurance Page
    public static ResultSet searchClient(String idNumber) throws SQLException {
        conn = DBConnection.getConnection();

        preparedStatement = conn.prepareStatement("select * from client where ssn=?");
        preparedStatement.setString(1, idNumber);

        return preparedStatement.executeQuery();
    }

    //Create insurance Page
    public static ResultSet insertClient(String idNumber, String firstName, String secondName, String thirdName, String lastName, DatePicker dateOfBirth, String phoneOne, String phoneTwo, byte[] idImage, byte[] drivingImage, int numberOfPhone) throws SQLException {
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
        return searchClient(idNumber);
    }

    // Delete Page
    public static Record searchClientInsurance(String clientId, String insuranceId) throws SQLException {
        conn = DBConnection.getConnection();

        String sql = """
                Select client.first_name , client.ssn , insurance.car_id , car.model, insurance.ins_id,
                insurance.insutance_type, insurance.start_date, insurance.end_date from client, insurance, car
                 where insurance.client_id = client.ssn and car.client_id= client.ssn and client.ssn=? and insurance.ins_id=?""";

        preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, clientId);
        preparedStatement.setString(2, insuranceId);
        ResultSet s = preparedStatement.executeQuery();

        Record record = new Record();

        if (s.next()) {

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
            record.startDate = s.getString(7);
            record.endDate = s.getString(8);

        }
        return record;

    }

    // Delete Page
    public static ArrayList<Record> searchClientToDelete(String clientId) throws SQLException {
        conn = DBConnection.getConnection();

        ArrayList<Record> data = new ArrayList<>();
        String sql = """
                Select client.first_name , client.ssn , insurance.car_id , car.model, insurance.ins_id,
                insurance.insutance_type, insurance.start_date, insurance.end_date from client, insurance, car
                 where client.ssn=? and insurance.client_id = client.ssn and car.client_id= client.ssn and car.car_id=insurance.car_id""";

        preparedStatement = conn.prepareStatement(sql);
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
            record.startDate = s.getString(7);
            record.endDate = s.getString(8);

            data.add(record);
        }

        return data;

    }

    // Delete Page
    public static void deleteInsurance(String clientId, String carId, int numberOfRecords) throws SQLException {
        conn = DBConnection.getConnection();

        if (numberOfRecords == 1) {

            statement = conn.createStatement();
            statement.executeUpdate("delete from client where ssn=" + clientId);
        } else if (numberOfRecords > 1) {
            statement = conn.createStatement();
            statement.executeUpdate("delete from car where car_id='" + carId + "'");
        }
        preparedStatement.close();
        conn.close();
    }

    //create insurance page
    public static ResultSet searchForCar(String carId) throws SQLException {
        conn = DBConnection.getConnection();
        preparedStatement = conn.prepareStatement("select * from car where car_id=?");
        preparedStatement.setString(1, carId);

        return preparedStatement.executeQuery();
    }

    //create insurance page
    public static ResultSet insertCar(String carId, String clientID, String model, String enginSize, byte[] carLicense, DatePicker licenseEnd,
                                      String modelDate, String price, String insurancePrice, String insuranceType, String color) throws SQLException {
        String insertCarSql = "INSERT INTO car (car_id, client_id, model, engin_size, color, car_license, license_end, model_date, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        conn = DBConnection.getConnection();
        preparedStatement = conn.prepareStatement(insertCarSql);
        preparedStatement.setString(1, carId);
        preparedStatement.setString(2, clientID);
        preparedStatement.setString(3, model);
        preparedStatement.setString(4, enginSize);
        preparedStatement.setString(5, color);
        preparedStatement.setString(6, Arrays.toString(carLicense));
        preparedStatement.setString(7, String.valueOf(Util.formatterDate(licenseEnd)));
        preparedStatement.setString(8, modelDate);
        preparedStatement.setString(9, price);
        preparedStatement.executeUpdate();
        insertInsurance(carId, clientID, insurancePrice, insuranceType, String.valueOf(Util.formatterDate(licenseEnd)));
        preparedStatement.close();
        conn.close();
        return searchForCar(carId);
    }

    //create insurance page
    private static void insertInsurance(String carId, String clientID, String insurancePrice, String insuranceType, String licenseEnd) throws SQLException {
        String insertInsuranceSql = "INSERT INTO insurance (ins_id, end_date, insutance_type, car_id, client_id, price) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        conn = DBConnection.getConnection();
        preparedStatement = conn.prepareStatement(insertInsuranceSql);
        preparedStatement.setString(1, Util.generateInsuranceID(clientID, carId));
        preparedStatement.setString(2, licenseEnd);
        if (insuranceType.equals("Shamel")) {
            preparedStatement.setString(3, "A");
        } else {
            preparedStatement.setString(3, "B");
        }
        preparedStatement.setString(4, carId);
        preparedStatement.setString(5, clientID);
        preparedStatement.setString(6, insurancePrice);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        conn.close();
    }
}

