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

    public boolean waitWhileZero(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (value != 0) return true; // condition has been met

            if (msTimeout == 0L) {
                while (value == 0) lockObject.wait();
                return true; // condition has been met
            } else {
                long msEndTime = System.currentTimeMillis() + msTimeout;
                long msRemaining = msTimeout;
                while (msRemaining >= 1L) {
                    lockObject.wait(msRemaining);
                    if (value != 0) return true; // condition has been met
                    msRemaining = msEndTime - System.currentTimeMillis();
                }
                return false; // timed out
            }
        }
    }

    public void waitWhileZero() throws InterruptedException {
        waitWhileZero(0L);
    }

    public boolean waitUntilZero(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (value == 0) return true; // condition has been met

            if (msTimeout == 0L) {
                while (value != 0) lockObject.wait();
                return true; // condition has been met
            } else {
                long msEndTime = System.currentTimeMillis() + msTimeout;
                long msRemaining = msTimeout;
                while (msRemaining >= 1L) {
                    lockObject.wait(msRemaining);
                    if (value == 0) return true; // condition has been met
                    msRemaining = msEndTime - System.currentTimeMillis();
                }
                return false; // timed out
            }
        }
    }

    public void waitUntilZero() throws InterruptedException {
        waitUntilZero(0L);
    }
}
