package com.abc.fruit;

/**
 *@Author: Nalongsone Danddank
 *@Email: nalongsone.danddank@my.metrostate.edu
 *@StudentID: 14958950
 *@Assignment: ICS440-HW02-FruitThread Fall2021
 *@Describe: Fruit Demo class has main method to run Fuilt class Thread.
 */
public class FruitDemo {

    /**
     * @param args
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException {
        new Fruit("Apple");
        new Fruit("Banana");
        new Fruit("Mango");
        new Fruit("Orange");
//        Thread.sleep(300);
    }

}
