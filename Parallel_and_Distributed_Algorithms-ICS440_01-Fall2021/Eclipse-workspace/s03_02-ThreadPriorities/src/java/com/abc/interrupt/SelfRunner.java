package com.abc.interrupt;

import com.abc.thread.*;

public class SelfRunner {
    private Thread internalThread;

    public SelfRunner(int threadPriority) {
        internalThread = new Thread(() -> runWork());
        internalThread.setPriority(threadPriority);
        internalThread.start();
    }

    private void runWork() {
        long iterationsCompleted = 0;
        try {
            ThreadTools.outln("About to iterate....");
            long numberOfIterations = 15_000_000L;
            for (long i = 0; i < numberOfIterations; i++) {

                for (int j = 0; j < 100; j++) {
                    double cos = Math.cos(Math.PI / i);
                    iterationsCompleted++;
                }

                if (Thread.interrupted()) throw new InterruptedException();
            }
            ThreadTools.outln("done");
        } catch (InterruptedException x) {
            // ignore and let thread die
        } finally {
            ThreadTools.outln("leaving and letting the thread die after %,d iterations, threadPriority=%d",
                iterationsCompleted, Thread.currentThread().getPriority());
        }
    }

    public void interruptInternalThread() {
        ThreadTools.outln("calling interrupt() on internalThread");
        internalThread.interrupt();
        ThreadTools.outln("back from interrupt()");
    }
}
