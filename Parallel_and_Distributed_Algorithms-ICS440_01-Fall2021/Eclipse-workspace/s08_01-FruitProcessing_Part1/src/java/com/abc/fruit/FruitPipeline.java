package com.abc.fruit;

import com.abc.pp.fifo.*;
import com.abc.pp.fifo.impl.*;
import com.abc.thread.*;

public class FruitPipeline {
    private static final String[] FRUITS =
        new String[] { "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew", "ilama", "jujube",
            "kiwi", "lemon", "mango", "nectarine", "orange", "pear", "quince", "raspberry", "strawberry", "tangerine",
            "uglifruit", "vanilla", "watermelon", "xigua", "youngberry", "zucchini" };

    public static void main(String[] args) throws InterruptedException {
        PPBoundedFifo<FruitWork> needsProcessingFifo = new CircularArrayPPBoundedFifo<>(10, FruitWork.class);
        PPBoundedFifo<FruitWork> processedFifo = new CircularArrayPPBoundedFifo<>(10, FruitWork.class);

        int workCount = 20;
        int processorCount = 1;

        FruitProcessor[] processors = new FruitProcessor[processorCount];
        ThreadTools.outln("spinning up %d processors", processors.length);
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new FruitProcessor(needsProcessingFifo, processedFifo);
        }

        ThreadTools.outln("spinning up printer");
        FruitPrinter printer = new FruitPrinter(processedFifo);

        // start the generator last so that everything else is primed and ready
        ThreadTools.outln("spinning up generator to create %d work items", workCount);
        FruitWorkGenerator generator = new FruitWorkGenerator(workCount, needsProcessingFifo);

        ThreadTools.outln("waiting for printer to be done...");
        printer.waitUntilStopped();
        ThreadTools.outln("printer is done, requesting the the generator and all processors stop");
        generator.stopRequest();
        generator.waitUntilStopped();
        for (FruitProcessor processor : processors) {
            processor.stopRequest();
            processor.waitUntilStopped();
        }
        ThreadTools.outln("everything should be stoppped");
    }

    private static class FruitWork {
        public final String name;
        public String processedName;
        public final Long sequence;
        public final boolean lastOne;

        public FruitWork(String name, Long sequence, boolean lastOne) {
            this.name = name;
            this.sequence = sequence;
            this.lastOne = lastOne;
        }
    }  // type FruitWork

    private static class FruitWorkGenerator {
        private final int workCount;
        private final PPBoundedFifo<FruitWork> outputFifo;
        private Thread internalThread;
        private volatile boolean keepGoing;

        public FruitWorkGenerator(int workCount,
                                  PPBoundedFifo<FruitWork> outputFifo) {
            this.workCount = workCount;
            this.outputFifo = outputFifo;

            keepGoing = true;
            internalThread = new Thread(() -> runWork(), getClass().getSimpleName());
            internalThread.start();
        }

        private void runWork() {
            ThreadTools.outln(Thread.currentThread().getName() + " starting");
            try {
                for (int i = 0; i < workCount && keepGoing; i++) {
                    outputFifo.add(new FruitWork(FRUITS[i], Long.valueOf(i), i == workCount - 1));
                }
            } catch ( InterruptedException x ) {
                // ignore
            } finally {
                ThreadTools.outln(Thread.currentThread().getName() + " finished");
            }
        }

        public void stopRequest() {
            keepGoing = false;
            internalThread.interrupt();
        }

        public void waitUntilStopped() throws InterruptedException {
            internalThread.join();
        }
    }  // type FruitWorkGenerator

    private static class FruitProcessor {
        private final PPBoundedFifo<FruitWork> inputFifo;
        private final PPBoundedFifo<FruitWork> outputFifo;
        private Thread internalThread;
        private volatile boolean keepGoing;

        public FruitProcessor(PPBoundedFifo<FruitWork> inputFifo,
                              PPBoundedFifo<FruitWork> outputFifo) {
            this.inputFifo = inputFifo;
            this.outputFifo = outputFifo;

            keepGoing = true;
            internalThread = new Thread(() -> runWork(), getClass().getSimpleName());
            internalThread.start();
        }

        private void runWork() {
            ThreadTools.outln(Thread.currentThread().getName() + " starting");
            try {
                while (keepGoing) {
                    FruitWork work = inputFifo.remove();
                    work.processedName = String.format("\"%s\".length=%d", work.name, work.name.length());
                    Thread.sleep(500); // pretend this takes more time to process
                    outputFifo.add(work);
                }
            } catch ( InterruptedException x ) {
                // ignore
            } finally {
                ThreadTools.outln(Thread.currentThread().getName() + " finished");
            }
        }

        public void stopRequest() {
            keepGoing = false;
            internalThread.interrupt();
        }

        public void waitUntilStopped() throws InterruptedException {
            internalThread.join();
        }
    }  // type FruitProcessor

    private static class FruitPrinter {
        private final PPBoundedFifo<FruitWork> inputFifo;
        private Thread internalThread;
        private volatile boolean keepGoing;

        public FruitPrinter(PPBoundedFifo<FruitWork> inputFifo) {
            this.inputFifo = inputFifo;

            keepGoing = true;
            internalThread = new Thread(() -> runWork(), getClass().getSimpleName());
            internalThread.start();
        }

        private void runWork() {
            ThreadTools.outln(Thread.currentThread().getName() + " starting");
            try {
                int printCount = 0;
                while (keepGoing) {
                    FruitWork work = inputFifo.remove();
                    ThreadTools.outln("sequence: %02d - processedName:%s", work.sequence, work.processedName);
                    printCount++;

                    if (work.lastOne) {
                        ThreadTools.outln("printed the last work, printCount=%d", printCount);
                        return; // let this thread die
                    }
                }
            } catch ( InterruptedException x ) {
                // ignore
            } finally {
                ThreadTools.outln(Thread.currentThread().getName() + " finished");
            }
        }

        @SuppressWarnings("unused")
        public void stopRequest() {
            keepGoing = false;
            internalThread.interrupt();
        }

        public void waitUntilStopped() throws InterruptedException {
            internalThread.join();
        }
    }  // type FruitPrinter
}
