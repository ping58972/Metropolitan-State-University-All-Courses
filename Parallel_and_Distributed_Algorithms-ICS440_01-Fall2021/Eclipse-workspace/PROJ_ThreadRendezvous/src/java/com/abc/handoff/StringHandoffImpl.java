package com.abc.handoff;

/*
 * By Nalongsone Danddank. ICS 440-01, Metropolitan State University. Final Project: String Hand
 * off, Thread Rendezvous.
 *
 */
import com.abc.pp.stringhandoff.*;
import com.programix.thread.*;

/**
 * Implement String Hand Off, how to pass and receive a String message.
 */
public class StringHandoffImpl implements StringHandoff {
    private String message; // for pass and receive

    // for keeping track if the instance has already initiated its passer and receive.
    private boolean isPassInitiated;
    private boolean isReceiveInitiated;
    /////////////////////////////////
    // for tracking, if they are ready to interact with its counterpart.
    private boolean isPassReady;
    private boolean isReceiveReady;
    //////////////////////////////
    // if the message has set, it safe to read.
    private boolean hasMessageSet;
    /////////////////////////
    // if has received the message. it safe reset.
    private boolean hasMessageReceived;
    /////////////////////////////
    // if want to shutdown, set to true
    private boolean isShutdown;

    public StringHandoffImpl() {
        message = null;
        isPassInitiated = false;
        isReceiveInitiated = false;
        isPassReady = false;
        isReceiveReady = false;
        hasMessageSet = false;
        hasMessageReceived = false;
        isShutdown = false;

    }

    @Override
    public synchronized void pass(String msg, long msTimeout)
        throws InterruptedException, TimedOutException, ShutdownException, IllegalStateException {
        // before beginning check for interrupt and shutdown first.
        interrupt_shutdown_check();

        if (isPassInitiated)
            throw new IllegalStateException("A thread Already attempt to pass the message!");
        try {
            // first set the field to true to let receiver know passer is ready.
            isPassInitiated = true;
            isPassReady = true;
            ///////////
            // check receive is ready or not.
            if (isReceiveReady) {
                // then, set message and tell receiver, message set already.
                message = msg;
                hasMessageSet = true;
                ///////////
                // after that, reset this field to false to prevents another thread interrupt and
                /////////// replace from another.
                isReceiveReady = false;
                this.notifyAll();
                // waiting until message has received.
                while ( !hasMessageReceived) {
                    this.wait();
                    // while waiting, checking interrupt and shutdown or not.
                    interrupt_shutdown_check();
                }
            } else if (msTimeout == 0L) {
                // waiting until receive ready.
                while ( !isReceiveReady) {
                    this.wait();
                    // while waiting, check interrupt and shutdown.
                    interrupt_shutdown_check();
                }
                // then, set the value to message and tell receiver that it has set.
                message = msg;
                hasMessageSet = true;
                // then set isReceiveReady to false.
                isReceiveReady = false;
                this.notifyAll();
                // waiting until message has received.
                while ( !hasMessageReceived) {
                    this.wait();
                    // while waiting, check interrupt and shutdown.
                    interrupt_shutdown_check();
                }
            } else {
                // if there have a timeout, make time to wait and add timeout length.
                long endtime = System.currentTimeMillis() + msTimeout;
                long msTimeRemaining = msTimeout;
                // wait until receiver ready and time out.
                while ( !isReceiveReady && msTimeRemaining >= 1L) {
                    this.wait(msTimeRemaining);
                    interrupt_shutdown_check();
                    msTimeRemaining = endtime - System.currentTimeMillis();
                }
                // check receiver ready or not.
                if (isReceiveReady) {
                    // then set the meassage and tell receiver that it ready.
                    message = msg;
                    hasMessageSet = true;
                    // then, reset isReceiveReady field to false to prevents interrupted and
                    // replaced from another.
                    isReceiveReady = false;
                    this.notifyAll();
                    // waiting until message has received.
                    while ( !hasMessageReceived) {
                        this.wait();
                        // while waiting , check interrupt and shutdown or not.
                        interrupt_shutdown_check();
                    }
                } else {
                    throw new TimedOutException("Passed has been timeout before receiver ready!");
                }
            }
        } finally {
            // finally always reset all field to false, even it complete or not.
            message = null;
            isPassReady = false;
            isPassInitiated = false;
            hasMessageSet = false;
            hasMessageReceived = false;
        }

    }

    @Override
    public synchronized void pass(String msg) throws InterruptedException, ShutdownException, IllegalStateException {
        // check interrupts then shutdown before go to pass the message with no timeout.
        interrupt_shutdown_check();
        pass(msg, 0L);
    }

    @Override
    public synchronized String receive(long msTimeout)
        throws InterruptedException, TimedOutException, ShutdownException, IllegalStateException {
        // first of all check interrupt and shutdowns.
        interrupt_shutdown_check();
        // allowing one thread can go to receive at a time.
        if (isReceiveInitiated) {
            throw new IllegalStateException("Already has a thread that attempt to receive!");
        }
        // create a local field to hold the message, temporarily.
        String tempMessage = null;
        try {
            // first set the initiated and ready fields to true. to tell passer that it ready.
            isReceiveInitiated = true;
            isReceiveReady = true;
            // check passer ready or not.
            if (isPassReady) {
                // then tell passer that receiver ready and wait for passer to set message.
                this.notifyAll();
                // waiting until message has set.
                while ( !hasMessageSet) {
                    this.wait();
                    // while waiting, check interrupt and shutdown.
                    interrupt_shutdown_check();
                }
                // reset passReady to prevent interrupted and replace by another.
                isPassReady = false;
                tempMessage = message;
                hasMessageReceived = true;
                this.notifyAll();
            } else if (msTimeout == 0L) {
                // waiting until pass ready.
                while ( !isPassReady) {
                    this.wait();
                    // while waiting, check interrupt and shutdown.
                    interrupt_shutdown_check();
                }
                // reset passReady to prevent interrupted and replace by another.
                isPassReady = false;
                // if message has set notify passer can go to set the message.
                if ( !hasMessageSet) {
                    this.notifyAll();
                }
                // waiting until message has set
                while ( !hasMessageSet) {
                    this.wait();
                    // while waiting, check interrupt and shutdown.
                    interrupt_shutdown_check();
                }
                // then, get the message and tell passer that message has gotten.
                tempMessage = message;
                hasMessageReceived = true;
                this.notifyAll();
            } else {
                // if there have a timeout, make time to wait and add timeout length.
                long endtime = System.currentTimeMillis() + msTimeout;
                long msTimeRemaining = msTimeout;

                // wait until receiver ready and time out.
                while ( !isPassReady && msTimeRemaining >= 1L) {
                    this.wait(msTimeRemaining);
                    interrupt_shutdown_check();
                    msTimeRemaining = endtime - System.currentTimeMillis();
                }
                // check passer ready or not.
                if (isPassReady) {
                    // to prevent receiver replaced by another.
                    isPassReady = false;
                    // check message has set or not. to notify passer.
                    if ( !hasMessageSet) {
                        this.notifyAll();
                    }
                    // waiting until message has set
                    while ( !hasMessageSet) {
                        this.wait();
                        interrupt_shutdown_check();
                    }
                    // then, get message and tell passer to received the message.
                    tempMessage = message;
                    hasMessageReceived = true;
                    this.notifyAll();
                } else {
                    throw new TimedOutException("Receiver has been timeout before passer ready!");
                }
            }
        } finally {
            // finally always reset all field to false, even it complete or not.
            isReceiveInitiated = false;
            isReceiveReady = false;
        }

        return tempMessage;

    }

    @Override
    public synchronized String receive() throws InterruptedException, ShutdownException, IllegalStateException {
        // check interrupts then shutdown before go to receive with no timeout.
        interrupt_shutdown_check();
        return receive(0L);
    }

    @Override
    public synchronized void shutdown() {
        // set the field to true to tell any thread that it shutdown.
        isShutdown = true;
    }

    @Override
    public Object getLockObject() {
        return this;
    }

    // this method for helping check the program interrupt and shutdown.
    private void interrupt_shutdown_check() throws InterruptedException, ShutdownException {
        // check if there has another thread interrupted, then notify and throw the exception.
        if (Thread.currentThread().isInterrupted()) {
            this.notifyAll();
            throw new InterruptedException("The Thread is interrupted!");
        }
        // check the thread shutdown or not, then notify other thread to check itself too.
        if (isShutdown) {
            this.notifyAll();
            throw new ShutdownException("Shtdown called!");
        }
    }

}
