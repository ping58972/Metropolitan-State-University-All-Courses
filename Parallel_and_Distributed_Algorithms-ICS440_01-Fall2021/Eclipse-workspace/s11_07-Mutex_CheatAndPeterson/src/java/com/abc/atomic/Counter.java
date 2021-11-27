package com.abc.atomic;

import com.abc.mutex.*;

public class Counter {
    private long count;
    private final Mutex mutex;

    public Counter(long initialCount,
                   Mutex mutex) {
        count = initialCount;
        this.mutex = mutex;
    }

    public Counter(Mutex mutex) {
        this(0, mutex);
    }

    public long getCount() {
        mutex.lock();
        try {
            return count;
        } finally {
            mutex.unlock();
        }
    }

    public void incrementCount() {
        mutex.lock();
        try {
            count++;
        } finally {
            mutex.unlock();
        }
    }

    public void decrementCount() {
        mutex.lock();
        try {
            count--;
        } finally {
            mutex.unlock();
        }
    }

    public long getAndIncrement() {
        mutex.lock();
        try {
            long value = count;
            count++;
            return value;
        } finally {
            mutex.unlock();
        }
    }
}
