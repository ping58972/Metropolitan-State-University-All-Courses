package com.abc.selfmore;

public class SelfRunMore {
    private static void println(String msg) {
        System.out.printf("%-20.20s|%s%n", Thread.currentThread().getName(), msg);
    }

    public static void main(String[] args) throws InterruptedException {
        println("constructing self-running: NestedSelfRunningWorker");
        NestedSelfRunningWorker worker = new NestedSelfRunningWorker();
        println("... back from constructor, sleeping for 3 seconds...");
        Thread.sleep(3100);
        println("Done sleep, issuing stopRequest()...");
        worker.stopRequest();
        println("Waiting until it's actually stoppped...");
        worker.waitUntilStopped();
        println("worker has stopped");
    }

    private static class NestedSelfRunningWorker {
        private Thread internalThread;
        private volatile boolean keepGoing;

        public NestedSelfRunningWorker() {
            keepGoing = true;
            internalThread = new Thread(() -> runWork(), getClass().getSimpleName());
            internalThread.start();
        }

        private void runWork() {
            try {
                println("starting");
                for (int i = 0; i < 10 && keepGoing; i++) {
                    println("inside runWork() - i=" + i);
                    Thread.sleep(500);
                }
            } catch ( InterruptedException x ) {
                // ignore
            } finally {
                println("finished");
            }
        }

        public void stopRequest() {
            keepGoing = false;
            internalThread.interrupt();
        }

        public void waitUntilStopped() throws InterruptedException {
            internalThread.join();
        }
    }  // type NestedSelfRunningWorker
}
