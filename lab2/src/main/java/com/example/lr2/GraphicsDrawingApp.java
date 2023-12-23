package com.example.lr2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicsDrawingApp extends Application {
    private final int CANVAS_WIDTH = 400;
    private final int CANVAS_HEIGHT = 400;

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ComboBox<String> primitiveComboBox;

    private double drawX;
    private double drawY;

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        primitiveComboBox = new ComboBox<>();
        primitiveComboBox.getItems().addAll("Прямоугольник", "Круг", "Линия");
        primitiveComboBox.setValue("Прямоугольник");
        drawPrimitive();
        primitiveComboBox.setOnAction(e -> drawPrimitive());

        canvas.setOnMouseClicked(this::handleCanvasClick);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setTop(primitiveComboBox);
        BorderPane.setMargin(primitiveComboBox, new Insets(5));

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 30);
        primaryStage.setTitle("Рисование");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawPrimitive() {
        graphicsContext.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        switch (primitiveComboBox.getValue()) {
            case "Прямоугольник":
                graphicsContext.setFill(Color.BLACK);
                graphicsContext.fillRect(drawX+10, drawY+10, 100, 80);
                break;
            case "Круг":
                graphicsContext.setFill(Color.RED);
                graphicsContext.fillOval(drawX, drawY, 120, 120);
                break;
            case "Линия":
                graphicsContext.setStroke(Color.BLUE);
                graphicsContext.setLineWidth(2);
                graphicsContext.strokeLine(drawX, drawY, drawX + 100, drawY + 100);
                break;
        }
    }

    private void handleCanvasClick(MouseEvent event) {
        drawX = event.getX();
        drawY = event.getY();
        drawPrimitive();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
