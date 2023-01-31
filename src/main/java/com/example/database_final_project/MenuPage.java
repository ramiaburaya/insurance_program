package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuPage {
    public static Stage menuPageStage = new Stage();

    public static void PrintMenuPage() {

        AnchorPane rootPane = new AnchorPane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.web("#BFACE2"), CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.setPadding(new Insets(10));


        AnchorPane secondPane = new AnchorPane();
        secondPane.setPadding(new Insets(5));
        secondPane.setLayoutX(5);
        secondPane.setLayoutY(96);
        secondPane.setPrefSize(1354, 559);
        secondPane.getStyleClass().add("pane");

        ButtonBar buttonBar = Util.createButtonBar(433, 29, 500, 40, 70);


        ImageView icon = new ImageView();
        icon.setFitHeight(70);
        icon.setFitWidth(267);
        icon.setLayoutX(1084);
        icon.setLayoutY(14);
        icon.setStyle("-fx-opacity: 0.7;");
        icon.setImage(Util.icon);

        Label op = Util.createLabel("The operation", 53, 36, 93, 27);

        TextField opField = Util.createTextField("The operation", 147, 37, 145, 27);
        opField.setEditable(false);

        Button createButton = new Button("Create Insurance");
        createButton.setCursor(Cursor.HAND);
        createButton.setTextFill(Paint.valueOf("#fffdfd"));
        createButton.getStyleClass().add("button");
        createButton.setOnAction(e -> {
            secondPane.getChildren().clear();
            opField.setText("Create Insurance");

            Button clientInfo = new Button("Client Information");
            clientInfo.setCursor(Cursor.HAND);
            clientInfo.setTextFill(Paint.valueOf("#fffdfd"));
            clientInfo.getStyleClass().add("button");

            Button driversInfo = new Button("Driver Information");
            driversInfo.setCursor(Cursor.HAND);
            driversInfo.setTextFill(Paint.valueOf("#fffdfd"));
            driversInfo.getStyleClass().add("button");

            Button carInfo = new Button("Car Information");
            carInfo.setCursor(Cursor.HAND);
            carInfo.setTextFill(Paint.valueOf("#fffdfd"));
            carInfo.getStyleClass().add("button");

            ButtonBar createButtonsPane = Util.createButtonBar(479, 14, 396, 40, 70);
            createButtonsPane.getButtons().addAll(clientInfo, carInfo, driversInfo);


            clientInfo.setOnAction(clientEvent -> {
                secondPane.getChildren().clear();
                secondPane.getChildren().addAll(createButtonsPane, CreateInsurance.ClientPane());
            });

            carInfo.setOnAction(carEvent -> {
                secondPane.getChildren().clear();

                secondPane.getChildren().addAll(createButtonsPane, CreateInsurance.CarPane());


            });

            driversInfo.setOnAction(driverEvent -> {
                secondPane.getChildren().clear();

                secondPane.getChildren().addAll(createButtonsPane, CreateInsurance.DriverPane());

            });
            secondPane.getChildren().add(createButtonsPane);
        });

        Button deleteButton = new Button("Delete Insurance");
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setTextFill(Paint.valueOf("#fffdfd"));
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction(e -> {

            secondPane.getChildren().clear();
            opField.setText("Delete Insurance");
        });

        Button modifyButton = new Button("Modify Insurance");
        modifyButton.setCursor(Cursor.HAND);
        modifyButton.setTextFill(Paint.valueOf("#fffdfd"));
        modifyButton.getStyleClass().add("button");
        modifyButton.setOnAction(e -> {
            secondPane.getChildren().clear();
            opField.setText("Modify Insurance");
        });

        Button searchButton = new Button("Search");
        searchButton.setCursor(Cursor.HAND);
        searchButton.setTextFill(Paint.valueOf("#fffdfd"));
        searchButton.getStyleClass().add("button");
        searchButton.setOnAction(e -> {
            secondPane.getChildren().clear();
            opField.setText("Search");
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setLayoutX(1276);
        logoutButton.setLayoutY(674);
        logoutButton.setCursor(Cursor.HAND);
        logoutButton.setPrefSize(75, 25);
        logoutButton.setTextFill(Paint.valueOf("#fffdfd"));
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setOnAction(e -> {
            LoginPage.PrintLoginPage();
            menuPageStage.close();
        });


        buttonBar.getButtons().addAll(createButton, deleteButton, modifyButton, searchButton);

        rootPane.getChildren().addAll(op, opField, icon, buttonBar, secondPane, logoutButton);
        Scene scene = new Scene(rootPane, 1365, 713);
        scene.getStylesheets().add(Util.style);
        scene.setFill(Color.web("#491e54"));

        menuPageStage.setScene(scene);
        menuPageStage.getIcons().add(Util.icon);
        menuPageStage.setMaximized(true);
        menuPageStage.setTitle(Util.name);
        menuPageStage.show();
    }
}
