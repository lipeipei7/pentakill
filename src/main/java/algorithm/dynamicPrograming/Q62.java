package algorithm.dynamicPrograming;

import java.util.Arrays;
import java.util.stream.IntStream;

/*
62. Unique Paths
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
Given the two integers m and n,
return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * 109.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
* */
public class Q62 {
    public static Q62 log = new Q62();
    public void printDP(int[][] dp) {
        String str = Arrays.deepToString(dp);
        System.out.println(str.replaceAll("], \\[", "],\n ["));
        System.out.println(" ");
    }
    public void printDP(boolean[][] dp) {
        int[][] intArray = Arrays.stream(dp).
                map(booleans -> IntStream.range(0, booleans.length)
                        .boxed().mapToInt(j -> booleans[j] ? 1 : 0)
                        .toArray())
                .toArray(int[][]::new);
        printDP(intArray);

    }


    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] arr: dp) {
            Arrays.fill(arr, 0, 1, 1);
        }
        Arrays.fill(dp[0], 1);
        Q62.log.printDP(dp);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                Q62.log.printDP(dp);
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Q62 solution = new Q62();
        System.out.println(solution.uniquePaths(3, 7));
    }
}
