package com.abc.atomic;

import com.abc.mutex.peterson.*;

public class CounterDemo {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
//        Counter counter = new Counter(new CheatMutex());
        Counter counter = new Counter(new PetersonMutex());

        new CounterGrabber(counter);
        new CounterGrabber(counter);
//        new CounterGrabber(counter);
//        new CounterGrabber(counter);
    }

    private static class CounterGrabber {
        private final Counter counter;

        public CounterGrabber(Counter counter) {
            this.counter = counter;

            Thread t = new Thread(() -> runWork(), getClass().getSimpleName());
            t.start();
        }

        private void runWork() {
            try {
                System.err.println("ThreadId=" + ThreadId.SINGLETON.getId());

                long[] numbers = new long[100];
                int numberPtr = 0;
                while (numberPtr < numbers.length) {
                    numbers[numberPtr] = counter.getAndIncrement();
                    numberPtr++;
                    Thread.sleep(20);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numbers.length; i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(numbers[i]);
                }
                System.out.println(sb + "\n---");
            } catch ( InterruptedException x ) {
                // ignore
            }
        }
    }  // type CounterGrabber
}
