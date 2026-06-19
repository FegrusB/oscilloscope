package com.example.oscilloscope.signalService;

import java.util.ArrayList;

public class SignalService {

    private ArrayList<ISignal> signals = new ArrayList<>();

    public void startSignal(double amplitude, double frequency) {
        signals.add(new SquareWave(amplitude, frequency));
    }

    public ISignal getSignal(int index) {
        return signals.get(index);
    }
}
