package com.abc.thread.mutex;

public interface Mutex {
    void lock(); // blocks until lock is available
    void unlock(); // non-blocking
}
