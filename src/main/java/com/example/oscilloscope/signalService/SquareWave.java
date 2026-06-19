package com.example.oscilloscope.signalService;

import java.util.concurrent.atomic.AtomicReference;

public class SquareWave implements ISignal {

    private final AtomicReference<Double> signalValue = new AtomicReference<>(0.0);

    Thread signalThread;

    public SquareWave(double amplitude, double frequency) {
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
        signalThread.setDaemon(true);
        signalThread.start();
    }

    public Double getSignal() {
        return signalValue.get();
    }

}
