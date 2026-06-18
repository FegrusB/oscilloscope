package com.example.oscilloscope.signalService;

import java.util.concurrent.atomic.AtomicReference;

public class Signal {

    public AtomicReference<Double> signalValue = new AtomicReference<>(0.0);

    Thread signalThread;

    public Signal(double amplitude, double frequency) {
        long wait = (long) (1000 / frequency);
        signalThread = new Thread(() -> {
            signalValue.set(amplitude);
            while (true) {
                signalValue.set(signalValue.get() * -1);
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        signalThread.start();
    }

}
