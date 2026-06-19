package com.example.oscilloscope;

import com.example.oscilloscope.signalService.SignalService;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicReference;

public class HelloController {

    private SignalService signalService;
    private double signalValue;
    private static final int MAX_POINTS = 200;
    private long startTime = 0;

    private final AtomicReference<XYChart.Data<Number,Number> > latestValue = new AtomicReference<>();

    @FXML private LineChart<Number,Number> scopeView;
    @FXML private final XYChart.Series<Number,Number> signalSeries = new XYChart.Series<>();
    @FXML private NumberAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private TextField frequency;

    @FXML
    private void initialize() {
        signalService = new SignalService();
        //signalService.startSignal(1,0.5,1000.0);
        signalService.startTest();
        startProbe();

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

    private void startProbe(){

        long wait = (long) (1000 / Double.parseDouble(frequency.getText()));

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

            double x = (double) (System.currentTimeMillis() - startTime) / 1000;
            double y = signalService.getSignal(0).getSignal();

            latestValue.set( new XYChart.Data<>(x, y));
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    protected void onHelloButtonClick() {
       new AnimationTimer() {
           @Override
           public void handle(long now) {
               signalSeries.getData().add(latestValue.get());
           }
       }.start();
    }
}