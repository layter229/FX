package com.example.fxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXlab1Controller {
    @FXML
    private void handleButtonOnPress(ActionEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private TextField textField;

    @FXML
    private void handleMouseEnter(MouseEvent event) {
        textField.setText(event.getSource().toString());
    }

    @FXML
    private TextArea textArea;

    @FXML
    private void handleMouseClick(MouseEvent event) {
        textArea.appendText("Java");
    }

    @FXML
    private void handleRadioButton(ActionEvent event) {
        RadioButton selRadio = (RadioButton)event.getSource();
        selRadio.getScene().getStylesheets().clear();
        selRadio.getScene().getRoot().getStylesheets().clear();

        switch (selRadio.getText()) {
            case "жёлтый":
                selRadio.getScene().getStylesheets().add(getClass().getResource("FXMLWindow1.css").toExternalForm());
            break;
            case "фиолетовый":
                selRadio.getScene().getStylesheets().add(getClass().getResource("FXMLWindow2.css").toExternalForm());
            break;
            case "салатовый":
                selRadio.getScene().getStylesheets().add(getClass().getResource("FXMLWindow3.css").toExternalForm());
            break;
        }
    }
}