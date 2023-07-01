package algorithm.dynamicPrograming;

import java.util.Arrays;

/* Ugly Number
* Given a integer n, find and return the ugly number u
* ugly number only consist of the multiples of 2, 3, 5
*
* example
* n = 10, u = 12
* [1, 2, 3, 4, 5, 6, 8, 9, 10, 12], 12 is at 10th number
*
* n = 1, u = 1
* [1], 1 is considered as ugly number
* */
public class Q264 {

    public int getUglyNumberMinHeap(int n) {
        return 0;
    }

    public int getUglyNumber(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2, i2 = 1, i3 = 1, i5 = 1; i < dp.length; i++) {
            dp[i] = getMin(dp[i2] * 2, dp[i3] * 3, dp[i5] * 5);

            //deduplication
            if (dp[i] == dp[i2] * 2) {
                i2++;
            }
            if (dp[i] == dp[i3] * 3) {
                i3++;
            }
            if (dp[i] == dp[i5] * 5) {
                i5++;
            }

        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private int getMin(int i2, int i3, int i5) {
        return Math.min(Math.min(i2, i3), i5);
    }

    public static void main(String[] args) {
        Q264 solution = new Q264();
        System.out.println(solution.getUglyNumber(10));
    }
}
