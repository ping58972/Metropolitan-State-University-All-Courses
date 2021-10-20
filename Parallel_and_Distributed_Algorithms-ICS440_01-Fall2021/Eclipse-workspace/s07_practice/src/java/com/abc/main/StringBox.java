package com.abc.main;

/**
 *
 */
public class StringBox {

    private String content;

    public synchronized void put(String newContent) {

    }
    public synchronized String getContent() {
        return content;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {

    }

//    public static int[] solution (int[] A, int F, int M) {
//        int[] result = new int[F];
//        int number_of_rolls = A.length + F;
//        int sum_of_rolls = M * number_of_rolls;
//        int sum_of_known_rolls = 0;
//
//            for(int roll_val: A)
//                sum_of_known_rolls += roll_val;
//            int sum_of_unknown_rolls = sum_of_rolls - sum_of_known_rolls;
//            //if the sum_of_unknown_rolls is out of range:
//            if(sum_of_unknown_rolls < F || sum_of_unknown_rolls > 6 * F)
//                return new int[1]; //possible rolls doesn't exist
//            /*all is left is to return one of the possible solutions. we will "fill" the dice results
//             * with the maximum values possible (6 if possible), and when we hit the "lower bound" (meaning the
//             * sum_of_unkown_rolls left is equal to the number of rolls left) it means the rest of the
//             * dice rolls are "1".*/
//            for (int i = 0; i < result.length; i++)
//            {
//                if (sum_of_unknown_rolls - (F - 1) <= 6)
//                {
//                    result[i] = sum_of_unknown_rolls - (F - 1);
//                    Arrays.fill(result, i + 1, result.length, 1);
//                    break;
//                }
//                else
//                {
//                    result[i] = 6;
//                    sum_of_unknown_rolls -= 6;
//                    F--;
//                }
//            }
//            return result;
//    }

}
