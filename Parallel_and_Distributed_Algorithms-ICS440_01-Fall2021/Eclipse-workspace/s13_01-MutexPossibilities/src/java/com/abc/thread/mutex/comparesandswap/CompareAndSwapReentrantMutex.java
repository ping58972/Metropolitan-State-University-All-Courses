package com.abc.thread.mutex.comparesandswap;

import java.util.concurrent.atomic.*;

import com.abc.thread.*;
import com.abc.thread.mutex.*;

// No issues!
// - no longer the Honor System for unlock! Only thread holding the lock changes anything
// - no deadlocking with ourself! This is a reentrant mutex
// - works like synchronized in Java!
public class CompareAndSwapReentrantMutex implements Mutex {
    private static final long THREAD_ID_NO_ONE = 0;

    private final AtomicLong atomicLong;
    private int lockDepth;

    public CompareAndSwapReentrantMutex() {
        atomicLong = new AtomicLong(THREAD_ID_NO_ONE);
        lockDepth = 0;
    }

    @Override
    public void lock() {
        long threadId = Thread.currentThread().getId();

        if (atomicLong.compareAndSet(threadId, threadId)) {
            // calling thread already has the lock, just increase the depth
            lockDepth++ ;
            return;
        }

        long spinCount = 0;
        NanoTimer timer = NanoTimer.createStarted();
        while ( !atomicLong.compareAndSet(THREAD_ID_NO_ONE, threadId)) spinCount++; // spin
        ThreadTools.outln("assigned lock to: %s, after spinning %,d times for %.7f seconds",
            Thread.currentThread().getName(), spinCount, timer.getElapsedSeconds());

        // just changed from NO_ONE to calling thread!!!
        lockDepth = 1;
    }

    @Override
    public void unlock() {
        long threadId = Thread.currentThread().getId();
        if (atomicLong.compareAndSet(threadId, threadId)) {
            // the calling thread *IS* the current holder
            lockDepth--;
            if (lockDepth == 0) {
                atomicLong.compareAndSet(threadId, THREAD_ID_NO_ONE);
                ThreadTools.outln("lock released by: " + Thread.currentThread().getName());
            }
        }
    }
}
