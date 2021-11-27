package com.abc.name;

public class ChangeableName {
    private String first;
    private String last;

    public ChangeableName() {
    }

    public synchronized String getFirst() {
        return first;
    }

    public synchronized void setFirst(String first) {
        this.first = first;
    }

    public synchronized String getLast() {
        return last;
    }

    public synchronized void setLast(String last) {
        this.last = last;
    }

    public synchronized void setName(String newFirst, String newLast) {
        first = newFirst;
        last = newLast;
    }

    public synchronized ChangeableName createCopy() {
        ChangeableName copy = new ChangeableName();
        copy.setFirst(first);
        copy.setLast(last);
        return copy;
    }
}
