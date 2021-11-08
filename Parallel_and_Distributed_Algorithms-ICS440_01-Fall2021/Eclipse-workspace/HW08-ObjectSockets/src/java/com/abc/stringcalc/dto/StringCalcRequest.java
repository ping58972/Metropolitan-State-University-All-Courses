package com.abc.stringcalc.dto;

import java.io.*;

public class StringCalcRequest implements Serializable {
    private String[] data;
    private boolean ignoreNulls;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    /**
     * If true, a null anywhere in the array is skipped for the calculations.
     * If false, a null in the array is treated as a zero-length String and
     * is included in the calculations.
     */
    public boolean isIgnoreNulls() {
        return ignoreNulls;
    }

    public void setIgnoreNulls(boolean ignoreNulls) {
        this.ignoreNulls = ignoreNulls;
    }
}