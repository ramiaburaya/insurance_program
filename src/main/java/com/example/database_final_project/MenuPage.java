package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
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
    public static void PrintMenuPage() {
        Stage menuPageStage = new Stage();

        AnchorPane rootPane = new AnchorPane();
        rootPane.setBackground(new Background(new BackgroundFill(Color.web("#BFACE2"), CornerRadii.EMPTY, Insets.EMPTY)));
        rootPane.setPadding(new Insets(10));

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(5));
        buttonBar.setPrefSize(500, 40);
        buttonBar.setButtonMinWidth(70);
        buttonBar.setLayoutX(433);
        buttonBar.setLayoutY(29);

        ImageView icon = new ImageView();
        icon.setFitHeight(70);
        icon.setFitWidth(267);
        icon.setLayoutX(1084);
        icon.setLayoutY(14);
        icon.setStyle("-fx-opacity: 0.7;");
        icon.setImage(new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm()));

        Button createButton = new Button("Create Insurance");
        createButton.setCursor(Cursor.HAND);
        createButton.setTextFill(Paint.valueOf("#fffdfd"));
        createButton.getStyleClass().add("button");
        createButton.setOnAction(e -> {

        });

        Button deleteButton = new Button("Delete Insurance");
        deleteButton.setCursor(Cursor.HAND);
        deleteButton.setTextFill(Paint.valueOf("#fffdfd"));
        deleteButton.getStyleClass().add("button");
        deleteButton.setOnAction(e -> {

        });

        Button modifyButton = new Button("Modify Insurance");
        modifyButton.setCursor(Cursor.HAND);
        modifyButton.setTextFill(Paint.valueOf("#fffdfd"));
        modifyButton.getStyleClass().add("button");
        modifyButton.setOnAction(e -> {

        });

        Button searchButton = new Button("Search");
        searchButton.setCursor(Cursor.HAND);
        searchButton.setTextFill(Paint.valueOf("#fffdfd"));
        searchButton.getStyleClass().add("button");
        searchButton.setOnAction(e -> {

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

        rootPane.getChildren().addAll(icon, buttonBar, logoutButton);
        Scene scene = new Scene(rootPane, 1365, 713);
        scene.getStylesheets().add(Objects.requireNonNull(LoginPage.class.getResource("style.css")).toExternalForm());
        scene.setFill(Color.web("#491e54"));

        menuPageStage.setScene(scene);
        menuPageStage.getIcons().add(new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm()));
        menuPageStage.setMaximized(true);
        menuPageStage.setTitle("Tamkeen");
        menuPageStage.show();
    }
}
