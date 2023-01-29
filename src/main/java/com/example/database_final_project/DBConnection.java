package com.example.database_final_project;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/insurance";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Comp333DP$";

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
}

