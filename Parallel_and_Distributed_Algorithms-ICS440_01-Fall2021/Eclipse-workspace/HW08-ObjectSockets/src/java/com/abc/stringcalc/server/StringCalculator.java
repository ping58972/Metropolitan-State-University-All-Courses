package com.abc.stringcalc.server;

import com.programix.util.*;

public class StringCalculator extends Object {
	private int count;
	private int totalLength;
	private double averageLength;
	private int maxLength;
	private int minLength;

    /**
     * Processes the array of strings and calculated all the values.
     * @param data the strings to process.
     * @param ignoreNulls if true, any null references found in the array
     * are skipped (as if there were not there), if false, null references
     * are treated as a zero-length string.
     */
	public StringCalculator(String[] data, boolean ignoreNulls) {
		// Instead of hanging onto the array reference in a member variable,
		// fully process the String[] during construction and pre-calculate
		// all of the answers.

        data = ( data == null ) ? StringTools.ZERO_LEN_ARRAY : data;
        
		count = 0;
		totalLength = 0;
		maxLength = 0;
		minLength = Integer.MAX_VALUE;

		for ( int i = 0; i < data.length; i++ ) {
            String s = data[i];
			if ( s == null ) {
                if ( ignoreNulls ) {
                    // skip the slots with null, don't increment counter
                    continue;
                } else {
                    // treat null as a zero-length string
                    s = StringTools.ZERO_LEN_STRING;
                }
			}

			count++;

			int len = s.length();
			totalLength += len;
			maxLength = Math.max(maxLength, len);
			minLength = Math.min(minLength, len);
		}

		if ( count == 0 ) {
			// Every slot in the array was holding null, or array had a length
            // of zero, or the original array reference was null.
			averageLength = 0.0;
			minLength = 0;
		} else {
			averageLength = ((double) totalLength) / ((double) count);
		}
	}

	public int getCount() {
		return count;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public int getMinLength() {
		return minLength;
	}

	public double getAverageLength() {
		return averageLength;
	}
}
