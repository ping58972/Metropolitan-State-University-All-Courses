package com.abc.string;

public class DeluxeStringDemo {
    public static void main(String[] args) {
        DeluxeString s1 = new DeluxeString("Hello".toCharArray());
        DeluxeString s2 = new DeluxeString("Hello".toCharArray());

        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
        System.out.println("s1.equals(s2)=" + s1.equals(s2));
        System.out.println("s2.equals(s1)=" + s2.equals(s1));

        System.out.println("s1.equals(4)=" + s1.equals(4));

        System.out.printf("s1.hashCode()=0x%08x %n", s1.hashCode());
        System.out.printf("s2.hashCode()=0x%08x %n", s2.hashCode());

    }
}
