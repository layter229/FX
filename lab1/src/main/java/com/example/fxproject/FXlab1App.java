package com.example.fxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FXlab1App extends Application {

    public void start(Stage primaryStage) {
        Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXlab1.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        Scene scene = new Scene(root, 610, 500);
        primaryStage.setTitle("JavaFXlab1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}