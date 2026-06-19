package com.example.oscilloscope.signalService;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CompositeSignal implements ISignal {


    private final AtomicReference<Double> signalValue = new AtomicReference<>(0.0);
    private final ArrayList<ISignal> signals;
    Thread signalThread;

    public CompositeSignal(ArrayList<ISignal> signals, double frequency) {

        this.signals = new ArrayList<>(signals);

        long wait = (long) (1000 / frequency);
        signalThread = new Thread(() -> {
            signalValue.set(getAmplitude());
            while (true) {
                signalValue.set(getAmplitude());
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        signalThread.setDaemon(true);
        signalThread.start();
    }

    private double getAmplitude() {
        double amplitude = 0.0;
        for (ISignal signal : signals) {amplitude += signal.getSignal();}
        return amplitude/signals.size();
    }


    @Override
    public Double getSignal() {
        return signalValue.get();
    }
}
