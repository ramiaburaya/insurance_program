/*Rami*/

package com.example.database_final_project;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        Label usernameLabel = Util.createLabel("Username", 212, 139, 82, 27);
        Label passwordLabel = Util.createLabel("Password", 212, 208, 82, 27);

        TextField usernameText = Util.createTextField("Username", 315, 140, 149, 25);

        PasswordField passwordText = new PasswordField();
        passwordText.setPadding(new Insets(5));
        passwordText.setPrefSize(149, 25);
        passwordText.setLayoutX(315);
        passwordText.setLayoutY(209);
        passwordText.setPromptText("Password");

        Button login = Util.createButton("Login", 269, 274, 62, 27);
        login.setFont(new Font(14));
        login.setOnAction(event -> {
            String enteredUsername = usernameText.getText().trim();
            String enteredPassword = passwordText.getText();
            try {

                ResultSet resultSet = DBConnection.LoginQuery(enteredUsername, enteredPassword);

                if (resultSet.next()) {
                    emp = new Employee(resultSet.getString("emp_id"), Integer.parseInt(resultSet.getString("ssn")), resultSet.getString("first_name"), resultSet.getString("second_name"), resultSet.getString("third_name"), resultSet.getString("fourth_name"), Integer.parseInt(resultSet.getString("phone_1")), Integer.parseInt(resultSet.getString("phone_2")), resultSet.getString("dob"), resultSet.getString("password"));
                    MenuPage.PrintMenuPage();
                    loginPageStage.close();
                } else {
                    Util.alert.setAlertType(Alert.AlertType.ERROR);
                    Util.alert.setTitle("Error");
                    Util.alert.setContentText("Password or username is incorrect");
                    Util.alert.show();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                DBConnection.preparedStatement.close();
                DBConnection.conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        });
        rootPane.getChildren().addAll(icon, usernameLabel, usernameText, passwordLabel, passwordText, login);

        Scene scene = new Scene(rootPane, 600, 400);
        scene.setFill(Color.web("#491e54"));

        scene.getStylesheets().add(Util.style);
        loginPageStage.setTitle(Util.name);
        loginPageStage.setScene(scene);
        loginPageStage.getIcons().add(Util.icon);
        loginPageStage.setResizable(false);
        loginPageStage.show();
    }

}

