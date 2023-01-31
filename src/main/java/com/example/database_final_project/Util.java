package com.example.database_final_project;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Util {
    public static String name = "Tamkeen";
    public static Image icon = new Image(Objects.requireNonNull(LoginPage.class.getResource("tamkeen.jpg")).toExternalForm());
    public static String style = Objects.requireNonNull(LoginPage.class.getResource("style.css")).toExternalForm();

    public static AnchorPane thirdPane = new AnchorPane();
    public static TextArea stepTextArea = new TextArea();

    public static Alert alert = new Alert(Alert.AlertType.NONE);

    public static void initializeThirdPane() {
        thirdPane.getChildren().clear();
        thirdPane.setLayoutX(27);
        thirdPane.setLayoutY(60);
        thirdPane.setPrefSize(1300, 483);
        thirdPane.getStyleClass().add("pane-2");
    }

    public static void initializeStepTextArea(double width, double height, String text) {
        stepTextArea.setPadding(new Insets(5));
        stepTextArea.setEditable(false);
        stepTextArea.setLayoutX(14);
        stepTextArea.setLayoutY(14);
        stepTextArea.setPrefSize(width, height);
        stepTextArea.setText(text);

    }

    public static Label createLabel(String text, double layoutX, double layoutY, double width, double height) {
        Label label = new Label(text);
        label.setPadding(new Insets(5));
        label.setPrefSize(width, height);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        return label;
    }

    public static ButtonBar createButtonBar(double layoutX, double layoutY, double width, double height, double buttonMinWidth) {
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(5));
        buttonBar.setPrefSize(width, height);
        buttonBar.setButtonMinWidth(buttonMinWidth);
        buttonBar.setLayoutX(layoutX);
        buttonBar.setLayoutY(layoutY);
        return buttonBar;
    }

    public static TextField createTextField(String promptText, double layoutX, double layoutY, double width, double height) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPadding(new Insets(5));
        textField.setPrefSize(width, height);
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

    public static boolean isEmptyField(TextField textField) {
        return textField.getText().equals("");
    }

    public static boolean isValid(TextField textField, int length) {
        return textField.getText().trim().length() == length;
    }

    public static boolean isInt(TextField s) {
        try {
            int Value = Integer.parseInt(s.getText());
            return true;
        } catch (NumberFormatException ignored) {
        }
        return false;
    }

    public static byte[] ReadImage(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        byte[] fileData = new byte[0];
        if (selectedFile != null) {
            fileData = Files.readAllBytes(selectedFile.toPath());
        }
        return fileData;
    }

    public static LocalDate formatterDate(DatePicker dateOfBirthPicker) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        LocalDate date = dateOfBirthPicker.getValue();
        if (date != null) {
            return LocalDate.parse(formatter.format(date));
        }
        return null;
    }

}
