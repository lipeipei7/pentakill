package algorithm.dynamicPrograming;

import java.util.Arrays;

/*
322. Coin Change
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1

Example 3:
Input: coins = [1], amount = 0
Output: 0

* */
public class Q322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int coin: coins) {
                if (coin > i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[dp.length - 1] == Integer.MAX_VALUE || dp[dp.length - 1] < 0) {
            return -1;
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        Q322 solution = new Q322();
        //solution.coinChange(new int[]{186,419,83,408}, 6249);
        solution.coinChange(new int[]{1, 2, 5}, 11);
    }
}
