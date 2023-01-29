package com.example.database_final_project;


import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class LoginPage {
    public static Employee emp;

    public static void PrintLoginPage() {
        Stage loginPageStage = new Stage();

        AnchorPane rootPane = new AnchorPane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.web("#BFACE2"), CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.setPadding(new Insets(10));

        ImageView icon = new ImageView();
        icon.setFitHeight(50);
        icon.setFitWidth(155);
        icon.setLayoutX(243);
        icon.setLayoutY(52);
        icon.setStyle("-fx-opacity: 0.7;");

        icon.setImage(new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm()));
        Label usernameLabel = new Label("Username");
        usernameLabel.setPadding(new Insets(5));
        usernameLabel.setPrefSize(82, 27);
        usernameLabel.setLayoutX(212);
        usernameLabel.setLayoutY(139);

        Label passwordLabel = new Label("Password");
        passwordLabel.setPadding(new Insets(5));
        passwordLabel.setPrefSize(82, 27);
        passwordLabel.setLayoutX(212);
        passwordLabel.setLayoutY(208);

        TextField usernameText = new TextField();
        usernameText.setPadding(new Insets(5));
        usernameText.setPrefSize(149, 25);
        usernameText.setLayoutX(315);
        usernameText.setLayoutY(140);


        PasswordField passwordText = new PasswordField();
        passwordText.setPadding(new Insets(5));
        passwordText.setPrefSize(149, 25);
        passwordText.setLayoutX(315);
        passwordText.setLayoutY(209);

        Button login = new Button("Login");
        login.setCursor(Cursor.HAND);
        login.setPadding(new Insets(5));
        login.setPrefSize(62, 27);
        login.setLayoutX(269);
        login.setLayoutY(274);
        login.setFont(new Font(14));
        login.setId("button");
        login.setTextFill(Paint.valueOf("#fffdfd"));
        login.getStyleClass().add("button");

        login.setOnAction(event -> {

            String username = usernameText.getText().trim();
            String password = passwordText.getText();
            try {
                /*prepareStatement to avoid sql injection*/
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from insurance.employee where emp_id=? and password =?");
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    emp = new Employee(resultSet.getString("emp_id"), Integer.parseInt(resultSet.getString("ssn"))
                            , resultSet.getString("first_name"), resultSet.getString("second_name")
                            , resultSet.getString("third_name"), resultSet.getString("fourth_name")
                            , Integer.parseInt(resultSet.getString("phone_1")), resultSet.getString("dob")
                            , resultSet.getString("password"));
                } else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("something Wrong");
                    error.setContentText("Password or username is incorrect");
                    error.show();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        });
        rootPane.getChildren().addAll(icon, usernameLabel, usernameText, passwordLabel, passwordText, login);

        Scene scene = new Scene(rootPane, 600, 400);
        scene.setFill(Color.web("#491e54"));
        scene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("style.css")).toExternalForm());
        loginPageStage.setTitle("Login");
        loginPageStage.setScene(scene);
        loginPageStage.getIcons().add(new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm()));
        loginPageStage.setResizable(false);
        loginPageStage.show();
    }

}

