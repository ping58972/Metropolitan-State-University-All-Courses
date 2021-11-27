package com.abc.thread;

import java.util.function.*;

public class WaitHelper {
    private final Object lockObject;

    public WaitHelper(Object lockObject) {
        this.lockObject = lockObject;
    }

    public Condition createCondition(BooleanSupplier expression) {
        return new Condition(expression, lockObject);
    }

    public static enum WaitResult {
        SUCCESS,
        TIMED_OUT;
    } // type WaitResult


    public static class Condition {
        private final BooleanSupplier expression;
        private final Object lockObject;

        public Condition(BooleanSupplier expression, Object lockObject) {
            this.expression = expression;
            this.lockObject = lockObject;
        }

        public WaitResult waitWhileTrue(long msTimeout) throws InterruptedException {
            synchronized (lockObject) {
                if (!expression.getAsBoolean()) return WaitResult.SUCCESS;

                if (msTimeout == 0L) {
                    do {
                        lockObject.wait();
                    } while (expression.getAsBoolean());

                    return WaitResult.SUCCESS;
                }

                long msEndTime = System.currentTimeMillis() + msTimeout;
                long msRemaining = msTimeout;
                while (msRemaining >= 1L) {
                    lockObject.wait(msRemaining);
                    if (!expression.getAsBoolean()) return WaitResult.SUCCESS;
                    msRemaining = msEndTime - System.currentTimeMillis();
                }

                return WaitResult.TIMED_OUT;
            }
        }

        public void waitWhileTrue() throws InterruptedException {
            waitWhileTrue(0L);
        }

        public WaitResult waitUntilTrue(long msTimeout) throws InterruptedException {
            synchronized (lockObject) {
                if (expression.getAsBoolean()) return WaitResult.SUCCESS;

                if (msTimeout == 0L) {
                    do {
                        lockObject.wait();
                    } while (!expression.getAsBoolean());

                    return WaitResult.SUCCESS;
                }

                long msEndTime = System.currentTimeMillis() + msTimeout;
                long msRemaining = msTimeout;
                while (msRemaining >= 1L) {
                    lockObject.wait(msRemaining);
                    if (expression.getAsBoolean()) return WaitResult.SUCCESS;
                    msRemaining = msEndTime - System.currentTimeMillis();
                }

                return WaitResult.TIMED_OUT;
            }
        }

        public void waitUntilTrue() throws InterruptedException {
            waitUntilTrue(0L);
        }
    }  // type Condition
}
