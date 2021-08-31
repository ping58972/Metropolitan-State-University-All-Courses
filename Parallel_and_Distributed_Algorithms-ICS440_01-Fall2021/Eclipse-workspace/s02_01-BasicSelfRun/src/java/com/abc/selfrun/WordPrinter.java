package com.abc.selfrun;

public class WordPrinter {
    private int favoriteNumber;

    public WordPrinter(int favoriteNumber) {
        // ... do other constructor stuff...
        this.favoriteNumber = favoriteNumber;

        Thread t = new Thread(() -> runWork());
        t.start();
    }

    private void runWork() {
        try {
            System.out.println("apple " + favoriteNumber);
            Thread.sleep(500);
            System.out.println("banana " + favoriteNumber);
            Thread.sleep(500);
            System.out.println("cherry " + favoriteNumber);
            Thread.sleep(500);
            System.out.println("date " + favoriteNumber);
            Thread.sleep(500);
            System.out.println("elderberry " + favoriteNumber);
        } catch (InterruptedException x) {
            x.printStackTrace();
        }
    }
}
