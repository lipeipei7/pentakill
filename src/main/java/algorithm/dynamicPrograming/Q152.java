package algorithm.dynamicPrograming;
/*
152. Maximum Product Subarray
Given an integer array nums, find a
subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/
public class Q152 {
    public int maxProduct(int[] nums) {
        int minProd = nums[0],
            maxProd = nums[0],
            res = nums[0];


        for (int i = 1; i < nums.length; i++) {
            int minTemp = minProd * nums[i];
            int maxTemp = maxProd * nums[i];

            minProd = Math.min(nums[i], Math.min(minTemp, maxTemp));
            maxProd = Math.max(nums[i], Math.max(minTemp, maxTemp));

            res = Math.max(res, maxProd);
        }

        return res;
    }
}
