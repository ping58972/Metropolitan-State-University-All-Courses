package com.abc.interrupt;

import com.abc.thread.*;

public class InterruptMain {
    public static void main(String[] args) throws InterruptedException {
        ThreadTools.outln("entering main()");
        SelfRunner sr = new SelfRunner();

        ThreadTools.outln("constructed self running obj, now sleeping for 5 seconds...");
        Thread.sleep(5_000L);

        ThreadTools.outln("about to call interruptInternalThread()...");
        sr.interruptInternalThread();
    }
}
