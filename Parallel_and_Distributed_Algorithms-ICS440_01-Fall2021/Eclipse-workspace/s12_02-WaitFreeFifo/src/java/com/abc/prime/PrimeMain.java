package com.abc.prime;

import com.abc.fifo.*;

public class PrimeMain {
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException {
        LongFifo candidateFifo = new CircularArrayLongFifo(500_000);
        LongFifo primeFifo = new CircularArrayLongFifo(200_000);

        new PrimePrinter(primeFifo);

        // only 1 allowed
        new PrimeChecker(candidateFifo, primeFifo);

        Thread.sleep(2_000L); // pause to let other threads spin up

        new CandidateGenerator(candidateFifo);
    }
}
