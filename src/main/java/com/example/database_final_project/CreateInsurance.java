package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateInsurance {
    private static int num = 0; // 1:one phone,2:two phone
    private static Client client;
    private final Car car = new Car();
    private static byte[] idImage;
    private static byte[] drivingImage;
    private static boolean drivingImageRead = false;
    private static boolean idImageRead = false;
    private static ResultSet resultSet;

    public CreateInsurance() {

    }

    public static AnchorPane ClientPane() {
        Util.initializeThirdPane();

        Util.initializeStepTextArea(290, 234, """
                                                   Step
                -------------------------------------------------------
                1- Enter Client ID
                2- Search
                3- User in Database move to car information
                -------------------------------------------------------
                4- User not in DataBase
                5- Insert all information
                6- phone should be 10 number
                7- Click Check button
                8- Click Add button
                -------------------------------------------------------""");


        Label idNumberLabel = Util.createLabel("ID number", 485, 28, 93, 27);
        Label firstNameLabel = Util.createLabel("First name", 479, 75, 93, 27);
        Label secondNameLabel = Util.createLabel("Second name", 651, 75, 93, 27);
        Label thirdNameLabel = Util.createLabel("Third name", 479, 151, 93, 27);
        Label lastNameLabel = Util.createLabel("Last name", 651, 151, 93, 27);
        Label dateOfBirthLabel = Util.createLabel("Date of Birth", 479, 222, 93, 27);
        Label phoneOneLabel = Util.createLabel("Phone one", 651, 222, 93, 27);
        Label phoneTwoLabel = Util.createLabel("Phone two", 479, 286, 93, 27);
        Label idImageLabel = Util.createLabel("ID image", 651, 286, 93, 27);
        Label drivingImageLabel = Util.createLabel("Driving image", 485, 364, 93, 27);

        TextField idNumberField = Util.createTextField("ID number", 650, 28, 145, 27);
        TextField firstNameField = Util.createTextField("First name", 479, 102, 145, 27);
        TextField secondNameField = Util.createTextField("Second name", 650, 102, 145, 27);
        TextField thirdNameField = Util.createTextField("Third name", 479, 178, 145, 27);
        TextField lastNameField = Util.createTextField("Last name", 650, 177, 145, 27);
        TextField phoneOneField = Util.createTextField("Phone one", 650, 250, 145, 27);
        TextField phoneTwoField = Util.createTextField("Phone two", 479, 313, 145, 27);


        CheckBox phoneTwoCheckBox = new CheckBox();
        phoneTwoCheckBox.setSelected(false);
        phoneTwoCheckBox.setLayoutX(444);
        phoneTwoCheckBox.setLayoutY(317);


        phoneTwoField.editableProperty().bind(phoneTwoCheckBox.selectedProperty());

        DatePicker dateOfBirthPicker = new DatePicker();
        dateOfBirthPicker.setPadding(new Insets(5));
        dateOfBirthPicker.setPrefSize(145, 25);
        dateOfBirthPicker.setLayoutX(479);
        dateOfBirthPicker.setLayoutY(250);


        Button refreshButton = Util.createButton("Refresh", 14, 264, 68, 25);
        Button idImageButton = Util.createButton("ID image", 650, 312, 145, 25);
        Button drivingImageButton = Util.createButton("Driving image", 650, 364, 145, 25);
        Button checkIDAndPhoneButton = Util.createButton("Check", 827, 28, 47, 25);
        Button searchButton = Util.createButton("search", 0, 0, 75, 25);
        Button addButton = Util.createButton("Add", 0, 0, 75, 25);

        addButton.setDisable(true);
        checkIDAndPhoneButton.setDisable(true);
        drivingImageButton.setDisable(true);
        idImageButton.setDisable(true);

        refreshButton.setOnAction(refreshEvent -> {
            MenuPage.menuPageStage.close();
            MenuPage.PrintMenuPage();
        });

        addButton.setOnAction(addEvent -> {
            if (Util.isEmptyField(firstNameField) || Util.isEmptyField(secondNameField) || Util.isEmptyField(thirdNameField)
                || Util.isEmptyField(lastNameField) || Util.isEmptyField(idNumberField) || Util.isEmptyField(phoneOneField)
                || !idImageRead || !drivingImageRead) {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setTitle("Error");
                Util.alert.setContentText("Some data not entered");
                Util.alert.show();
            } else {
                try {
                    DBConnection.insertClient(idNumberField.getText(), firstNameField.getText(), secondNameField.getText()
                            , thirdNameField.getText(), lastNameField.getText(), dateOfBirthPicker
                            , phoneOneField.getText(), phoneTwoField.getText(), idImage, drivingImage, num);
                    Util.alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    Util.alert.setTitle("Successfully");
                    Util.alert.setContentText("Client Added successfully");
                    Util.alert.show();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }


            addButton.setDisable(true);
            idImageButton.setDisable(true);
            drivingImageButton.setDisable(true);
            checkIDAndPhoneButton.setDisable(true);
            searchButton.setDisable(true);

        });

        checkIDAndPhoneButton.setOnAction(checkIDAndPhoneEvent -> {
            if (phoneTwoCheckBox.selectedProperty().getValue()) {
                if ((Util.isValid(idNumberField, 9) && Util.isInt(idNumberField))
                    && (Util.isValid(phoneOneField, 10) && Util.isInt(phoneOneField))
                    && (Util.isValid(phoneTwoField, 10) && Util.isInt(phoneTwoField))) {
                    addButton.setDisable(false);
                    checkIDAndPhoneButton.setDisable(true);
                } else {
                    Util.alert.setAlertType(Alert.AlertType.ERROR);
                    Util.alert.setTitle("Error");
                    Util.alert.setContentText("check if the id number digit is : 9 And ( phone one and phone two number digit is :10 )");
                    Util.alert.show();
                }
            } else {
                if ((Util.isValid(idNumberField, 9) && Util.isInt(idNumberField))
                    && (Util.isValid(phoneOneField, 10) && Util.isInt(phoneOneField))) {

                    num = 1;
                    addButton.setDisable(false);
                    checkIDAndPhoneButton.setDisable(true);
                } else {
                    Util.alert.setAlertType(Alert.AlertType.ERROR);
                    Util.alert.setTitle("Error");
                    Util.alert.setContentText("check if the id number digit is : 9 And phone one number digit is :10 )");
                    Util.alert.show();
                }

            }
        });

        searchButton.setOnAction(searchEvent -> {
            if (!Util.isEmptyField(idNumberField)) {
                try {
                    resultSet = DBConnection.searchClient(idNumberField.getText());
                    Util.alert.setTitle("Result");
                    if (resultSet.next()) {
                        idImageButton.setDisable(true);

                        client = new Client(Integer.parseInt(resultSet.getString("ssn")), resultSet.getString("first_name"), resultSet.getString("second_name"), resultSet.getString("third_name"), resultSet.getString("fourth_name"), Integer.parseInt(resultSet.getString("phone_1")), Integer.parseInt(resultSet.getString("phone_2")), resultSet.getString("dob"));

                        dateOfBirthPicker.setValue(LocalDate.of(Integer.parseInt(resultSet.getString("dob").split("-")[0]), Integer.parseInt(resultSet.getString("dob").split("-")[1]), Integer.parseInt(resultSet.getString("dob").split("-")[2])));

                        firstNameField.setText(resultSet.getString("first_name"));
                        secondNameField.setText(resultSet.getString("second_name"));
                        thirdNameField.setText(resultSet.getString("third_name"));
                        lastNameField.setText(resultSet.getString("fourth_name"));
                        Util.alert.setAlertType(Alert.AlertType.INFORMATION);
                        Util.alert.setContentText("The user is in the database, you can add a new car");
                        phoneOneField.setText("0" + resultSet.getString("phone_1"));
                        phoneTwoField.setText("0" + resultSet.getString("phone_2"));

                    } else {
                        idImageButton.setDisable(false);

                        Util.alert.setAlertType(Alert.AlertType.ERROR);
                        Util.alert.setContentText("The user does not exist in the database");
                        Util.thirdPane.getChildren().add(checkIDAndPhoneButton);
                    }
                    Util.alert.show();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                try {
                    DBConnection.stmt.close();
                    DBConnection.conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setTitle("Error");
                Util.alert.setContentText("Please enter ID number of client before searching");
                Util.alert.show();
            }
        });

        idImageButton.setOnAction(idImageEvent -> {
            try {
                idImage = Util.ReadImage(MenuPage.menuPageStage);
                idImageRead = true;
                drivingImageButton.setDisable(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        drivingImageButton.setOnAction(drivingImageEvent -> {
            try {
                drivingImage = Util.ReadImage(MenuPage.menuPageStage);
                drivingImageRead = true;
                checkIDAndPhoneButton.setDisable(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ButtonBar buttonBar = Util.createButtonBar(533, 402, 183, 54, 70);

        buttonBar.getButtons().addAll(addButton, searchButton);


        Util.thirdPane.getChildren().addAll(Util.stepTextArea, refreshButton, idNumberLabel, idNumberField, firstNameLabel, firstNameField, secondNameLabel, secondNameField, thirdNameLabel, thirdNameField, lastNameLabel, lastNameField, dateOfBirthLabel, dateOfBirthPicker, phoneOneLabel, phoneTwoCheckBox, phoneOneField, phoneTwoLabel, phoneTwoField, idImageLabel, idImageButton, drivingImageLabel, drivingImageButton, buttonBar);
        return Util.thirdPane;
    }


    public static AnchorPane CarPane() {
        Util.initializeThirdPane();


        return Util.thirdPane;
    }

    public static AnchorPane DriverPane() {
        Util.initializeThirdPane();


        return Util.thirdPane;
    }


}
