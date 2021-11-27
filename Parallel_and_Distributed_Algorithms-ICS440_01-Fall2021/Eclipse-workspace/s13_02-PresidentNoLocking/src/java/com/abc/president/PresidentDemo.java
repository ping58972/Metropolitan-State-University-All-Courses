package com.abc.president;

public class PresidentDemo {
    public static void main(String[] args) {
        President p1 = new President();
        p1.edit().setFirstName("George").setLastName("Washington").commit();

        President.View v1 = p1.getView();
        System.out.println("v1.getFirstName()=" + v1.getFirstName());
        System.out.println("v1.getLastName()=" + v1.getLastName());

        p1.edit().setFirstName("Abe").setLastName("Lincoln").commit();

        System.out.println("v1.getFirstName()=" + v1.getFirstName());
        System.out.println("v1.getLastName()=" + v1.getLastName());

        President.View v2 = p1.getView();
        System.out.println("v2.getFirstName()=" + v2.getFirstName());
        System.out.println("v2.getLastName()=" + v2.getLastName());
    }
}
