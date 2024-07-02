package algorithm.dynamicPrograming;

import java.util.Arrays;

/*
931. Minimum Falling Path Sum
* */
public class Q931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix[0].length,
                m = matrix.length;
        int[][] dp = new int[m + 1][n + 2];
        for (int[] arr: dp) {
            Arrays.fill(arr, 0, 1, Integer.MAX_VALUE);
            Arrays.fill(arr, arr.length - 1, arr.length, Integer.MAX_VALUE);
        }


        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + matrix[i - 1][j - 1];
            }
        }

        return Arrays.stream(dp[m]).min().orElse(-1);
    }

    public static void main(String[] args) {
        Q931 solution = new Q931();
        System.out.println(solution.minFallingPathSum(new int[][]{
                new int[]{2, 1, 3},
                new int[]{6, 5, 4},
                new int[]{7, 8, 9}
        }));
    }
}
