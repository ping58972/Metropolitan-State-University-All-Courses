package com.abc.thread.mutex.testandset;

import com.abc.thread.*;
import com.abc.thread.mutex.*;

/**
 * Works for ANY number of threads.
 */
public class TestAndSetMutex implements Mutex {
    // ISSUES:
    //   a) ANY thread can call unlock() and mistakenly steal it from the owner
    //   b) a thread can deadlock with itself if it calls lock() while being
    //      the thread that owns the lock:
    //            ---> non-reentrant mutex

    private final TestAndSet tas = new TestAndSet();

    @Override
    public void lock() {
        // SPIN in this while loop, waiting for lock to be available
        long spinCount = 0;
        NanoTimer timer = NanoTimer.createStarted();
        while (tas.testAndSet() == 1) spinCount++;
        ThreadTools.outln("assigned lock to: %s, after spinning %,d times for %.7f seconds",
            Thread.currentThread().getName(), spinCount, timer.getElapsedSeconds());
    }

    @Override
    public void unlock() {
        tas.reset();
        ThreadTools.outln("lock released by: " + Thread.currentThread().getName());
    }
}
