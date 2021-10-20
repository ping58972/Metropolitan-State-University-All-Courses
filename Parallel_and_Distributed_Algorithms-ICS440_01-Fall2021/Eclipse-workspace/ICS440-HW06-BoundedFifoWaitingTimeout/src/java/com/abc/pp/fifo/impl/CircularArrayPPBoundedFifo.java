package com.abc.pp.fifo.impl;

import java.lang.reflect.*;
import java.util.*;

import com.abc.pp.fifo.*;

/**
 * Implementation of {@link PPBoundedFifo} which uses a circular array internally.
 * <p>
 * Look at the documentation in PPBoundedFifo to see how the methods are supposed to work.
 * <p>
 * The data is stored in the slots array. count is the number of items currently in the FIFO. When
 * the FIFO is not empty, head is the index of the next item to remove. When the FIFO is not full,
 * tail is the index of the next available slot to use for an added item. Both head and tail need to
 * jump to index 0 when they "increment" past the last valid index of slots (this is what makes it
 * circular).
 * <p>
 * See <a href="https://en.wikipedia.org/wiki/Circular_buffer">Circular Buffer on Wikipedia</a> for
 * more information.
 */
public class CircularArrayPPBoundedFifo<T> implements PPBoundedFifo<T> {
    private final Class<T> itemType;
    private final T[] slots;
    private int head;
    private int tail;
    private int count;
    private final Object lockObject;

    public CircularArrayPPBoundedFifo(int fixedCapacity, Class<T> itemType, Object proposedLockObject) {

        lockObject = proposedLockObject != null ? proposedLockObject : new Object();

        if (fixedCapacity < 1) {
            throw new IllegalArgumentException("fixedCapacity must be at least 1");
        }

        if (itemType == null) {
            throw new IllegalArgumentException("itemType must not be null");
        }
        this.itemType = itemType;

        slots = createTypeArray(fixedCapacity);
        head = 0;
        tail = 0;
        count = 0;

        // TODO - add more to constructor if you need to....but don't change code above this line
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

        return getCount() == 0;
    }

    @Override
    public boolean isFull() {

        return getCount() == getCapacity();
    }

    @Override
    public void clear() {
        synchronized (lockObject) {
            head = 0;
            tail = 0;
            count = 0;
            // need this for garbic collection.
            Arrays.fill(slots, null);
            lockObject.notifyAll();
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
            slots[head] = item;
            head = (head + 1) % slots.length;
            count++ ;
            lockObject.notifyAll();
        }
    }

    @Override
    public T remove() throws InterruptedException {
        synchronized (lockObject) {
            waitWhileEmpty();
            T value = slots[tail];
            // need this for garbic collection.
            slots[tail] = null;
            tail = (tail + 1) % slots.length;
            count-- ;
            lockObject.notifyAll();
            return value;
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
        if (items == null || items.length == 0)
            return;
        synchronized (lockObject) {

            // for (T t : items) {
            // waitWhileFull();
            // add(t);
            // }
            int addCount = 0;
            do {
                waitWhileFull();
                int currentSpace = getCapacity();
                int addThisTime = Math.min(items.length - addCount, currentSpace);
                for (int i = 0; i < addThisTime; i++ ) {
                    slots[head] = items[addCount + i];
                    head = (head + 1) % slots.length;

                }
                count += addThisTime;
                addCount += addThisTime;
                lockObject.notifyAll();
            } while (addCount < items.length);
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
            T[] arr = createTypeArray(count);
            if (count == 0)
                return arr;
            int i = 0;
            while (count > 0) {
                try {
                    arr[i] = remove();
                } catch (InterruptedException x) {
                }
                i++ ;
            }
            clear();
            return arr;
        }

    }

    @Override
    public boolean waitUntilEmpty(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (msTimeout == 0L) {
                while ( !isEmpty()) {
                    lockObject.wait();
                }
                return true;
            }
            long endTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while ( !isEmpty() && (msRemaining > 0L)) {
                lockObject.wait(msRemaining);
                msRemaining = endTime - System.currentTimeMillis();
            }
            return isEmpty();
        }
    }

    // this method is correct as written - do not change
    @Override
    public void waitUntilEmpty() throws InterruptedException {
        waitUntilEmpty(0);
    }

    @Override
    public boolean waitWhileEmpty(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (msTimeout == 0L) {
                while (isEmpty()) {
                    lockObject.wait();
                }
                return true;
            }
            long endTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while (isEmpty() && (msRemaining > 0L)) {
                lockObject.wait(msRemaining);
                msRemaining = endTime - System.currentTimeMillis();
            }
            return !isEmpty();
        }
    }

    @Override
    public void waitWhileEmpty() throws InterruptedException {
        waitWhileEmpty(0);
    }

    @Override
    public boolean waitUntilFull(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (msTimeout == 0L) {
                while ( !isFull()) {
                    lockObject.wait();
                }
                return true;
            }
            long endTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while ( !isFull() && (msRemaining > 0L)) {
                lockObject.wait(msRemaining);
                msRemaining = endTime - System.currentTimeMillis();
            }
            return isFull();
        }
    }

    @Override
    public void waitUntilFull() throws InterruptedException {
        waitUntilFull(0);
    }

    @Override
    public boolean waitWhileFull(long msTimeout) throws InterruptedException {
        synchronized (lockObject) {
            if (msTimeout == 0L) {
                while (isFull()) {
                    lockObject.wait();
                }
                return true;
            }
            long endTime = System.currentTimeMillis() + msTimeout;
            long msRemaining = msTimeout;
            while (isFull() && (msRemaining > 0L)) {
                lockObject.wait(msRemaining);
                msRemaining = endTime - System.currentTimeMillis();
            }
            return !isFull();
        }
    }

    @Override
    public void waitWhileFull() throws InterruptedException {
        waitWhileFull(0);
    }
}
