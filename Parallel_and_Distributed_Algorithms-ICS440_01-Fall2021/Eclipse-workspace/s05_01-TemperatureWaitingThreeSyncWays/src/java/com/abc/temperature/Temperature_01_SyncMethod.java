package com.abc.temperature;

public class Temperature_01_SyncMethod {
    private double degrees;

    public Temperature_01_SyncMethod() {
        degrees = 0.0;
    }

    public synchronized double getDegrees() {
        return degrees;
    }

    public synchronized void setDegrees(double newDegrees) {
        if (degrees != newDegrees) {
            degrees = newDegrees;
            notifyAll();
        }
    }

    public synchronized void waitUntilDegreesAtLeast(double min) throws InterruptedException {
        while (degrees < min) {
            wait();
        }
    }
}
