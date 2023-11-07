package algorithm.dynamicPrograming;


/*
    160. Climbing Stairs
    There are in total n steps to climb until you can reach the top.
    You can climb 1 or 2 steps a time. Determine the number of ways you can do that.

    Example:
    Input: n = 4, Return 5.
*/
public class LAI160 {
    public int stairs(int n) {
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
