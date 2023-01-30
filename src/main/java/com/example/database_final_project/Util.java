package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.util.Objects;


public class Util {
    public static String name = "Tamkeen";
    public static Image icon = new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm());
    public static String style = Objects.requireNonNull(LoginPage.class.getResource("style.css")).toExternalForm();

    public static Label createLabel(String text, double layoutX, double layoutY) {
        Label label = new Label(text);
        label.setPadding(new Insets(5));
        label.setPrefSize(93, 27);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        return label;
    }

    public static TextField createTextField(String promptText, double layoutX, double layoutY) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPadding(new Insets(5));
        textField.setPrefSize(145, 27);
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        return textField;
    }

    public static Button createButton(String text, double layoutX, double layoutY, double width, double height) {
        Button button = new Button(text);
        button.setCursor(Cursor.HAND);
        button.setTextFill(Paint.valueOf("#fffdfd"));
        button.setPadding(new Insets(5));
        button.getStyleClass().add("button");
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(width, height);

        return button;
    }
}
