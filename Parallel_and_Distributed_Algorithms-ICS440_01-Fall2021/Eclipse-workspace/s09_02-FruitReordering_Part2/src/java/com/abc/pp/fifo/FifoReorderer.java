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
            builder.sequenceExtractor,
            builder.sequenceFifo);

        backEnd = new BackEnd<>(
            builder.backEndInputFifo,
            builder.backEndOutputFifo,
            builder.sequenceExtractor,
            builder.sequenceFifo);
    }

    public static class Builder<T> {
        public static final int DEFAULT_MAX_BACKLOG = 100;

        private int maxBacklog;

        private PPBoundedFifo<T> frontEndInputFifo;
        private PPBoundedFifo<T> frontEndOutputFifo;

        private PPBoundedFifo<T> backEndInputFifo;
        private PPBoundedFifo<T> backEndOutputFifo;

        private SequenceExtractor<T> sequenceExtractor;
        private PPBoundedFifo<Long> sequenceFifo;

        public Builder() {
            maxBacklog = DEFAULT_MAX_BACKLOG;
        }

        public FifoReorderer<T> create() throws IllegalArgumentException {
            if (sequenceExtractor == null) throw new IllegalArgumentException("sequenceExtractor must not be null");
            if (frontEndInputFifo == null) throw new IllegalArgumentException("frontEndInputFifo must not be null");
            if (frontEndOutputFifo == null) throw new IllegalArgumentException("frontEndOutputFifo must not be null");
            if (backEndInputFifo == null) throw new IllegalArgumentException("backEndInputFifo must not be null");
            if (backEndOutputFifo == null) throw new IllegalArgumentException("backEndOutputFifo must not be null");

            sequenceFifo = new CircularArrayPPBoundedFifo<>(maxBacklog, Long.class);
            return new FifoReorderer<>(this);
        }

        public Builder<T> setMaxBacklog(int maxBacklog) {
            this.maxBacklog = Math.max(1, maxBacklog);
            return this;
        }

        public Builder<T> setSequenceExtractor(SequenceExtractor<T> sequenceExtractor) {
            this.sequenceExtractor = sequenceExtractor;
            return this;
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
        private SequenceExtractor<T> sequenceExtractor;
        private final PPBoundedFifo<Long> sequenceFifo;

        private final Thread internalThread;
        private volatile boolean keepRunning;

        private FrontEnd(PPBoundedFifo<T> inboxFifo,
                         PPBoundedFifo<T> outboxFifo,
                         SequenceExtractor<T> sequenceExtractor,
                         PPBoundedFifo<Long> sequenceFifo) {

            this.inboxFifo = inboxFifo;
            this.outboxFifo = outboxFifo;
            this.sequenceExtractor = sequenceExtractor;
            this.sequenceFifo = sequenceFifo;

            keepRunning = true;
            (internalThread = new Thread(() -> runWork(), "Reorderer-FrontEnd")).start();
        }

        private void runWork() {
            try {
                while (keepRunning) {
                    T work = inboxFifo.remove();
                    Long workSeq = sequenceExtractor.extractSequence(work);

                    sequenceFifo.add(workSeq);
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
        private SequenceExtractor<T> sequenceExtractor;
        private final PPBoundedFifo<Long> sequenceFifo;

        private final Thread internalThread;
        private volatile boolean keepRunning;

        private BackEnd(PPBoundedFifo<T> inboxFifo,
                        PPBoundedFifo<T> outboxFifo,
                        SequenceExtractor<T> sequenceExtractor,
                        PPBoundedFifo<Long> sequenceFifo) {
            this.inboxFifo = inboxFifo;
            this.outboxFifo = outboxFifo;
            this.sequenceExtractor = sequenceExtractor;
            this.sequenceFifo = sequenceFifo;

            keepRunning = true;
            (internalThread = new Thread(() -> runWork(), "Reorderer-BackEnd")).start();
        }

        private void runWork() {
            Map<Long, T> sequenceToWorkLookup = new HashMap<>();

            try {
                while (keepRunning) {
                    Long nextSequenceToSendAlong = sequenceFifo.remove();
                    T workFromBacklog = sequenceToWorkLookup.remove(nextSequenceToSendAlong);
                    if (workFromBacklog != null) {
                        // was already in our backlog, so send it along
                        outboxFifo.add(workFromBacklog);
                    } else {
                        // not in our backlog, keep pulling and storing until we find it
                        while (keepRunning) {
                            T work = inboxFifo.remove();
                            Long sequence = sequenceExtractor.extractSequence(work);

                            if (sequence.equals(nextSequenceToSendAlong)) {
                                outboxFifo.add(work);
                                break;
                            } else {
                                // not the one we're looking for, put it into the backlog and keep looking
                                sequenceToWorkLookup.put(sequence, work);
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

    public interface SequenceExtractor<T> {
        Long extractSequence(T item);
    }  // type SequenceExtractor
}
