package com.abc.interrupt;

import com.abc.thread.*;

public class InterruptMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadTools.outln("entering main()");
        SelfRunner[] selfRunners = new SelfRunner[] {
            new SelfRunner(Thread.MAX_PRIORITY),
            new SelfRunner(Thread.NORM_PRIORITY),
            new SelfRunner(Thread.MIN_PRIORITY)
        };

        ThreadTools.outln("constructed self running obj, now sleeping for 5 seconds...");
        Thread.sleep(10_000L);

        ThreadTools.outln("about to call interruptInternalThread()...");
        for (SelfRunner selfRunner : selfRunners) {
            selfRunner.interruptInternalThread();
        }
    }
}
