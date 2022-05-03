package interview;

import java.util.*;

/**
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    public static int dayOfYear2(int m, int d, int y) {
        List<Integer> days = Arrays.asList(0, 0, 31, 28, 31);
        for (int i = 0; i <= m; i++ ) {
            d += days.get(i);
        }
        return d;
    }

    // public static int dayOfYear(int month, int dayOfMonth, int year) {
    //     if (month == 2) {
    //         dayOfMonth += 31;
    //     } else if (month == 3) {
    //         dayOfMonth += 59;
    //     } else if (month == 4) {
    //         dayOfMonth += 90;
    //     } else if (month == 5) {
    //         dayOfMonth += 31 + 28 + 31 + 30;
    //     } else if (month == 6) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31;
    //     } else if (month == 7) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30;
    //     } else if (month == 8) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31;
    //     } else if (month == 9) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31;
    //     } else if (month == 10) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30;
    //     } else if (month == 11) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
    //     } else if (month == 12) {
    //         dayOfMonth += 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 31;
    //     }
    //     return dayOfMonth;
    // }

}
