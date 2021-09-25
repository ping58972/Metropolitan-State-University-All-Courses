package com.abc.foo;

import java.util.*;

import com.abc.thread.*;

public class BarMain {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException {
        Bar a = new Bar();
        Bar b = new Bar();
        Bar c = new Bar();

        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println("c=" + c);

        StringStore stringStore = new StringStore();

        new BarCreator(400_000, stringStore);
        new BarCreator(450_000, stringStore);
        new BarCreator(750_000, stringStore);
        new BarCreator(450_000, stringStore);
        new BarCreator(450_000, stringStore);
//        new BarCreator(90_000_000, stringStore);

        Thread.sleep(10_000L);
        stringStore.printReport();
    }

    private static class BarCreator {
        private final int barCount;
        private final StringStore stringStore;

        public BarCreator(int barCount,
                          StringStore stringStore) {
            this.barCount = barCount;
            this.stringStore = stringStore;

            Thread t = new Thread(() -> runWork(), getClass().getSimpleName());
            t.start();
        }

        private void runWork() {
            for (int i = 0; i < barCount; i++) {
                Bar bar = new Bar();
                stringStore.add(bar.toString());
            }
        }
    }  // type BarCreator

    private static class StringStore {
        private final List<String> list;

        public StringStore() {
            list = new ArrayList<>();
        }

        public synchronized void add(String s) {
            list.add(s);
        }

        public void printReport() {
            List<String> copy = null;
            synchronized (this) {
                copy = new ArrayList<>(list);
            }

            ThreadTools.outln("%,d strings added", copy.size());

            Map<String, Integer> map = new HashMap<>();
            for (String s : copy) {
                Integer oldCount = map.get(s);
                if (oldCount == null) {
                    map.put(s, 1);
                } else {
                    map.put(s, oldCount + 1);
                }
            }

            long goodCount = 0;
            long twoCount = 0;
            long threeCount = 0;
            long fourCount = 0;
            long fivePlusCount = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                int count = entry.getValue();
                if (count >= 4) {
                    ThreadTools.outln("duplicate for %s, it appeared %d times", entry.getKey(), count);
                }

                if (count == 1) goodCount++;
                else if (count == 2) twoCount++;
                else if (count == 3) threeCount++;
                else if (count == 4) fourCount++;
                else fivePlusCount++;
            }
            ThreadTools.outln("%,10d good ones", goodCount);
            ThreadTools.outln("%,10d exactly twice", twoCount);
            ThreadTools.outln("%,10d exactly 3x", threeCount);
            ThreadTools.outln("%,10d exactly 4x", fourCount);
            ThreadTools.outln("%,10d 5+ times", fivePlusCount);
        }
    }  // type StringStore
}
