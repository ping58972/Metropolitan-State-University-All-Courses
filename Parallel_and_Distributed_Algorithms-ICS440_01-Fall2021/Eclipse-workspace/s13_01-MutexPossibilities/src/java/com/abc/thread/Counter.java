package com.abc.thread;

import com.abc.thread.mutex.*;

public class Counter {
    private long count;
    private final Mutex mutex;

    public Counter(long initialCount, Mutex mutex) {

        count = initialCount;
        this.mutex = mutex;
    }

    public long getCount() {
        // lock-try-finally-unlock
        mutex.lock();
        try {
            return count;
        } finally {
            mutex.unlock();
        }
    }

    public void incrementCount() {
        // lock-try-finally-unlock
        mutex.lock();
        try {
            count++ ; // read, add, write
        } finally {
            mutex.unlock();
        }
    }

    public long getAndIncrementCount() {
        // lock-try-finally-unlock
        mutex.lock();
        try {
            long c = count;
            count++ ;
            return c;
        } finally {
            mutex.unlock();
        }
    }

    public long getAndIncrementCountSlowly(long msToRun) {
        // lock-try-finally-unlock
        mutex.lock();
        try {
            long c = getCount();
            ThreadTools.nap(msToRun);
            count++ ;
            return c;
        } finally {
            mutex.unlock();
        }
    }
}
