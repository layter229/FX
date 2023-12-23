package com.example.lr3;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class EditableBarChartExtended extends Application {

    private BarChart<String, Number> barChart;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        barChart = createChart();
        root.setCenter(barChart);

        TextField numSetsField = new TextField();
        Button addDataButton = new Button("Добавить данные");
        addDataButton.setOnAction(e -> showInputDialog());

        Button removeLastSetButton = new Button("Удалить последний набор");
        removeLastSetButton.setOnAction(e -> removeLastSet());

        Button clearChartButton = new Button("Очистить диаграмму");
        clearChartButton.setOnAction(e -> clearChart());

        root.setBottom(numSetsField);
        root.setRight(addDataButton);
        root.setLeft(removeLastSetButton);
        root.setBottom(clearChartButton);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Редактируемая столбчатая диаграмма");
        primaryStage.show();
    }

    private BarChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Редактируемая столбчатая диаграмма");

        return barChart;
    }

    private void showInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Введите данные");
        dialog.setHeaderText(null);
        dialog.setContentText("Введите значение:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(data -> addData(Integer.parseInt(data)));
    }

    private void addData(int newData) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        barChart.getData().add(series);

        // Генерация случайного цвета
        String randomColor = getRandomColor();

        XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(Integer.toString(series.getData().size() + 1), newData);

        // Установка цвета столбца
        if (newDataPoint.getNode() != null) {
            newDataPoint.getNode().setStyle("-fx-bar-fill: " + randomColor + ";");
        }

        series.getData().add(newDataPoint);

        animateDataPoint(newDataPoint);
    }

    private String getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    private void removeLastSet() {
        int numSets = barChart.getData().size();
        if (numSets > 0) {
            int lastIndex = numSets - 1;
            XYChart.Series<String, Number> lastSet = barChart.getData().get(lastIndex);

            for (XYChart.Data<String, Number> dataPoint : lastSet.getData()) {
                animateDataPointRemoval(dataPoint);
            }

            barChart.getData().remove(lastIndex);
        }
    }

    private void clearChart() {
        for (XYChart.Series<String, Number> set : barChart.getData()) {
            for (XYChart.Data<String, Number> dataPoint : set.getData()) {
                animateDataPointRemoval(dataPoint);
            }
        }
        barChart.getData().clear();
    }

    private void animateDataPointRemoval(XYChart.Data<String, Number> dataPoint) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(dataPoint.YValueProperty(), dataPoint.getYValue())),
                new KeyFrame(new Duration(1000), new KeyValue(dataPoint.YValueProperty(), 0))
        );
        timeline.play();
    }

    private void animateDataPoint(XYChart.Data<String, Number> dataPoint) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(dataPoint.YValueProperty(), 0)),
                new KeyFrame(new Duration(1000), new KeyValue(dataPoint.YValueProperty(), dataPoint.getYValue()))
        );
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
