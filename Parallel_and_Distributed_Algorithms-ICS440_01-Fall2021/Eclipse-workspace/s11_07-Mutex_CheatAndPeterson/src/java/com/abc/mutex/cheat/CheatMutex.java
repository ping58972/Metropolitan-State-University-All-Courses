package com.abc.mutex.cheat;

import com.abc.mutex.*;

public class CheatMutex implements Mutex {
    private volatile boolean locked;

    public CheatMutex() {
        locked = false;
    }

    @Override
    public void lock() {
        while (locked); // spin (busy wait)

        locked = true;
    }

    @Override
    public void unlock() {
        locked = false;
    }
}
