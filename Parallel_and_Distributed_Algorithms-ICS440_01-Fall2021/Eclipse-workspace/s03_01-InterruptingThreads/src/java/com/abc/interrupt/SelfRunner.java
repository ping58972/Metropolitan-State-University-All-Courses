package com.abc.interrupt;

import com.abc.thread.*;

public class SelfRunner {
    private Thread internalThread;

    public SelfRunner() {
        internalThread = new Thread(() -> runWork());
        internalThread.start();
    }

    private void runWork() {
        try {
            ThreadTools.outln("About to iterate....");
            long numberOfIterations = 50L;
            for (long i = 0; i < numberOfIterations; i++) {

                for (int j = 0; j < 10_000_000; j++) {
                    double cos = Math.cos(Math.PI / i);
                }

                if (Thread.interrupted()) throw new InterruptedException();
            }
            ThreadTools.outln("done");
        } catch (InterruptedException x) {
            // ignore and let thread die
        } finally {
            ThreadTools.outln("leaving and letting the thread die");
        }
    }

    public void interruptInternalThread() {
        ThreadTools.outln("calling interrupt() on internalThread");
        internalThread.interrupt();
        ThreadTools.outln("back from interrupt()");
    }
}
