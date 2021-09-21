package com.abc.countdown;

/**
 *@Author: Nalongsone Danddank
 *@Email: nalongsone.danddank@my.metrostate.edu
 *@StudentID: 14958950
 *@Assignment: ICS440-HW03-CountdownTimer Fall2021
 *@Describe: This app counts down from 10 seconds to zero, one second at a time.
 */

public class CountdownWorker {
    private final int totalSeconds;
    private final MinuteSecondDisplay display;
    private final Thread internalThread;


    public CountdownWorker(int totalSeconds, MinuteSecondDisplay display) {
        this.totalSeconds = totalSeconds;
        this.display = display;


        // do the self-run pattern to spawn a thread to do the counting down.
        internalThread = new Thread(() -> countdownWork());
        internalThread.start();

    }

    // do the counting down and time correction.
    private void countdownWork() {
        long normalSleepTime = 1000;
        long nextSleepTime = normalSleepTime;
        int counter = 0;

        // Use System.currentTimeMillis() to find out the currnet time in ms.
        long startTime = System.currentTimeMillis();
        long actualTotalelapsedSecs = 0;
        while ( counter < totalSeconds ) {
            // MinuteSecondDisplay, use this method to display the number of seconds left
            display.setSeconds(totalSeconds - counter);

            // Calculate the actual elapsed time and compare it to the expected elapsed time and
            // adjust the next sleep time accordingly.
            long counterSecs = counter *1000;
            actualTotalelapsedSecs =  System.currentTimeMillis() - startTime ;
            long diffSecs = counterSecs - actualTotalelapsedSecs;
            nextSleepTime = normalSleepTime + diffSecs ;
            if ( nextSleepTime < 0 ) {
                nextSleepTime = 0;
            }

            // Sleep between calls to setSeconds,
           try {
               Thread.sleep(nextSleepTime);
           } catch ( InterruptedException x ) {
               // ignore
           }
           counter++;
        }
        //After reaching 0, print the actual total elapsed time.
//        System.out.println("the actual total elapsed time of " + internalThread.getName()
//        +": "+actualTotalelapsedSecs + " ms.");
        display.setSeconds(0);
        System.out.println("the actual total elapsed time of " + internalThread.getName()
        +": "+(System.currentTimeMillis() - startTime) + " ms.");
    }

}
