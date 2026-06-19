package com.example.oscilloscope;

import com.example.oscilloscope.signalService.SignalService;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class HelloController {

    private SignalService signalService;
    private double signalValue;
    private static final int MAX_POINTS = 200;
    private long startTime = 0;

    @FXML private LineChart<Number,Number> scopeView;
    @FXML private final XYChart.Series<Number,Number> signalSeries = new XYChart.Series<>();
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML
    private void initialize() {
        signalService = new SignalService();
        signalService.startSignal(1,0.5);

        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);

        xAxis.setAnimated(false);
        xAxis.setAnimated(false);

        scopeView.setAnimated(false);
        scopeView.setCreateSymbols(false);

        signalSeries.setName("signal 1");
        scopeView.getData().add(signalSeries);

        startTime = System.currentTimeMillis();


    }

    private void startScope(){
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                double x = (double) (System.currentTimeMillis() - startTime) /1000;
                double y = signalService.getSignal(0).signalValue.get();

                signalSeries.getData().add(new XYChart.Data<>(x,y));
            }
        }.start();
    }

    @FXML
    protected void onHelloButtonClick() {
        startScope();
    }
}