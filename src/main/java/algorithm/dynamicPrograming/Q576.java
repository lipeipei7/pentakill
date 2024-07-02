package algorithm.dynamicPrograming;

/*
576. Out of Boundary Paths
There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn].
You are allowed to move the ball to one of the four adjacent cells in the grid
(possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn,
return the number of paths to move the ball out of the grid boundary.
Since the answer can be very large, return it modulo 10^9 + 7.

Example 1:
Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
Output: 6

Example 2:
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
* */
public class Q576 {
    private static final int MOD = (int) (Math.pow(10, 9) + 7);
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Integer[][][] memo = new Integer[m][n][maxMove + 1];
        return dfs(m, n, maxMove, startRow, startColumn, memo);
    }

    private int dfs(int m, int n, int maxMove, int row, int col, Integer[][][] memo) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }
        if (maxMove <= 0) {
            return 0;
        }
        if (memo[row][col][maxMove] != null) {
            return memo[row][col][maxMove];
        }

        int res = 0;
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            res = (res + dfs(m, n, maxMove - 1, newRow, newCol, memo)) % MOD;
        }

        memo[row][col][maxMove] = res;
        return res;
    }
}
