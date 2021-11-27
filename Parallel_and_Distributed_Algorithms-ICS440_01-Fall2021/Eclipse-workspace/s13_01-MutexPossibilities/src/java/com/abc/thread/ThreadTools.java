package com.abc.thread;

public class ThreadTools {
    public static final long NEVER_TIMEOUT = 0L;

    private static final long nsStartup = System.nanoTime();

    public static final ThreadLocal<Integer> CUSTOM_THREAD_ID = new ThreadLocal<>();

    // no instances
    private ThreadTools() {
    }

    public static void outln(String format, Object...args) {
        commonln(true, format, args);
    }

    public static void errln(String format, Object...args) {
        commonln(false, format, args);
    }

    private static synchronized void commonln(boolean stdOut, String format, Object...args) {
        double secondsElapsed = (System.nanoTime() - nsStartup) / NanoTimer.NS_PER_SECOND;
        String fullMsg = String.format("%10.5f|%-15.15s|%s",
            secondsElapsed, Thread.currentThread().getName(), String.format(format, args));
        if (stdOut) {
            System.out.println(fullMsg);
        } else {
            System.err.println(fullMsg);
        }
    }

    public static void nap(long msDuration) {
        try {
            Thread.sleep(msDuration);
        } catch (InterruptedException x) {
            Thread.currentThread().interrupt(); // re-assert the interrupt
        }
    }
}
