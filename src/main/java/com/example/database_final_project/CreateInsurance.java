package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class CreateInsurance {

    private static Client client;
    private Car car = new Car();

    private static final Alert alert = new Alert(Alert.AlertType.NONE);

    public CreateInsurance() {

    }

    public static AnchorPane ClientPane() {
        AnchorPane rootPane = new AnchorPane();
        rootPane.setLayoutX(27);
        rootPane.setLayoutY(60);
        rootPane.setPrefSize(1300, 483);
        rootPane.getStyleClass().add("pane-2");

        rootPane.getChildren().clear();

        Label idNumberLabel = Util.createLabel("ID number", 485, 28);
        Label firstNameLabel = Util.createLabel("First name", 479, 75);
        Label secondNameLabel = Util.createLabel("Second name", 651, 75);
        Label thirdNameLabel = Util.createLabel("Third name", 479, 151);
        Label lastNameLabel = Util.createLabel("Last name", 651, 151);
        Label dateOfBirthLabel = Util.createLabel("Date of Birth", 479, 222);
        Label phoneOneLabel = Util.createLabel("Phone one", 651, 222);
        Label phoneTwoLabel = Util.createLabel("Phone two", 479, 286);
        Label idImageLabel = Util.createLabel("ID image", 651, 286);
        Label drivingImageLabel = Util.createLabel("Driving image", 485, 364);

        TextField idNumberField = Util.createTextField("ID number", 650, 28);
        TextField firstNameField = Util.createTextField("First name", 479, 102);
        TextField secondNameField = Util.createTextField("Second name", 650, 102);
        TextField thirdNameField = Util.createTextField("Third name", 479, 178);
        TextField lastNameField = Util.createTextField("Last name", 650, 177);
        TextField phoneOneField = Util.createTextField("Phone one", 650, 250);
        TextField phoneTwoField = Util.createTextField("Phone two", 479, 313);

        DatePicker dateOfBirthPicker = new DatePicker();
        dateOfBirthPicker.setPadding(new Insets(5));
        dateOfBirthPicker.setPrefSize(145, 25);
        dateOfBirthPicker.setLayoutX(479);
        dateOfBirthPicker.setLayoutY(250);


        Button idImageButton = Util.createButton("ID image", 650, 312, 145, 25);

        Button drivingImageButton = Util.createButton("Driving image", 650, 364, 145, 25);

        Button addButton = Util.createButton("Add", 0, 0, 75, 25);
        addButton.setDisable(true);
        addButton.setOnAction(addEvent -> {

        });

        Button searchButton = Util.createButton("search", 0, 0, 75, 25);
        searchButton.setOnAction(searchEvent -> {
            if (!idNumberField.getText().equals("")) {
                try {
                    Connection conn = DBConnection.getConnection();
                    PreparedStatement searchStmt = conn.prepareStatement("select * from client where ssn=?");
                    searchStmt.setString(1, idNumberField.getText());

                    ResultSet resultSet = searchStmt.executeQuery();
                    alert.setTitle("Result");
                    if (resultSet.next()) {
                        drivingImageButton.setDisable(true);
                        idImageButton.setDisable(true);

                        client = new Client(Integer.parseInt(resultSet.getString("ssn")), resultSet.getString("first_name"), resultSet.getString("second_name"), resultSet.getString("third_name"), resultSet.getString("fourth_name"), Integer.parseInt(resultSet.getString("phone_1")), Integer.parseInt(resultSet.getString("phone_2")), resultSet.getString("dob"));

                        dateOfBirthPicker.setValue(LocalDate.of(Integer.parseInt(resultSet.getString("dob").split("-")[0]), Integer.parseInt(resultSet.getString("dob").split("-")[1]), Integer.parseInt(resultSet.getString("dob").split("-")[2])));

                        firstNameField.setText(resultSet.getString("first_name"));
                        secondNameField.setText(resultSet.getString("second_name"));
                        thirdNameField.setText(resultSet.getString("third_name"));
                        lastNameField.setText(resultSet.getString("fourth_name"));
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("The user is in the database, you can add a new car");
                        phoneOneField.setText(resultSet.getString("phone_1"));
                        phoneTwoField.setText(resultSet.getString("phone_2"));

                    } else {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("The user does not exist in the database");
                        addButton.setDisable(false);

                        //TODO When the user not in database you should add it
                    }
                    alert.show();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter ID number of client before searching");
                alert.show();
            }
        });


        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(5));
        buttonBar.setPrefSize(183, 54);
        buttonBar.setButtonMinWidth(70);
        buttonBar.setLayoutX(533);
        buttonBar.setLayoutY(402);
        buttonBar.getButtons().addAll(addButton, searchButton);
           /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            LocalDate date = dateOfBirthPicker.getValue();
            if (date != null) {
                System.out.println(formatter.format(date));
            }*/

        rootPane.getChildren().addAll(idNumberLabel, idNumberField, firstNameLabel, firstNameField, secondNameLabel, secondNameField, thirdNameLabel, thirdNameField, lastNameLabel, lastNameField, dateOfBirthLabel, dateOfBirthPicker, phoneOneLabel, phoneOneField, phoneTwoLabel, phoneTwoField, idImageLabel, idImageButton, drivingImageLabel, drivingImageButton, buttonBar);
        return rootPane;
    }


    public static AnchorPane CarPane() {
        AnchorPane rootPane = new AnchorPane();
        rootPane.setLayoutX(27);
        rootPane.setLayoutY(60);
        rootPane.setPrefSize(1300, 483);
        rootPane.getStyleClass().add("pane-2");

        rootPane.getChildren().clear();


        return rootPane;
    }

    public static AnchorPane DriverPane() {
        AnchorPane rootPane = new AnchorPane();
        rootPane.setLayoutX(27);
        rootPane.setLayoutY(60);
        rootPane.setPrefSize(1300, 483);
        rootPane.getStyleClass().add("pane-2");

        rootPane.getChildren().clear();


        return rootPane;
    }


}
