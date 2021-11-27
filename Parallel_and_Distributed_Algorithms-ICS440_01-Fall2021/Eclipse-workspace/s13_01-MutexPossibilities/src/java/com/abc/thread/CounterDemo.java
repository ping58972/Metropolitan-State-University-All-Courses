package com.abc.thread;

import com.abc.thread.mutex.*;
import com.abc.thread.mutex.comparesandswap.*;

public class CounterDemo {
    @SuppressWarnings("unused")
    public static void demoWithMutex(Mutex mutex) {
        ThreadTools.outln("mutex implementation is: %s", mutex.getClass().getSimpleName());
        Counter counter = new Counter(1, mutex);

        // NOTE: can only use two threads for Peterson solution
        new SlowIncrementer(0, counter, 1_000L);
        new SlowIncrementer(1, counter, 1_500L);
        new SlowIncrementer(2, counter, 4_000L);
    }

    public static void main(String[] args) {
//        demoWithMutex(new SyncCheatMutex());
//        demoWithMutex(new PetersonMutex());
//        demoWithMutex(new TestAndSetMutex());
//        demoWithMutex(new CompareAndSwapUsingTASReentrantMutex());
        demoWithMutex(new CompareAndSwapReentrantMutex());
    }

    private static class SlowIncrementer {
        private final int threadId;
        private final Counter counter;
        private final long msDelay;

        private volatile boolean keepRunning;
        private final Thread internalThread;

        public SlowIncrementer(int threadId, Counter counter, long msDelay) {
            this.threadId = threadId;
            this.counter = counter;
            this.msDelay = msDelay;

            keepRunning = true;
            internalThread = new Thread(() -> runWork());
            internalThread.setName("SloInc#" + threadId);
            internalThread.setPriority(8); // pretty high
            internalThread.start();
        }

        private void runWork() {
            ThreadTools.CUSTOM_THREAD_ID.set(threadId);

            ThreadTools.outln("entering runWork, napping for %,d ms", msDelay);
            ThreadTools.nap(msDelay);

            ThreadTools.outln("about to call getAndIncrementSlowly...");
            long newCount = counter.getAndIncrementCountSlowly(2_000);
            ThreadTools.outln("back from getAndIncrementCountSlowly, newCount=%d", newCount);
        }

        @SuppressWarnings("unused")
        public void stopRequest() {
            keepRunning = false;
            internalThread.interrupt();
        }
    }  // type SlowIncrementer
}
