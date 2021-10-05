package com.abc.temperature;

public class Temperature_03_SyncLockObject {
    private double degrees;
    private final Object lockObject;

    public Temperature_03_SyncLockObject(Object proposedLockObject) {
        lockObject = proposedLockObject != null ? proposedLockObject : new Object();
        degrees = 0.0;
    }

    public double getDegrees() {
        synchronized (lockObject) {
            return degrees;
        }
    }

    public void setDegrees(double newDegrees) {
        synchronized (lockObject) {
            if (degrees != newDegrees) {
                degrees = newDegrees;
                lockObject.notifyAll();
            }
        }
    }

    public void waitUntilDegreesAtLeast(double min) throws InterruptedException {
        synchronized (lockObject) {
            while (degrees < min) {
                lockObject.wait();
            }
        }
    }

    public Object getLockObject() {
        return lockObject;
    }
}
