package com.abc.thread.mutex.synccheat;

import com.abc.thread.*;
import com.abc.thread.mutex.*;

public class SyncCheatMutex implements Mutex {
    // ISSUES:
    //   a) ANY thread can call unlock() and mistakenly steal it from the owner
    //   b) a thread can deadlock with itself if it calls lock() while being
    //      the thread that owns the lock:
    //            ---> non-reentrant mutex

    private boolean locked;

    public SyncCheatMutex() {
        locked = false;
    }

    @Override
    public void lock() {
        // SPIN in this while loop, waiting for lock to be available
        long spinCount = 0;
        NanoTimer timer = NanoTimer.createStarted();
        int customThreadId = ThreadTools.CUSTOM_THREAD_ID.get();
        while (true) {
            synchronized (this) {
                if (!locked) {
                    locked = true; // lock is available, grab it!
                    ThreadTools.outln("assigned lock to: %d, after spinning %,d times for %.7f seconds",
                        customThreadId, spinCount, timer.getElapsedSeconds());
                    break;
                }
            }
            spinCount++;
        }
    }

    @Override
    public synchronized void unlock() {
        locked = false;
        ThreadTools.outln("lock released by: " + ThreadTools.CUSTOM_THREAD_ID.get());
    }
}
