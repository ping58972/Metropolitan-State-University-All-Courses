package com.abc.number;

public class IntegerBox {
    private int value;

    public IntegerBox(int initialValue) {
        value = initialValue;
    }

    public IntegerBox() {
        this(0);
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        if (value != this.value) {
            this.value = value;
            notifyAll();
        }
    }

    public synchronized boolean waitWhileZero(long msTimeout) throws InterruptedException {
        if (value != 0) return true; // condition has been met

        if (msTimeout == 0L) {
            while (value == 0) wait();
            return true; // condition has been met
        } else {
            long msEndTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while (msRemaining >= 1L) {
                wait(msRemaining);
                if (value != 0) return true; // condition has been met
                msRemaining = msEndTime - System.currentTimeMillis();
            }
            return false; // timed out
        }
    }

    public synchronized void waitWhileZero() throws InterruptedException {
        waitWhileZero(0L);
    }

    public synchronized boolean waitUntilZero(long msTimeout) throws InterruptedException {
        if (value == 0) return true; // condition has been met

        if (msTimeout == 0L) {
            while (value != 0) wait();
            return true; // condition has been met
        } else {
            long msEndTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while (msRemaining >= 1L) {
                wait(msRemaining);
                if (value == 0) return true; // condition has been met
                msRemaining = msEndTime - System.currentTimeMillis();
            }
            return false; // timed out
        }
    }

    public synchronized void waitUntilZero() throws InterruptedException {
        waitUntilZero(0L);
    }
}
