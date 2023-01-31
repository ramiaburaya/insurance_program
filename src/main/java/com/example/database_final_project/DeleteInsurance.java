package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteInsurance {
    public static AnchorPane deletePane() {
        Util.initializeThirdPane();
        Util.initializeStepTextArea(310, 240, """
                                                   Step
                -------------------------------------------------------
                First Way
                         1- Enter Client ID
                         2- Enter insurance ID
                         3- Click Search
                         4- Click Delete Button
                -------------------------------------------------------
                Second Way
                         1- Enter Client ID
                         2- Enter Car ID
                         3- Click Search
                         4- Click Delete Button""");

        ComboBox<String> wayComboBox = new ComboBox<>();
        wayComboBox.getItems().addAll("First Way", "Second Way");
        wayComboBox.setLayoutX(341);
        wayComboBox.setLayoutY(22);
        wayComboBox.setPrefSize(150, 35);
        wayComboBox.setPromptText("Select way");

        wayComboBox.setPadding(new Insets(5));
        Label clientIdLabel = Util.createLabel("Client ID", 544, 47, 94, 17);
        Label insuranceIdLabel = Util.createLabel("Insurance ID", 544, 91, 94, 17);
        Label carIDLabel = Util.createLabel("Car ID", 544, 132, 94, 17);

        TextField clientIDField = Util.createTextField("Client ID", 638, 47, 145, 27);
        TextField insuranceIDField = Util.createTextField("Insurance ID", 638, 91, 145, 27);
        TextField carIDField = Util.createTextField("Car Number", 638, 132, 145, 27);
        insuranceIDField.setEditable(false);
        carIDField.setEditable(false);


        wayComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("First Way")) {
                insuranceIDField.setEditable(true);
                carIDField.setEditable(false);

            } else {
                carIDField.setEditable(true);
                insuranceIDField.setEditable(false);

            }
        });

        Button searchButton = Util.createButton("Search", 650, 307, 80, 25);

        Button deleteButton = Util.createButton("Delete", 544, 307, 80, 25);
        deleteButton.setDisable(true);


        searchButton.setOnAction(searchEvent -> {
            if (!wayComboBox.getSelectionModel().isEmpty()) {
                if (wayComboBox.getSelectionModel().getSelectedItem().equals("First Way")) {
                    if (!Util.isEmptyField(clientIDField) || !Util.isEmptyField(insuranceIDField)) {
                        if (Util.isValid(clientIDField, 9) && Util.isValid(insuranceIDField, 10)) {
                            if (Util.isInt(clientIDField)) {
                                try {
                                    ResultSet resultSet = DBConnection.searchClientInsurance(clientIDField.getText(), insuranceIDField.getText());
                                    //TODO make the result out in Table View

                                } catch (SQLException e) {
                                    System.out.println(e.getMessage());
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
                                Util.alert.setContentText("Client Id must be Integer");
                                Util.alert.show();
                            }
                        } else {
                            Util.alert.setAlertType(Alert.AlertType.ERROR);
                            Util.alert.setTitle("Error");
                            Util.alert.setContentText("Client ID must be 9 Digit and insurance ID must be 10 digit");
                            Util.alert.show();
                        }
                    } else {
                        Util.alert.setAlertType(Alert.AlertType.ERROR);
                        Util.alert.setTitle("Error");
                        Util.alert.setContentText("Client ID or Insurance ID Empty");
                        Util.alert.show();
                    }
                } else {
                    if (!Util.isEmptyField(clientIDField) || !Util.isEmptyField(carIDField)) {
                        //TODO Make search using clientID and carID
                    } else {
                        Util.alert.setAlertType(Alert.AlertType.ERROR);
                        Util.alert.setTitle("Error");
                        Util.alert.setContentText("Client ID or Car ID Empty");
                        Util.alert.show();
                    }
                }
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setTitle("Error");
                Util.alert.setContentText("Select way");
                Util.alert.show();
            }
        });
        Util.thirdPane.getChildren().addAll(Util.stepTextArea, wayComboBox, clientIdLabel, clientIDField, insuranceIdLabel,
                insuranceIDField, carIDLabel, carIDField, searchButton, deleteButton);
        return Util.thirdPane;
    }
}
