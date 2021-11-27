package com.abc.thread.mutex.peterson;

import com.abc.thread.*;
import com.abc.thread.mutex.*;

/**
 * Only works for thread threads with ID's "0" and "1".
 */
public class PetersonMutex implements Mutex {
    // ISSUES:
    //   a) a thread can deadlock with itself if it calls lock() while being
    //      the thread that owns the lock:
    //            ---> non-reentrant mutex

    private volatile boolean flag0;
    private volatile boolean flag1;
    private volatile int victim;

    public PetersonMutex() {
        flag0 = false;
        flag1 = false;
        victim = -1;
    }

    @Override
    public void lock() {
        int i = ThreadTools.CUSTOM_THREAD_ID.get(); // me
        int j = 1 - i;                              // other thread

        if (i > 1 || j > 1) throw new IllegalStateException("i=" + i + ", j=" + j + " but both must only ever be 0 or 1");

        if (i == 0) {
            flag0 = true;
        } else { // it's 1
            flag1 = true;
        }
        victim = i; // I offer to be the victim

        // SPIN in this while loop, waiting for lock to be available
        long spinCount = 0;
        NanoTimer timer = NanoTimer.createStarted();
        while ((j == 0 ? flag0 : flag1) && (victim == i)) spinCount++; // spin on CPU!
        ThreadTools.outln("assigned lock to: %d, after spinning %,d times for %.7f seconds",
            i, spinCount, timer.getElapsedSeconds());
    }

    @Override
    public void unlock() {
        int i = ThreadTools.CUSTOM_THREAD_ID.get(); // me
        if (i == 0) {
            flag0 = false;
        } else { // it's 1
            flag1 = false;
        }
        ThreadTools.outln("lock released by: " + i);
    }
}
