package com.abc.address;

public class AddressDemo {
    public static void main(String[] args) {
        //Address a1 = new Address("123 Main St", "Apt 3B", "St. Paul", "MN", "55102");

//        Address.Builder builder = new Address.Builder();
//        builder.setStreet1("123 Main St");
//        builder.setCity("Plymouth");
//        builder.setState("MN");
//        builder.setZip("55401");
//
//        Address a1 = builder.create();

        Address a1 = new Address.Builder()
            .setStreet1("123 Main St")
            .setCity("Plymouth")
            .setZip("55401")
            .create();

        System.out.println("a1.getStreet1()=" + a1.getStreet1());
        System.out.println("a1.getCity()=" + a1.getCity());
        System.out.println("a1.getZip()=" + a1.getZip());

        Address a2 = new Address.Builder(a1)
            .setCity("Minneapolis")
            .create();
        System.out.println("a2.getStreet1()=" + a2.getStreet1());
        System.out.println("a2.getCity()=" + a2.getCity());
        System.out.println("a2.getZip()=" + a2.getZip());

        Address a3 = a2.createCopyWithZip("12345");
        System.out.println("a3.getStreet1()=" + a3.getStreet1());
        System.out.println("a3.getCity()=" + a3.getCity());
        System.out.println("a3.getZip()=" + a3.getZip());
    }
}
