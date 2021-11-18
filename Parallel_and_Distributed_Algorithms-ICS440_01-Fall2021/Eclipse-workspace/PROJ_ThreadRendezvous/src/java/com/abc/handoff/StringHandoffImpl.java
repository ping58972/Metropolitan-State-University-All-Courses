package com.abc.handoff;

import com.abc.pp.stringhandoff.*;
import com.programix.thread.*;

public class StringHandoffImpl implements StringHandoff {
    private volatile String message;
    public StringHandoffImpl() {
    }

    @Override
    public synchronized void pass(String msg, long msTimeout)
        throws InterruptedException, TimedOutException, ShutdownException, IllegalStateException {
        message = msg;
        this.notifyAll();
        waitUntilPickup(msTimeout);

    }

    @Override
    public synchronized void pass(String msg) throws InterruptedException, ShutdownException, IllegalStateException {
        pass(msg, 0L);
    }

    @Override
    public synchronized String receive(long msTimeout)
        throws InterruptedException, TimedOutException, ShutdownException, IllegalStateException {
        waitUntilPass(msTimeout);
        String tempStr = message;
        message = null;
        this.notifyAll();
        return tempStr;

    }

    @Override
    public synchronized String receive() throws InterruptedException, ShutdownException, IllegalStateException {

        return receive(0L);
    }

    @Override
    public synchronized void shutdown() {
        Thread.currentThread().interrupt();
        // throw new ShutdownException();
    }

    @Override
    public Object getLockObject() {
        return this;
    }

    private synchronized boolean waitUntilPickup(long msTimeout) throws InterruptedException {
        if (message == null)
            return true;
        if (msTimeout == 0L) {
            while (message != null) {
                wait();
            }
            return true;
        }
        long endTime = System.currentTimeMillis() + msTimeout;
        long msRemaining = msTimeout;
        while (msRemaining > 0L) {
            wait(msRemaining);
            if (message != null)
                return true;
            msRemaining = endTime - System.currentTimeMillis();
        }
        return false;
    }

    private synchronized void waitUntilPickup() throws InterruptedException {
        waitUntilPickup(0L);
    }

    private synchronized void waitUntilPass() throws InterruptedException {
        waitUntilPass(0L);
    }

    private synchronized boolean waitUntilPass(long msTimeout) throws InterruptedException {
        if (message != null)
            return true;
        if (msTimeout == 0L) {
            while (message == null) {
                wait();
            }
            return true;
        }
        long endTime = System.currentTimeMillis() + msTimeout;
        long msRemaining = msTimeout;
        while (msRemaining > 0L) {
            wait(msRemaining);
            if (message == null)
                return true;
            msRemaining = endTime - System.currentTimeMillis();
        }
        return false;
    }

}