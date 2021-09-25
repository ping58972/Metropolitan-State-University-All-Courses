package com.abc.foo;

public class Bar {
    private static long nextSerialNumber = 1_000_001L;

    private final long serialNumber;

    public Bar() {
        serialNumber = getNextSerialNumber();
    }

    private static synchronized long getNextSerialNumber() {
        return nextSerialNumber++;
    }

    @Override
    public String toString() {
        return "Bar[serialNumber=" + serialNumber + "]";
    }
}
