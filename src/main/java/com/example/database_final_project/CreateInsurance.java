/*Rami*/

package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*Rami*/
public class CreateInsurance {
    private static int num = 0; // 1:one phone,2:two phone
    private static final Client client = new Client();
    private static final Car car = new Car();
    private static byte[] idImage;
    private static byte[] drivingImage;
    private static byte[] carLicenseImage;
    private static boolean drivingImageRead = false;
    private static boolean idImageRead = false;
    private static boolean readLicense = false;
    private static ResultSet resultSet;
    private static final DatePicker datePicker = new DatePicker();

    public CreateInsurance() {

    }

    /*Rami*/
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


        Util.limitTextFieldLength(idNumberField, "Client ID number is 9 Digit", 9);
        Util.limitTextFieldLength(phoneOneField, "phone number is 10 Digit", 10);
        Util.limitTextFieldLength(phoneOneField, "phone number is 10 Digit", 10);


        CheckBox phoneTwoCheckBox = new CheckBox();
        phoneTwoCheckBox.setSelected(false);
        phoneTwoCheckBox.setLayoutX(444);
        phoneTwoCheckBox.setLayoutY(317);


        phoneTwoField.editableProperty().bind(phoneTwoCheckBox.selectedProperty());
        datePicker.setValue(null);
        datePicker.setPadding(new Insets(5));
        datePicker.setPrefSize(145, 25);
        datePicker.setLayoutX(479);
        datePicker.setLayoutY(250);


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

                Util.alert.show();
                try {
                    resultSet = DBConnection.insertClient(idNumberField.getText(), firstNameField.getText(), secondNameField.getText()
                            , thirdNameField.getText(), lastNameField.getText(), datePicker
                            , phoneOneField.getText(), phoneTwoField.getText(), idImage, drivingImage, num);

                    if (resultSet.next()) {
                        client.setSsn(Integer.parseInt(resultSet.getString("ssn")));
                        client.setFirstName(resultSet.getString("first_name"));
                        client.setSecondName(resultSet.getString("second_name"));
                        client.setThirdName(resultSet.getString("third_name"));
                        client.setFourthName(resultSet.getString("fourth_name"));
                        client.setPhone_1(Integer.parseInt(resultSet.getString("phone_1")));
                        client.setPhone_2(Integer.parseInt(resultSet.getString("phone_2")));
                        client.setDate(resultSet.getString("dob"));
                    }


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

                        client.setSsn(Integer.parseInt(resultSet.getString("ssn")));
                        client.setFirstName(resultSet.getString("first_name"));
                        client.setSecondName(resultSet.getString("second_name"));
                        client.setThirdName(resultSet.getString("third_name"));
                        client.setFourthName(resultSet.getString("fourth_name"));
                        client.setPhone_1(Integer.parseInt(resultSet.getString("phone_1")));
                        client.setPhone_2(Integer.parseInt(resultSet.getString("phone_2")));
                        client.setDate(resultSet.getString("dob"));

                        datePicker.setValue(LocalDate.of(Integer.parseInt(resultSet.getString("dob").split("-")[0]), Integer.parseInt(resultSet.getString("dob").split("-")[1]), Integer.parseInt(resultSet.getString("dob").split("-")[2])));

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
                    DBConnection.preparedStatement.close();
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


        Util.thirdPane.getChildren().addAll(Util.stepTextArea, refreshButton, idNumberLabel, idNumberField, firstNameLabel,
                firstNameField, secondNameLabel, secondNameField, thirdNameLabel, thirdNameField, lastNameLabel,
                lastNameField, dateOfBirthLabel, datePicker, phoneOneLabel, phoneTwoCheckBox, phoneOneField,
                phoneTwoLabel, phoneTwoField, idImageLabel, idImageButton, drivingImageLabel, drivingImageButton, buttonBar);
        return Util.thirdPane;
    }

    /*Rami*/
    public static AnchorPane CarPane() {
        Util.initializeThirdPane();

        Util.initializeStepTextArea(290, 173, """
                                                   Step
                -------------------------------------------------------
                1- Enter car ID
                2- Search
                3- User not in DataBase
                4- Insert all information
                5- Click calculate button
                6- Click Add button
                -------------------------------------------------------""");

        Label carIdLabel = Util.createLabel("Car ID", 523, 33, 110, 27);
        Label clientIdLabel = Util.createLabel("Client ID", 523, 76, 110, 27);
        Label carModelLabel = Util.createLabel("Car Model", 701, 76, 110, 27);
        Label engineSizeLabel = Util.createLabel("Engine Size", 523, 143, 110, 27);
        Label carColorLabel = Util.createLabel("Color", 701, 143, 110, 27);
        Label carLicenseLabel = Util.createLabel("Car license", 523, 208, 110, 27);
        Label carlicenseEndLabel = Util.createLabel("license End Date", 701, 208, 110, 27);
        Label carModelDateLabel = Util.createLabel("model Date", 523, 268, 110, 27);
        Label carPriceLabel = Util.createLabel("Car price", 701, 268, 110, 27);
        Label carInsuranceTypeLabel = Util.createLabel("insurance type", 523, 339, 110, 27);
        Label carInsurancePriceLabel = Util.createLabel("Insurance price", 701, 339, 110, 27);

        TextField carIdField = Util.createTextField("Car ID", 648, 33, 145, 27);
        TextField clientIdField = Util.createTextField("Client ID", 523, 105, 145, 27);
        TextField carModelField = Util.createTextField("Car Model", 701, 105, 145, 27);
        TextField engineSizeField = Util.createTextField("Engine Size", 523, 170, 145, 27);
        TextField carColorField = Util.createTextField("Color", 702, 170, 145, 27);
        TextField carModelDateField = Util.createTextField("Model Date", 523, 303, 145, 27);
        TextField carPriceField = Util.createTextField("Car price", 701, 303, 145, 27);
        TextField carInsurancePriceField = Util.createTextField("Insurance price", 701, 365, 145, 27);
        carInsurancePriceField.setDisable(true);
        clientIdField.setText(String.valueOf(client.getSsn()));

        datePicker.setValue(null);
        datePicker.setPadding(new Insets(0));
        datePicker.setPrefSize(145, 25);
        datePicker.setLayoutX(702);
        datePicker.setLayoutY(236);
        datePicker.setDisable(true);

        ComboBox<String> insuranceTypeComboBox = new ComboBox<>();
        insuranceTypeComboBox.getItems().addAll("Shamel", "Mandatory");
        insuranceTypeComboBox.setLayoutX(522);
        insuranceTypeComboBox.setLayoutY(366);
        insuranceTypeComboBox.setPrefSize(150, 25);
        insuranceTypeComboBox.setPromptText("Insurance Type");
        insuranceTypeComboBox.setDisable(true);

        Button readLicenseImage = Util.createButton("Car license", 524, 236, 145, 25);
        Button searchButton = Util.createButton("Search", 748, 417, 60, 25);
        Button addButton = Util.createButton("Add", 578, 417, 60, 25);
        Button calculateButton = Util.createButton("calculate ", 864, 366, 60, 25);

        readLicenseImage.setDisable(true);
        addButton.setDisable(true);
        calculateButton.setDisable(true);

        readLicenseImage.setOnAction(readLicenseEvent -> {
            try {
                carLicenseImage = Util.ReadImage(MenuPage.menuPageStage);
                readLicense = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        searchButton.setOnAction(e -> {
            if (!Util.isEmptyField(carIdField)) {
                if (carIdField.getText().length() >= 10) {
                    try {
                        resultSet = DBConnection.searchForCar(carIdField.getText(), clientIdField.getText());
                        Util.alert.setTitle("Result");
                        if (resultSet.next()) {
                            car.setColor(resultSet.getString("color"));
                            car.setEngineSize(Double.parseDouble(resultSet.getString("engin_size")));
                            car.setModel(resultSet.getString("model"));
                            car.setPrice(Double.parseDouble(resultSet.getString("price")));
                            car.setLicenseEnd(resultSet.getString("license_end"));
                            car.setModelDate(resultSet.getString("model_date"));
                            car.setClientID(resultSet.getString("client_id"));
                            car.setCarId(resultSet.getString("car_id"));

                            carColorField.setText(resultSet.getString("color"));
                            carModelField.setText(resultSet.getString("model"));
                            engineSizeField.setText(resultSet.getString("engin_size"));
                            datePicker.setValue(LocalDate.of(Integer.parseInt(resultSet.getString("license_end").split("-")[0]),
                                    Integer.parseInt(resultSet.getString("license_end").split("-")[1]),
                                    Integer.parseInt(resultSet.getString("license_end").split("-")[2])));
                            carModelDateField.setText(resultSet.getString("model_date").split("-")[0]);
                            carPriceField.setText(resultSet.getString("price"));

                            Util.alert.setAlertType(Alert.AlertType.INFORMATION);
                            Util.alert.setContentText("The car is in the database");
                        } else {
                            readLicenseImage.setDisable(false);
                            addButton.setDisable(false);
                            calculateButton.setDisable(false);
                            insuranceTypeComboBox.setDisable(false);
                            datePicker.setDisable(false);
                            Util.alert.setAlertType(Alert.AlertType.ERROR);
                            Util.alert.setContentText("The car does not exist in the database");

                        }
                        Util.alert.show();

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage() + "  2");
                    }
                    try {
                        DBConnection.preparedStatement.close();
                        DBConnection.conn.close();
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {
                    Util.alert.setAlertType(Alert.AlertType.ERROR);
                    Util.alert.setTitle("Error");
                    Util.alert.setContentText("Enter Car id must be at least 10");
                    Util.alert.show();
                }

            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setTitle("Error");
                Util.alert.setContentText("Enter Car id before search");
                Util.alert.show();

            }
        });

        addButton.setOnAction(e -> {
            if (!Util.isEmptyField(carIdField) && !Util.isEmptyField(clientIdField) && !Util.isEmptyField(carModelField)
                    && !Util.isEmptyField(engineSizeField) && !Util.isEmptyField(carColorField) &&
                    !Util.isEmptyField(carModelDateField) && !Util.isEmptyField(carPriceField)
                    && !insuranceTypeComboBox.getSelectionModel().isEmpty() && readLicense
                    && !Util.isEmptyField(carInsurancePriceField)) {
                try {
                    resultSet = DBConnection.insertCar(carIdField.getText(), clientIdField.getText(), carModelField.getText(), engineSizeField.getText()
                            , carLicenseImage, datePicker, carModelDateField.getText(), carPriceField.getText(),
                            carInsurancePriceField.getText(), insuranceTypeComboBox.getValue(), carColorField.getText());
                    if (resultSet.next()) {
                        car.setColor(resultSet.getString("color"));
                        car.setEngineSize(Double.parseDouble(resultSet.getString("engin_size")));
                        car.setModel(resultSet.getString("model"));
                        car.setPrice(Double.parseDouble(resultSet.getString("price")));
                        car.setLicenseEnd(resultSet.getString("license_end"));
                        car.setModelDate(resultSet.getString("model_date"));
                        car.setClientID(resultSet.getString("client_id"));
                        car.setCarId(resultSet.getString("car_id"));
                    }
                    Util.alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    Util.alert.setTitle("Result");
                    Util.alert.setContentText("Car added Successfully");
                    Util.alert.show();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage() + "  1");
                }
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setContentText("Some data Are missing");
                Util.alert.setTitle("Error");
                Util.alert.show();
            }
        });

        calculateButton.setOnAction(e -> {
            if (!insuranceTypeComboBox.getSelectionModel().isEmpty() && !Util.isEmptyField(carPriceField) && !Util.isEmptyField(engineSizeField)) {
                carInsurancePriceField.setText("" + Util.cacluatePrice(insuranceTypeComboBox.getValue(), Integer.parseInt(engineSizeField.getText()), Integer.parseInt(carPriceField.getText())));
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setContentText("insurance type first or Engine size or car price ins empty");
                Util.alert.setTitle("Error");
                Util.alert.show();
            }
        });
        Util.thirdPane.getChildren().addAll(Util.stepTextArea, carIdLabel, carIdField, clientIdLabel, clientIdField,
                carModelLabel, carModelField, engineSizeLabel, engineSizeField, carColorLabel, carColorField
                , carLicenseLabel, readLicenseImage, carlicenseEndLabel, datePicker, carModelDateLabel, carModelDateField,
                carPriceLabel, carPriceField, carInsuranceTypeLabel, insuranceTypeComboBox,
                carInsurancePriceLabel, carInsurancePriceField, calculateButton, searchButton, addButton);


        return Util.thirdPane;
    }

    public static AnchorPane DriverPane() {
        Util.initializeThirdPane();


        return Util.thirdPane;
    }


}
