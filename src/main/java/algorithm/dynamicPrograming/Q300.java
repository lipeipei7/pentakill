package algorithm.dynamicPrograming;

import java.util.Arrays;

/*
300. Longest Increasing Subsequence
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1
*/
public class Q300 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int h = 1; h < nums.length; h++) {
            for (int l = 0; l < h; l++) {
                if (nums[l] < nums[h]) {
                    dp[h] = Math.max(dp[h], dp[l] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    public int lengthOfLISNLogN(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int length = 0; // Length of the tails array

        for (int num : nums) {
            int i = binarySearch(dp, num, length); //larger number index
            dp[i] = num; //maintain sorted array
            if (i == length) {
                length++;
            }
        }

        return length;
    }

    private int binarySearch(int[] dp, int target, int length) {
        int l = 0, r = length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (dp[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }

    public static void main(String[] args) {
        Q300 solution = new Q300();
        System.out.println(solution.lengthOfLISNLogN(new int[]{1, 2, 3, 100, 0, 1, 2, 3, 4}));
    }
}
