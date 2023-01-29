package com.example.database_final_project;

import javafx.application.Application;

import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        LoginPage.PrintLoginPage();

    }

    public static void main(String[] args) {
        launch();
    }
}