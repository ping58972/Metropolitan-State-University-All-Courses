package com.abc.thread.mutex.testandset;

import java.util.concurrent.atomic.*;

public class TestAndSet {
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    /** Always sets the value to 1, but returns the previous value */
    public int testAndSet() {
        boolean prev = atomicBoolean.getAndSet(true);
        return prev ? 1 : 0;
    }

    public void reset() {
        atomicBoolean.set(false);
    }
}
