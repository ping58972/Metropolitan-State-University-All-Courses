package com.abc.pp.fifo;

import java.util.*;

import com.abc.pp.fifo.impl.*;

public class FifoReorderer<T> {
    public final FrontEnd<T> frontEnd;
    public final BackEnd<T> backEnd;

    private FifoReorderer(Builder<T> builder) {
        frontEnd = new FrontEnd<>(
            builder.frontEndInputFifo,
            builder.frontEndOutputFifo,
            builder.sequenceFifo);

        backEnd = new BackEnd<>(
            builder.backEndInputFifo,
            builder.backEndOutputFifo,
            builder.sequenceFifo);
    }

    public static class Builder<T> {
        public static final int DEFAULT_MAX_BACKLOG = 100;

        private int maxBacklog;

        private Class<T> itemType;

        private PPBoundedFifo<T> frontEndInputFifo;
        private PPBoundedFifo<T> frontEndOutputFifo;

        private PPBoundedFifo<T> backEndInputFifo;
        private PPBoundedFifo<T> backEndOutputFifo;

        private PPBoundedFifo<T> sequenceFifo;

        public Builder() {
            maxBacklog = DEFAULT_MAX_BACKLOG;
        }

        public FifoReorderer<T> create() throws IllegalArgumentException {
            if (itemType == null) throw new IllegalArgumentException("itemType must not be null");
            if (frontEndInputFifo == null) throw new IllegalArgumentException("frontEndInputFifo must not be null");
            if (frontEndOutputFifo == null) throw new IllegalArgumentException("frontEndOutputFifo must not be null");
            if (backEndInputFifo == null) throw new IllegalArgumentException("backEndInputFifo must not be null");
            if (backEndOutputFifo == null) throw new IllegalArgumentException("backEndOutputFifo must not be null");

            sequenceFifo = new CircularArrayPPBoundedFifo<>(maxBacklog, itemType);
            return new FifoReorderer<>(this);
        }

        public Builder<T> setMaxBacklog(int maxBacklog) {
            this.maxBacklog = Math.max(1, maxBacklog);
            return this;
        }

        public Builder<T> setItemType(Class<T> itemType) {
            this.itemType = itemType;
            return  this;
        }

        public Builder<T> setFrontEndInputFifo(PPBoundedFifo<T> frontEndInputFifo) {
            this.frontEndInputFifo = frontEndInputFifo;
            return this;
        }

        public Builder<T> setFrontEndOutputFifo(PPBoundedFifo<T> frontEndOutputFifo) {
            this.frontEndOutputFifo = frontEndOutputFifo;
            return this;
        }

        public Builder<T> setBackEndInputFifo(PPBoundedFifo<T> backEndInputFifo) {
            this.backEndInputFifo = backEndInputFifo;
            return this;
        }

        public Builder<T> setBackEndOutputFifo(PPBoundedFifo<T> backEndOutputFifo) {
            this.backEndOutputFifo = backEndOutputFifo;
            return this;
        }
    } // type Builder


    public static class FrontEnd<T> {
        private final PPBoundedFifo<T> inboxFifo;
        private final PPBoundedFifo<T> outboxFifo;
        private final PPBoundedFifo<T> sequenceFifo;

        private final Thread internalThread;
        private volatile boolean keepRunning;

        private FrontEnd(PPBoundedFifo<T> inboxFifo,
                         PPBoundedFifo<T> outboxFifo,
                         PPBoundedFifo<T> sequenceFifo) {

            this.inboxFifo = inboxFifo;
            this.outboxFifo = outboxFifo;
            this.sequenceFifo = sequenceFifo;

            keepRunning = true;
            (internalThread = new Thread(() -> runWork(), "Reorderer-FrontEnd")).start();
        }

        private void runWork() {
            try {
                while (keepRunning) {
                    T work = inboxFifo.remove();
                    sequenceFifo.add(work);
                    outboxFifo.add(work);
                }
            } catch ( InterruptedException x ) {
                // ignore, and let thread die
            }
        }

        public void stopRequest() {
            keepRunning = false;
            internalThread.interrupt();
        }
    } // type FrontEnd


    public static class BackEnd<T> {
        private final PPBoundedFifo<T> inboxFifo;
        private final PPBoundedFifo<T> outboxFifo;
        private final PPBoundedFifo<T> sequenceFifo;

        private final Thread internalThread;
        private volatile boolean keepRunning;

        private BackEnd(PPBoundedFifo<T> inboxFifo,
                        PPBoundedFifo<T> outboxFifo,
                        PPBoundedFifo<T> sequenceFifo) {
            this.inboxFifo = inboxFifo;
            this.outboxFifo = outboxFifo;
            this.sequenceFifo = sequenceFifo;

            keepRunning = true;
            (internalThread = new Thread(() -> runWork(), "Reorderer-BackEnd")).start();
        }

        private void runWork() {
            Backlog<T> backlog = new Backlog<>();

            try {
                while (keepRunning) {
                    T nextWorkToSendAlong = sequenceFifo.remove();

                    if (backlog.remove(nextWorkToSendAlong)) {
                        // was already in our backlog, so send it along
                        outboxFifo.add(nextWorkToSendAlong);
                    } else {
                        // not in our backlog, keep pulling and storing until we find it
                        inboxLoop: while (keepRunning) {
                            T work = inboxFifo.remove();
                            //ThreadTools.outln("got work: %s", work);

                            if (work == nextWorkToSendAlong) {
                                outboxFifo.add(work);
                                break inboxLoop;
                            } else {
                                // not the one we're looking for, put it into the backlog and keep looking
                                backlog.append(work);
                            }
                        }
                    }
                }
            } catch ( InterruptedException x ) {
                // ignore, and let thread die
            }
        }

        public void stopRequest() {
            keepRunning = false;
            internalThread.interrupt();
        }
    } // type BackEnd

    private static class Backlog<T> {
        private final IdentityHashMap<T, Object> map = new IdentityHashMap<>();
        private final Object dummyObject = new Object();

        public void append(T item) {
            map.put(item, dummyObject);
            //ThreadTools.outln("adding to backlog, currSize=%d, item=%s", map.size(), item);
        }

        public boolean remove(T item) {
            return map.remove(item) == dummyObject;
        }
    }  // type Backlog
}
