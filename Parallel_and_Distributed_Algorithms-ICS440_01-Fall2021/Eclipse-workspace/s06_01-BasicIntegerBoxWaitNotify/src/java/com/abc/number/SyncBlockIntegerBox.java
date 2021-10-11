package com.abc.number;

public class SyncBlockIntegerBox {
    private int value;
    private final Object lockObject;

    public SyncBlockIntegerBox(int initialValue,
                               Object proposedLockObject) {
        value = initialValue;
        lockObject = proposedLockObject != null ? proposedLockObject : new Object();
    }

    public SyncBlockIntegerBox() {
        this(0, null);
    }

    public Object getLockObject() {
        return lockObject;
    }

    public int getValue() {
        synchronized (lockObject) {
            return value;
        }
    }

    public void setValue(int value) {
        synchronized (lockObject) {
            if (value != this.value) {
                this.value = value;
                lockObject.notifyAll();
            }
        }
    }

    public void waitWhileZero() throws InterruptedException {
        synchronized (lockObject) {
            while (value == 0) lockObject.wait();
        }
    }

    public void waitUntilZero() throws InterruptedException {
        synchronized (lockObject) {
            while (value != 0) lockObject.wait();
        }
    }
}
