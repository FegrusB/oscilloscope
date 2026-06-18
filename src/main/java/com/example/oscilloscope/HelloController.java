package com.example.oscilloscope;

import com.example.oscilloscope.signalService.SignalService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    private SignalService signalService;
    private double signalValue;

    public HelloController() {
        signalService = new SignalService();
        signalService.startSignal(1,0.5);
    }

    @FXML
    private Label welcomeText = new Label();



    @FXML
    protected void onHelloButtonClick() {
        signalValue = signalService.getSignal(0).signalValue.get();
        welcomeText.setText(String.valueOf(signalValue));
    }
}