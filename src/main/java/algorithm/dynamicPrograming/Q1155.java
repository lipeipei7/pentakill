package algorithm.dynamicPrograming;
/*
1155. Number of Dice Rolls With Target Sum
You have n dice, and each dice has k faces numbered from 1 to k.

Given three integers n, k, and target,
return the number of possible ways (out of the kn total ways) to roll the dice,
so the sum of the face-up numbers equals target.
Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:
Input: n = 1, k = 6, target = 3
Output: 1
Explanation: You throw one die with 6 faces.
There is only one way to get a sum of 3.

Example 2:
Input: n = 2, k = 6, target = 7
Output: 6
Explanation: You throw two dice, each with 6 faces.
There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.

Example 3:
Input: n = 30, k = 30, target = 500
Output: 222616187
Explanation: The answer must be returned modulo 10^9 + 7.
* */
public class Q1155 {
    public int numRollsToTarget(int n, int k, int target) {
        int MOD = (int) (Math.pow(10, 9) + 7);
        int[][] dp = new int[n + 1][target + 1];

        // Base case: there's one way to get a sum of 0 using 0 dice
        dp[0][0] = 1;

        // Iterate through the number of dice
        for (int i = 1; i <= n; i++) {
            // Iterate through the possible sums
            for (int j = 1; j <= target; j++) {
                // Iterate through the possible outcomes of one dice
                for (int l = 1; l <= k; l++) {
                    if (j >= l) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - l]) % MOD;
                    }
                }
            }
        }

        Q62.log.printDP(dp);
        return dp[n][target];
    }

    public static void main(String[] args) {
        Q1155 solution = new Q1155();
        System.out.println(solution.numRollsToTarget(2, 6, 7));
    }
}
