package com.example.oscilloscope.signalService;

import java.util.ArrayList;

public class SignalService {

    private ArrayList<ISignal> signals = new ArrayList<>();

    public void startSignal(double amplitude, double frequency) {
        signals.add(new SquareWave(amplitude, frequency));
    }

    public void startSignal(double amplitude, double frequency, double updates) {
        signals.add(new SineWave(amplitude, frequency, updates, 0));
    }

    public void startTest() {

        ArrayList<ISignal> signalsComp = new ArrayList<>();

        signalsComp.add(new SineWave(50,0.5,1000.0, 0.0));
        signalsComp.add(new SineWave(0.5,10,1000.0, 500));
        signalsComp.add(new SineWave(0.5,8,1000.0, 500));
        signalsComp.add(new SineWave(0.5,15,1000.0, 500));
        signalsComp.add(new SineWave(0.1,1000,1000.0, 500));
        signalsComp.add(new SineWave(0.1,20,1000.0, 500));
        signalsComp.add(new SineWave(0.8,30,1000.0, 500));
        signalsComp.add(new SineWave(0.9,1,1000.0, 500));


        signals.add(new CompositeSignal(signalsComp, 2000));

    }

    public ISignal getSignal(int index) {
        return signals.get(index);
    }
}
