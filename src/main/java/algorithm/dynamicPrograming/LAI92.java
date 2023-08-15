package algorithm.dynamicPrograming;

/*
    92. Buy Stock I
    Given an array of positive integers representing a stock’s price on each day.
    On each day you can only make one operation:
        either buy or sell one unit of stock and you can make at most 1 transaction.
    Determine the maximum profit you can make.

    Assumptions
    The given array is not null and is length of at least 2.
    Examples
    {2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4
*/
public class LAI92 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] dp = new int[prices.length];
        dp[0] = 0;

        for (int i = 1, minPrice = prices[0]; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);

            dp[i] = Math.max(dp[i - 1], prices[i] - minPrice);
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        LAI92 solution = new LAI92();
        System.out.println(solution.maxProfit(new int[]{2, 3, 2, 1, 4, 5}));
    }
}
