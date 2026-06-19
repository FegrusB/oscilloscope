package com.example.oscilloscope.signalService;

import java.util.concurrent.atomic.AtomicReference;

public class SineWave implements ISignal {

    private final AtomicReference<Double> signalValue = new AtomicReference<>(0.0);

    private final long startTime = System.currentTimeMillis();

    private final double angularFrequency;

    Thread signalThread;

    public SineWave(double amplitude, double frequency, double updates) {

        this.angularFrequency = 2 * (Math.PI * frequency);
        long wait = (long) (1000 / updates);

        signalThread = new Thread(() -> {
            signalValue.set(0.0);
            while (true) {

                signalValue.set( Math.sin(angularFrequency * ( (double) (System.currentTimeMillis() - startTime) / 1000)) * amplitude );

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
