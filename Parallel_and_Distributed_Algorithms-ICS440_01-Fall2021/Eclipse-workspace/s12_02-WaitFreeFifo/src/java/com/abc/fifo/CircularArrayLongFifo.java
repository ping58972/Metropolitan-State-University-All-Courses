package com.abc.fifo;


/**
 * Implementation of {@link LongFifo} which uses a circular array internally.
 * <p>
 * Look at the documentation in LongFifo to see how the methods are supposed to
 * work.
 * <p>
 * The data is stored in the slots array. count is the number of items currently
 * in the FIFO. When the FIFO is not empty, head is the index of the next item
 * to remove. When the FIFO is not full, tail is the index of the next available
 * slot to use for an added item. Both head and tail need to jump to index 0
 * when they "increment" past the last valid index of slots (this is what makes
 * it circular).
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular Buffer
 * on Wikipedia</a> for more information.
 */
public class CircularArrayLongFifo implements LongFifo {
    private final long[] slots;
    private volatile int head;
    private volatile int tail;

    public CircularArrayLongFifo(int fixedCapacity) {
        slots = new long[fixedCapacity];
        head = 0;
        tail = 0;
    }

    // this method is correct as written - do not change
    @Override
    public int getCount() {
        return tail - head;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean isFull() {
        return tail - head == slots.length;
    }

    @Override
    public void clear() {
        head = tail;
    }

    @Override
    public int getCapacity() {
        return slots.length;
    }

    @Override
    public void add(long value) throws InterruptedException {
        //while (isFull()); // spin
        while (isFull()) {
            if (Thread.interrupted()) throw new InterruptedException();
        }

        slots[tail % slots.length] = value;
        tail++;
    }

    @Override
    public long remove() throws InterruptedException {
        //while (isEmpty()); // spin
        while (isEmpty()) {
            if (Thread.interrupted()) throw new InterruptedException();
        }

        long value = slots[head % slots.length];
        head++;
        return value;
    }
}
