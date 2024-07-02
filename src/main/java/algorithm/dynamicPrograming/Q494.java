package algorithm.dynamicPrograming;

import java.util.Arrays;

/*
494. Target Sum
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-'
before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them
to build the expression "+2-1".

Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1
* */
public class Q494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if(Math.abs(target) > sum) {
            return 0;
        }
        int[][] dp = new int[nums.length][2 * sum + 1];

        dp[0][sum + nums[0]] += 1;
        dp[0][sum - nums[0]] += 1;

        for(int i = 1; i < nums.length; i++){
            for(int j = -sum; j <= sum; j++){
                if(dp[i-1][j+sum] != 0){
                    dp[i][j+sum+nums[i]] += dp[i-1][j+sum];
                    dp[i][j+sum-nums[i]] += dp[i-1][j+sum];
                }
            }
            Q62.log.printDP(dp);
        }
        return dp[nums.length-1][target+sum];
    }

    public static void main(String[] args) {
        Q494 solution = new Q494();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
