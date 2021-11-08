package com.abc.stringcalc.dto;

import java.io.*;

public class StringCalcResponse implements Serializable {
    private int count;
    private int totalLength;
    private double averageLength;
    private int maxLength;
    private int minLength;

    /**
     * Returns the number of <tt>String</tt>'s counted. If the request 
     * indicated that array slots with <tt>null</tt> in them should be
     * ignored, then this "count" will be less than the length of the 
     * <tt>String[]</tt> in the request.
     */
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the total length of all of the <tt>String</tt>'s in the request. 
     */
    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    /**
     * Returns the average length of the <tt>String</tt>'s in the request.
     * If there aren't any valid strings to process, then this value is
     * set to 0.0.
     */
    public double getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(double averageLength) {
        this.averageLength = averageLength;
    }

    /**
     * Returns the length of the longest <tt>String</tt> in the request.
     * If there aren't any valid strings to process, then this value is
     * set to 0.
     */
    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Returns the length of the shortest <tt>String</tt> in the request.
     * If there aren't any valid strings to process, then this value is
     * set to 0.
     */
    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }
}