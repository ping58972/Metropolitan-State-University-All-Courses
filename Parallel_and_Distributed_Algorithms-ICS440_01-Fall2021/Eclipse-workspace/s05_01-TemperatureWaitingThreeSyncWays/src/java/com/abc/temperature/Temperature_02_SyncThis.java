package com.abc.temperature;

public class Temperature_02_SyncThis {
    private double degrees;

    public Temperature_02_SyncThis() {
        degrees = 0.0;
    }

    public double getDegrees() {
        synchronized (this) {
            return degrees;
        }
    }

    public void setDegrees(double newDegrees) {
        synchronized (this) {
            if (degrees != newDegrees) {
                degrees = newDegrees;
                notifyAll(); // same as "this.notifyAll()", but we don't write "this."
            }
        }
    }


    public void waitUntilDegreesAtLeast(double min) throws InterruptedException {
        synchronized (this) {
            while (degrees < min) {
                wait(); // same as "this.wait()", but we don't write "this."
            }
        }
    }
}
