package com.abc.mutex.peterson;

import com.abc.mutex.*;

public class PetersonMutex implements Mutex {
    private volatile boolean flag0;
    private volatile boolean flag1;
    private volatile int victim;

    public PetersonMutex() {
    }

    private void setFlag(int flagNumber, boolean state) {
        if (flagNumber == 0) {
            flag0 = state;
        } else if (flagNumber == 1) {
            flag1 = state;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean isFlagUp(int flagNumber) {
        if (flagNumber == 0) {
            return flag0;
        } else if (flagNumber == 1) {
            return flag1;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void lock() {
        int i = ThreadId.SINGLETON.getId();
        int j = 1 - i;

        setFlag(i, true);
        victim = i;

        while (isFlagUp(j) && victim == i); // spin (busy wait)
    }

    @Override
    public void unlock() {
        int i = ThreadId.SINGLETON.getId();
        setFlag(i, false);
    }
}
