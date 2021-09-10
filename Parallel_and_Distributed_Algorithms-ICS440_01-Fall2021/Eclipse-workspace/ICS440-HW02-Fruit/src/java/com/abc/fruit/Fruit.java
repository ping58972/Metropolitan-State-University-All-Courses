package com.abc.fruit;

/**
 *@Author: Nalongsone Danddank
 *@Email: nalongsone.danddank@my.metrostate.edu
 *@StudentID: 14958950
 *@Assignment: ICS440-HW02-FruitThread Fall2021
 *@Describe: Fruit class to create a new Thread to run the Thread in runWork method.
 */
public class Fruit {
    private Thread internalThread;
    private String name;
    public Fruit(String name) {
        this.name = name;
        internalThread = new Thread(()-> runWork());
        internalThread.start();
    }
    private void runWork() {
        for(int i=1; i<=20; i++) {
            try {
                System.out.println(this.name+ "\t#"+i);
                Thread.sleep(500);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }

    }
}
