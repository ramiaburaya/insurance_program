/*Saja*/
package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update {
    private static int num = 0; // 1:one phone,2:two phone
    private static final Client client = new Client();

    //	    private static final Car car = new Car();
    private static byte[] idImage;
    private static byte[] drivingImage;
    private static byte[] carLicenseImage;
    private static boolean drivingImageRead = false;
    private static boolean idImageRead = false;
    private static boolean readLicense = false;
    private static ResultSet resultSet;
    private static final DatePicker datePicker = new DatePicker();


    public static AnchorPane TransferPane() {
        Util.initializeThirdPane();


        Util.initializeStepTextArea(302, 278, """
                                                   Step
                -------------------------------------------------------
                1- Enter Insurance ID
                2- Search
                3- Enter Client ID
                4- Search
                5- Insurance in DataBase
                -------------------------------------------------------
                6- User in DataBase
                7- Click Transfer button
                -------------------------------------------------------
                8- User not in DataBase
                9- Insert all information
                10- Click Transfer button
                -------------------------------------------------------""");
        datePicker.setValue(null);
        datePicker.setPrefSize(145, 25);
        datePicker.setLayoutX(479);
        datePicker.setLayoutY(250);


        Label insuranceidLabel = Util.createLabel("Insurance ID ", 479, 15, 93, 27);// تحديد الابعاد
        Label idNumberLabel = Util.createLabel("Client ID ", 651, 15, 93, 27);
        Label firstNameLabel = Util.createLabel("First name", 479, 75, 93, 27);
        Label secondNameLabel = Util.createLabel("Second name", 651, 75, 93, 27);
        Label thirdNameLabel = Util.createLabel("Third name", 479, 151, 93, 27);
        Label lastNameLabel = Util.createLabel("Last name", 651, 151, 93, 27);
        Label dateOfBirthLabel = Util.createLabel("Date of Birth", 479, 222, 93, 27);
        Label phoneOneLabel = Util.createLabel("Phone one", 651, 222, 93, 27);
        Label phoneTwoLabel = Util.createLabel("Phone two", 479, 286, 93, 27);
        Label idImageLabel = Util.createLabel("ID image", 651, 286, 93, 27);
        Label drivingImageLabel = Util.createLabel("Driving image", 485, 364, 93, 27);

        TextField idNumberField = Util.createTextField("ID number", 479, 42, 145, 27);
        TextField clientIdField = Util.createTextField(" new client ID ", 650, 42, 145, 27);

        TextField firstNameField = Util.createTextField("First name", 479, 102, 145, 27);
        TextField secondNameField = Util.createTextField("Second name", 650, 102, 145, 27);
        TextField thirdNameField = Util.createTextField("Third name", 479, 178, 145, 27);
        TextField lastNameField = Util.createTextField("Last name", 650, 177, 145, 27);
        TextField phoneOneField = Util.createTextField("Phone one", 650, 250, 145, 27);
        TextField phoneTwoField = Util.createTextField("Phone two", 479, 313, 145, 27);

        Util.limitTextFieldLength(idNumberField, "insurance ID number is 10 Digit", 10);
        Util.limitTextFieldLength(phoneOneField, "phone number is 10 Digit", 10);
        Util.limitTextFieldLength(phoneOneField, "phone number is 10 Digit", 10);

        Button idImageButton = Util.createButton("ID image", 650, 312, 145, 25);
        Button drivingImageButton = Util.createButton("Driving image", 650, 364, 145, 25);
        Button checkIDAndPhoneButton = Util.createButton("Check", 827, 28, 47, 25);
        Button transferButton = Util.createButton("Transfer", 504, 419, 111, 25);
        Button searchButton = Util.createButton("search", 651, 419, 111, 25);
        Button search1Button = Util.createButton("search", 651, 419, 111, 25);

        transferButton.setDisable(true);
        checkIDAndPhoneButton.setDisable(true);
        drivingImageButton.setDisable(true);
        idImageButton.setDisable(true);

        searchButton.setOnAction(searchEvent -> {
            if (!Util.isEmptyField(idNumberField)) {
                try {
                    resultSet = DBConnection.searchForInsuarcenModify(idNumberField.getText());
                    Util.alert.setTitle("Result");
                    if (resultSet.next()) {
                        Util.alert.setAlertType(Alert.AlertType.INFORMATION);
                        Util.alert.setContentText("The insurance is in the database, search for client");
                        Util.thirdPane.getChildren().remove(searchButton);
                        Util.thirdPane.getChildren().add(search1Button);
                        search1Button.setOnAction(e -> {
                            if (!Util.isEmptyField(clientIdField)) {
                                try {
                                    ResultSet clientResultSet = DBConnection.searchForClientModify(clientIdField.getText());
                                    if (clientResultSet.next()) {
                                        Util.alert.setAlertType(Alert.AlertType.INFORMATION);
                                        Util.alert.setContentText("The insurance is in the database, search for client");
                                        Util.thirdPane.getChildren().remove(searchButton);

                                        Util.alert.show();
                                    } else {
                                        idImageButton.setDisable(false);
                                        transferButton.setDisable(false);
                                        Util.alert.setAlertType(Alert.AlertType.ERROR);
                                        Util.alert.setContentText("The user does not exist in the Database, Insert All information");
                                        Util.thirdPane.getChildren().add(checkIDAndPhoneButton);
                                        Util.alert.show();
                                    }

                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }


                            } else {
                                Util.alert.setAlertType(Alert.AlertType.ERROR);
                                Util.alert.setContentText("Please enter ID number of client before searching");
                                Util.thirdPane.getChildren().add(checkIDAndPhoneButton);
                            }
                        });
                    } else {
                        idImageButton.setDisable(false);

                        Util.alert.setAlertType(Alert.AlertType.ERROR);
                        Util.alert.setContentText("The insurance does not exist in the database");
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
                Util.alert.setContentText("Please enter ID number of insurance before searching");
                Util.alert.show();
            }
        });
        transferButton.setOnAction(UpdateEvent -> {
            if (!Util.isEmptyField(idNumberField)) {
                if (clientIdField.getText().equals(client.getSsn()) && idNumberField.getText().equals(transferButton)) {

                }
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
        Util.thirdPane.getChildren().addAll(Util.stepTextArea, insuranceidLabel, idNumberLabel, idNumberField, firstNameLabel,
                firstNameField, secondNameLabel, secondNameField, thirdNameLabel, thirdNameField, lastNameLabel,
                lastNameField, dateOfBirthLabel, datePicker, phoneOneLabel, phoneOneField,
                phoneTwoLabel, phoneTwoField, idImageLabel, idImageButton, drivingImageLabel, drivingImageButton, clientIdField, searchButton, transferButton);
        return Util.thirdPane;
    }

    public static AnchorPane ChangeInsurancePane() {
        Util.initializeThirdPane();
        Label engineSizeLabel = Util.createLabel("Engine Size", 523, 143, 110, 27);
        Label insuranceIdLabel = Util.createLabel("Insurance ID", 523, 33, 110, 27);
        Label carInsuranceTypeLabel = Util.createLabel("insurance type", 523, 339, 110, 27);
        Label InsurancePriceLabel = Util.createLabel(" price to pay", 701, 339, 110, 27);

        TextField insuranceIdField = Util.createTextField("Insurance Id", 648, 33, 145, 27);
        TextField InsurancePriceField = Util.createTextField("Insurance price", 701, 365, 145, 27);
        TextField engineSizeField = Util.createTextField("Engine Size", 523, 170, 145, 27);
        InsurancePriceField.setDisable(true);


        datePicker.setValue(null);
        datePicker.setPadding(new Insets(0));
        datePicker.setPrefSize(145, 25);
        datePicker.setLayoutX(479);
        datePicker.setLayoutY(250);
        datePicker.setDisable(true);

        Button searchButton = Util.createButton("Search", 748, 417, 60, 25);
        Button ChangeButton = Util.createButton("Change", 578, 417, 60, 25);
        Button calculateButton = Util.createButton("calculate ", 864, 366, 60, 25);
        Util.initializeStepTextArea(290, 234, """
                                                   Step
                -----------------------------------------------------
                1- Enter Insurance ID
                2- Search
                3- Select insurance Type
                4- click Calculate button
                5- Enter Change Button
                --------------------------------------------------------""");

        ComboBox<String> insuranceTypeComboBox = new ComboBox<>();
        insuranceTypeComboBox.getItems().addAll("Shamel", "Mandatory");
        insuranceTypeComboBox.setLayoutX(522);
        insuranceTypeComboBox.setLayoutY(366);
        insuranceTypeComboBox.setPrefSize(150, 25);
        insuranceTypeComboBox.setPromptText("Insurance Type");
        insuranceTypeComboBox.setDisable(true);
        calculateButton.setOnAction(e -> {
            if (!insuranceTypeComboBox.getSelectionModel().isEmpty() && !Util.isEmptyField(InsurancePriceField) && !Util.isEmptyField(engineSizeField)) {
                InsurancePriceField.setText("" + Util.cacluatePrice(insuranceTypeComboBox.getValue(), Integer.parseInt(engineSizeField.getText()), Integer.parseInt(InsurancePriceField.getText())));
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setContentText("insurance type first or Engine size or car price ins empty");
                Util.alert.setTitle("Error");
                Util.alert.show();
            }
        });
        ChangeButton.setOnAction(UpdateEvent -> {

        });
        Util.thirdPane.getChildren().addAll(Util.stepTextArea,
                engineSizeLabel, engineSizeField, datePicker, engineSizeLabel, insuranceIdLabel, carInsuranceTypeLabel, InsurancePriceLabel,
                insuranceIdField, InsurancePriceField, engineSizeField, calculateButton, searchButton);
        return Util.thirdPane;
    }
}
