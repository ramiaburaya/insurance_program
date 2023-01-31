package com.example.database_final_project;

import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.Arrays;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Comp333DP$";
    public static Connection conn;
    public static PreparedStatement stmt;

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

        stmt = conn.prepareStatement("select * from employee where emp_id=? and password =?");
        stmt.setString(1, enteredUsername);
        stmt.setString(2, enteredPassword);

        return stmt.executeQuery();

    }

    public static ResultSet searchClient(String idNumber) throws SQLException {
        conn = DBConnection.getConnection();

        stmt = conn.prepareStatement("select * from client where ssn=?");
        stmt.setString(1, idNumber);

        return stmt.executeQuery();
    }

    public static void insertClient(String idNumber, String firstName, String secondName, String thirdName, String lastName, DatePicker dateOfBirth, String phoneOne, String phoneTwo, byte[] idImage, byte[] drivingImage, int numberOfPhone) throws SQLException {
        conn = DBConnection.getConnection();

        stmt = conn.prepareStatement("insert into client (ssn,first_name, second_name, third_name, fourth_name,dob,phone_1,phone_2,ssn_image,driving_license) values (?,?,?,?,?,?,?,?,?,?)");

        stmt.setString(1, idNumber);
        stmt.setString(2, firstName);
        stmt.setString(3, secondName);
        stmt.setString(4, thirdName);
        stmt.setString(5, lastName);
        stmt.setString(6, String.valueOf(Util.formatterDate(dateOfBirth)));
        stmt.setString(7, phoneOne);
        if (numberOfPhone == 1) {
            stmt.setString(8, "0");
        } else {
            stmt.setString(8, phoneTwo);

        }
        stmt.setString(9, Arrays.toString(idImage));
        stmt.setString(10, Arrays.toString(drivingImage));
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public static void DeleteClientInsurance() throws SQLException {

    }

    public static ResultSet searchClientInsurance(String clientId, String insuranceId) throws SQLException {
        conn = DBConnection.getConnection();


        stmt = conn.prepareStatement("select C.first_name,C.ssn,I.car_id,R.model,I.insutance_type from " +
                                     "client C,insurance I,car R where I.client_id= C.ssn and R.client_id= C.ssn " +
                                     "and C.ssn=? and I.ins_id=?");
        stmt.setString(1, clientId);
        stmt.setString(2, insuranceId);

        return stmt.executeQuery();

    }
}

