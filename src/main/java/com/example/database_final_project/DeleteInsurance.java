package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.sql.SQLException;
import java.util.ArrayList;


public class DeleteInsurance {

    public static AnchorPane deletePane() {
        Util.initializeThirdPane();
        Util.initializeStepTextArea(310, 250, """
                                                   Step
                -------------------------------------------------------
                First Way
                         1- Enter Client ID
                         2- Enter insurance ID
                         3- Click Search
                         4- Select Row
                         4- Click Delete Button
                -------------------------------------------------------
                Second Way
                         1- Enter Client ID
                         2- Click Search
                         4- Select Row
                         3- Click Delete Button""");

        ComboBox<String> wayComboBox = new ComboBox<>();
        wayComboBox.getItems().addAll("First Way", "Second Way");
        wayComboBox.setLayoutX(106);
        wayComboBox.setLayoutY(264);
        wayComboBox.setPrefSize(150, 15);
        wayComboBox.setPromptText("Select way");
        wayComboBox.setPadding(new Insets(5));

        Label clientIdLabel = Util.createLabel("Client ID", 14, 316, 94, 17);
        Label insuranceIdLabel = Util.createLabel("Insurance ID", 14, 358, 94, 17);
        Label carIDLabel = Util.createLabel("Car ID", 14, 396, 94, 17);

        TextField clientIDField = Util.createTextField("Client ID", 108, 316, 145, 27);
        TextField insuranceIDField = Util.createTextField("Insurance ID", 108, 358, 145, 27);
        TextField carIDField = Util.createTextField("Car Number", 108, 396, 145, 27);
        TextField numberOfRecordsField = Util.createTextField("Car Number", 0, 0, 0, 0);

        insuranceIDField.setEditable(false);
        carIDField.setEditable(false);

        Util.limitTextFieldLength(clientIDField, "Client ID number is 9 Digit", 9);
        Util.limitTextFieldLength(insuranceIDField, "insurance ID number is 10 Digit", 10);

        wayComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> insuranceIDField.setEditable(newValue.equals("First Way")));

        Line line = new Line();
        line.setLayoutX(318);
        line.setLayoutY(30);
        line.setStartX(27);
        line.setEndX(27);
        line.setStartY(-15);
        line.setEndY(437);

        TableView<Record> table = new TableView<>();
        table.setPadding(new Insets(5));
        table.setEditable(false);
        table.setLayoutX(352);
        table.setLayoutY(15);
        table.setPrefSize(934, 453);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setOnMouseClicked(tableEvent -> {
            if (tableEvent.getClickCount() == 2) {
                Record record = table.getFocusModel().getFocusedItem();
                if (record != null) {
                    carIDField.setText(record.carID);
                    insuranceIDField.setText(record.insuranceId);
                    clientIDField.setText(record.clientId);
                }
            }
        });

        TableColumn<Record, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));


        TableColumn<Record, String> clientIdColumn = new TableColumn<>("Client ID");
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));


        TableColumn<Record, String> carIdColumn = new TableColumn<>("Car ID");
        carIdColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));


        TableColumn<Record, String> carModelColumn = new TableColumn<>("Car Model");
        carModelColumn.setCellValueFactory(new PropertyValueFactory<>("carModel"));


        TableColumn<Record, String> insuranceIdColumn = new TableColumn<>("Insurance ID");
        insuranceIdColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceId"));

        TableColumn<Record, String> insuranceTypeColumn = new TableColumn<>("Insurance Type");
        insuranceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("insuranceType"));

        TableColumn<Record, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Record, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        table.getColumns().addAll(nameColumn, clientIdColumn, carIdColumn, carModelColumn, insuranceIdColumn
                , insuranceTypeColumn, startDateColumn, endDateColumn);

        Button searchButton = Util.createButton("Search", 181, 432, 80, 25);

        Button deleteButton = Util.createButton("Delete", 21, 432, 80, 25);
        deleteButton.setDisable(true);


        searchButton.setOnAction(searchEvent -> {
            table.getItems().clear();
            if (!wayComboBox.getSelectionModel().isEmpty()) {
                if (wayComboBox.getSelectionModel().getSelectedItem().equals("First Way")) {
                    if (!Util.isEmptyField(clientIDField) || !Util.isEmptyField(insuranceIDField)) {
                        if (Util.isValid(clientIDField, 9) && Util.isValid(insuranceIDField, 10)) {
                            if (Util.isInt(clientIDField)) {
                                try {
                                    Record record = DBConnection.searchClientInsurance(clientIDField.getText(), insuranceIDField.getText());
                                    numberOfRecordsField.setText("1");
                                    table.getItems().add(record);
                                    nameColumn.setResizable(false);
                                    clientIdColumn.setResizable(false);
                                    carIdColumn.setResizable(false);
                                    carModelColumn.setResizable(false);
                                    insuranceIdColumn.setResizable(false);
                                    insuranceTypeColumn.setResizable(false);
                                    startDateColumn.setResizable(false);
                                    endDateColumn.setResizable(false);

                                    deleteButton.setDisable(false);
                                } catch (SQLException e) {
                                    System.out.println(e.getMessage());
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
                    if (!Util.isEmptyField(clientIDField)) {
                        try {
                            ArrayList<Record> records = DBConnection.searchClientToDelete(clientIDField.getText());
                            numberOfRecordsField.setText("" + records.size());
                            for (Record record : records) {
                                table.getItems().add(record);
                            }
                            nameColumn.setResizable(false);
                            clientIdColumn.setResizable(false);
                            carIdColumn.setResizable(false);
                            carModelColumn.setResizable(false);
                            insuranceIdColumn.setResizable(false);
                            insuranceTypeColumn.setResizable(false);
                            startDateColumn.setResizable(false);
                            endDateColumn.setResizable(false);
                            deleteButton.setDisable(false);
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
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

        deleteButton.setOnAction(deleteEvent -> {
            if (!Util.isEmptyField(clientIDField) && !Util.isEmptyField(carIDField) && !Util.isEmptyField(insuranceIDField)) {
                try {
                    DBConnection.deleteInsurance(clientIDField.getText(), carIDField.getText()
                            , Integer.parseInt(numberOfRecordsField.getText()));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    DBConnection.preparedStatement.close();
                    DBConnection.conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                Util.alert.setAlertType(Alert.AlertType.CONFIRMATION);
                Util.alert.setTitle("Done");
                Util.alert.setContentText("Deleted Successfully");
                Util.alert.show();
            } else {
                Util.alert.setAlertType(Alert.AlertType.ERROR);
                Util.alert.setTitle("Error");
                Util.alert.setContentText("select row first");
                Util.alert.show();
            }
        });
        Util.thirdPane.getChildren().addAll(Util.stepTextArea, wayComboBox, clientIdLabel, clientIDField, insuranceIdLabel,
                insuranceIDField, carIDLabel, carIDField, searchButton, deleteButton, line, table);
        return Util.thirdPane;
    }
}
