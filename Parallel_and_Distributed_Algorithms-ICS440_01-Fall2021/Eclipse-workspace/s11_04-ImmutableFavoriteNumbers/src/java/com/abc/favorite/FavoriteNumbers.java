package com.abc.favorite;

/**
 *
 *
 * Instances are immutable.
 */
public final class FavoriteNumbers {
    private final int[] numbers;

    public FavoriteNumbers(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("array must have at least one favorite number");
        }
        this.numbers = numbers.clone();
    }

    public int mostFavoriteNumber() {
        return numbers[0];
    }

    public int[] getNumbers() {
        return numbers.clone();
    }
}
