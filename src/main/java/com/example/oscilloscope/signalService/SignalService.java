package com.example.oscilloscope.signalService;

import java.util.ArrayList;

public class SignalService {

    private ArrayList<Signal> signals = new ArrayList<>();

    public void startSignal(double amplitude, double frequency) {
        signals.add(new Signal(amplitude, frequency));
    }

    public Signal getSignal(int index) {
        return signals.get(index);
    }
}
