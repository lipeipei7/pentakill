package algorithm.dynamicPrograming;

import java.util.Arrays;

public class QXXX {

    public int getNumRoutes(int startRow, int startColumn, int endRow, int endColumn) {
        int[][] dp = new int[endRow][endColumn];

        Arrays.fill(dp[0], 1);

        for (int r = 1, c = 0; r < dp.length; r++) {
            for (c = 0; c < dp[0].length; c++) {
                if (c == 0) {
                    dp[r][c] = 1;
                    continue;
                }
                dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
            }

        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String[] args) {
        QXXX solution = new QXXX();
        System.out.println(solution.getNumRoutes(0, 0, 3, 5));
    }
}
