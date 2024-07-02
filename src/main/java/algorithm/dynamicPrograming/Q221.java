package algorithm.dynamicPrograming;

/*
221. Maximal Square
Given an m x n binary matrix filled with 0's and 1's,
find the largest square containing only 1's and return its area.

Example 1:
Input: matrix = [["1","0","1","0","0"],
                 ["1","0","1","1","1"],
                 ["1","1","1","1","1"],
                 ["1","0","0","1","0"]]
Output: 4

Example 2:
Input: matrix = [["0","1"],
                 ["1","0"]]
Output: 1

Example 3:
Input: matrix = [["0"]]
Output: 0


* */
public class Q221 {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length,
                m = matrix[0].length,
                max = 0;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
//        Q62.log.printDP(dp);

        return max * max;
    }

    public static void main(String[] args) {
        Q221 solution = new Q221();
        System.out.println(solution.maximalSquare(new char[][]{
                new char[]{'1', '1', '1', '1'},
                new char[]{'0', '1', '1', '0'},
        }));
    }
}
