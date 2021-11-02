package com.abc.pp.fifo.impl;

import java.lang.reflect.*;
import java.util.*;

import com.abc.pp.fifo.*;
import com.abc.thread.*;
import com.abc.thread.WaitHelper.*;

/**
 * Implementation of {@link PPBoundedFifo} which uses a circular array
 * internally.
 * <p>
 * Look at the documentation in PPBoundedFifo to see how the methods are
 * supposed to work.
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
public class CircularArrayPPBoundedFifo<T> implements PPBoundedFifo<T> {
    private final Class<T> itemType;
    private final T[] slots;
    private int head;
    private int tail;
    private int count;
    private final Object lockObject;
    private final WaitHelper.Condition fullCondition;
    private final WaitHelper.Condition emptyCondition;
    private final T[] zeroLengthArray;

    public CircularArrayPPBoundedFifo(int fixedCapacity,
                                    Class<T> itemType,
                                    Object proposedLockObject) {

        lockObject =
            proposedLockObject != null ? proposedLockObject : new Object();

        if ( fixedCapacity < 1 ) {
            throw new IllegalArgumentException(
                "fixedCapacity must be at least 1");
        }

        if (itemType == null) {
            throw new IllegalArgumentException("itemType must not be null");
        }
        this.itemType = itemType;

        slots = createTypeArray(fixedCapacity);
        head = 0;
        tail = 0;
        count = 0;

        zeroLengthArray = createTypeArray(0);

        WaitHelper waitHelper = new WaitHelper(lockObject);
        fullCondition = waitHelper.createCondition(() -> isFull());
        emptyCondition = waitHelper.createCondition(() -> isEmpty());
    }

    // this constructor is correct as written - do not change
    public CircularArrayPPBoundedFifo(int fixedCapacity, Class<T> itemType) {
        this(fixedCapacity, itemType, null);
    }

    @SuppressWarnings("unchecked")
    private T[] createTypeArray(int size) {
        T[] array = (T[]) Array.newInstance(itemType, size);
        return array;
    }

    // this method is correct as written - do not change
    @Override
    public int getCount() {
        synchronized (lockObject) {
            return count;
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (lockObject) {
            return count == 0;
        }
    }

    @Override
    public boolean isFull() {
        synchronized (lockObject) {
            return count == slots.length;
        }
    }

    @Override
    public void clear() {
        synchronized (lockObject) {
            Arrays.fill(slots, null); // absolutely necessary
            head = 0;
            tail = 0;
            count = 0;
        }
    }

    // this method is correct as written - do not change
    @Override
    public int getCapacity() {
        return slots.length;
    }

    @Override
    public void add(T item) throws InterruptedException {
        synchronized (lockObject) {
            waitWhileFull();
            slots[tail] = item;
            tail = (tail + 1) % slots.length;
            count++;
            lockObject.notifyAll();
        }
    }

    private T removeFromNotEmptyFifo() {
        if (isEmpty()) throw new RuntimeException("this should 'never' happen");

        T item = slots[head];
        head = (head + 1) % slots.length;
        count--;
        lockObject.notifyAll();
        return item;
    }

    @Override
    public T remove() throws InterruptedException {
        synchronized (lockObject) {
            waitWhileEmpty();
            return removeFromNotEmptyFifo();
        }
    }

    // this method is correct as written - do not change
    @Override
    public Object getLockObject() {
        return lockObject;
    }

    // this method is correct as written - do not change
    @Override
    public Class<T> getItemType() {
        return itemType;
    }

    @Override
    public void addAll(T[] items) throws InterruptedException {
        synchronized (lockObject) {
            for (T item : items) {
                add(item);
            }
        }
    }

    @Override
    public T[] removeAtLeastOne() throws InterruptedException {
        synchronized (lockObject) {
            waitWhileEmpty();
            return removeAll();
        }
    }

    @Override
    public T[] removeAll() {
        synchronized (lockObject) {
            if (isEmpty()) return zeroLengthArray;

            T[] items = createTypeArray(count);
            for (int i = 0; i < items.length; i++) {
                items[i] = removeFromNotEmptyFifo();
            }
            return items;
        }
    }

    @Override
    public boolean waitUntilEmpty(long msTimeout) throws InterruptedException {
        return emptyCondition.waitUntilTrue(msTimeout) == WaitResult.SUCCESS;
    }

    @Override
    public void waitUntilEmpty() throws InterruptedException {
        waitUntilEmpty(0L);
    }

    @Override
    public boolean waitWhileEmpty(long msTimeout) throws InterruptedException {
        return emptyCondition.waitWhileTrue(msTimeout) == WaitResult.SUCCESS;
    }

    @Override
    public void waitWhileEmpty() throws InterruptedException {
        waitWhileEmpty(0L);
    }

    @Override
    public boolean waitUntilFull(long msTimeout) throws InterruptedException {
        return fullCondition.waitUntilTrue(msTimeout) == WaitResult.SUCCESS;
    }

    @Override
    public void waitUntilFull() throws InterruptedException {
        waitUntilFull(0L);
    }

    @Override
    public boolean waitWhileFull(long msTimeout) throws InterruptedException {
        return fullCondition.waitWhileTrue(msTimeout) == WaitResult.SUCCESS;

//        synchronized (lockObject) {
//            if (!isFull()) return true; // success
//
//            if (msTimeout == 0L) {
//                do {
//                    lockObject.wait();
//                } while (isFull());
//
//                return true; // success
//            }
//
//            long msEndTime = System.currentTimeMillis() + msTimeout;
//            long msRemaining = msTimeout;
//            while (msRemaining >= 1L) {
//                lockObject.wait(msRemaining);
//                if (!isFull()) return true; // success
//                msRemaining = msEndTime - System.currentTimeMillis();
//            }
//
//            return false; //  timed out
//        }
    }

    @Override
    public void waitWhileFull() throws InterruptedException {
        waitWhileFull(0L);
    }

}
