package com.abc.thread.mutex.comparesandswap;

import com.abc.thread.mutex.testandset.*;

public class CompareAndSwapUsingTestAndSet {
    private final TestAndSet tas = new TestAndSet();
    private long value;

    public CompareAndSwapUsingTestAndSet(long initialValue) {
        value = initialValue;
    }

    public boolean compareAndSwap(long oldValue, long newValue) {
        while (tas.testAndSet() == 1);
        try {
            if (oldValue == value) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        } finally {
            tas.reset();
        }
    }
}
