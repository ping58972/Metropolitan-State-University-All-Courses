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

    public synchronized void waitWhileZero() throws InterruptedException {
        while (value == 0) wait();
    }

    public synchronized void waitUntilZero() throws InterruptedException {
        while (value != 0) wait();
    }
}
