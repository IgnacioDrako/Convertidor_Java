package com.example;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.Funciones;

public class Ventana extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Converter");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "TXT to XML",
                "XML to TXT",
                "XML to JSON",
                "JSON to XML",
                "TXT to JSON",
                "JSON to TXT"
        );

        Button inputFileButton = new Button("Select Input File");
        Button outputFileButton = new Button("Select Output File");
        Button convertButton = new Button("Convert");

        Label inputFileLabel = new Label("No file selected");
        Label outputFileLabel = new Label("No file selected");

        FileChooser fileChooser = new FileChooser();
        File[] inputFile = new File[1];
        File[] outputFile = new File[1];

        inputFileButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                inputFile[0] = selectedFile;
                inputFileLabel.setText(selectedFile.getAbsolutePath());
            }
        });

        outputFileButton.setOnAction(e -> {
            File selectedFile = fileChooser.showSaveDialog(primaryStage);
            if (selectedFile != null) {
                outputFile[0] = selectedFile;
                outputFileLabel.setText(selectedFile.getAbsolutePath());
            }
        });

        convertButton.setOnAction(e -> {
            if (inputFile[0] != null && outputFile[0] != null && comboBox.getValue() != null) {
                try {
                    switch (comboBox.getValue()) {
                        case "TXT to XML":
                            Funciones.txtToXml(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                        case "XML to TXT":
                            Funciones.xmlToTxt(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                        case "XML to JSON":
                            Funciones.xmlToJson(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                        case "JSON to XML":
                            Funciones.jsonToXml(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                        case "TXT to JSON":
                            Funciones.txtToJson(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                        case "JSON to TXT":
                            Funciones.jsonToTxt(inputFile[0].getAbsolutePath(), outputFile[0].getAbsolutePath());
                            break;
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Conversion successful!");
                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error during conversion: " + ex.getMessage());
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select input file, output file, and conversion type.");
                alert.showAndWait();
            }
        });

        VBox vbox = new VBox(10, comboBox, inputFileButton, inputFileLabel, outputFileButton, outputFileLabel, convertButton);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
