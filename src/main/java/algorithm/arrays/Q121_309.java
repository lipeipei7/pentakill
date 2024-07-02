package algorithm.arrays;

import algorithm.dynamicPrograming.Q62;

/*
121. Best Time to Buy and Sell Stock

You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day
to buy one stock and choosing a different day in the future to sell that stock.
Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

Example 2:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
*/
/*
Follow Up
309. Best Time to Buy and Sell Stock with Cooldown

You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve.

You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times)
with the following restrictions:
    After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0

*/
public class Q121_309 {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0, j = i + 1; j < prices.length; j++) {
            res = Math.max(res, prices[j] - prices[i]);

            if (prices[i] > prices[j]) {
                i = j;
            }
        }
        return res;
    }

    public int maxProfitCoolDown(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][3];

        // Initial conditions
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
            Q62.log.printDP(dp);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }

    public int maxProfitCoolDownFSM(int[] p) {
        int n = p.length;
        if (n < 2) return 0;
        int h = -p[0], s = 0, c = 0; // state after buy(h for hold), sell, cool down

        for (int x : p) {
            h = Math.max(h, c - x);
            c = s;
            s = Math.max(s, h + x);
        }

        return s;
    }


    public static void main(String[] args) {
        Q121_309 solution = new Q121_309();
        System.out.println(solution.maxProfitCoolDown(new int[]{1, 2, 3, 0, 2}));
    }
}
