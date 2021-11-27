package com.abc.thread;

public class ThreadTools {
    private static final NanoTimer timer = NanoTimer.createStarted();

    // no instances
    private ThreadTools() {
    }

    public static synchronized void outln(String fmt, Object... args) {
        String msg = String.format(fmt, args);
        System.out.printf("%10.5f|%-20.20s|%s%n", timer.getElapsedSeconds(), Thread.currentThread().getName(), msg);
    }
}
