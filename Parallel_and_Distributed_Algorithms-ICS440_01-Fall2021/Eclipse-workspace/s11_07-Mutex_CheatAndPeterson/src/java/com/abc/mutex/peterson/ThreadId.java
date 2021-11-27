package com.abc.mutex.peterson;

import java.util.*;

public class ThreadId {
    public static final ThreadId SINGLETON = createSingleton();

    private final Map<Thread, Integer> threadIdLookup = new WeakHashMap<>();

    private ThreadId() {
    }

    private static synchronized ThreadId createSingleton() {
        return new ThreadId();
    }

    /** Only ever returns 0 or 1 */
    public synchronized int getId() {
        Integer id = threadIdLookup.get(Thread.currentThread());
        if (id == null) {
            if (threadIdLookup.size() >= 2) throw new IllegalStateException("only two threads are supported!");

            id = threadIdLookup.size();
            threadIdLookup.put(Thread.currentThread(), id);
        }
        return id;
    }
}
