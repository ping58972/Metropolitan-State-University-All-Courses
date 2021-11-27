package com.abc.name;

/**
 * Instances are immutable.
 */
public final class Person {
    private final ChangeableName name;
    private final int age;

    public Person(ChangeableName suppliedName, int age) {
        this.name = suppliedName.createCopy();
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return name.getLast();
    }

    public String getFirstName() {
        return name.getFirst();
    }

    public ChangeableName getName() {
        return name.createCopy();
    }
}
