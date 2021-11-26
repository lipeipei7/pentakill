package com.learning.pentaQ.data.impl;

import com.learning.pentaQ.data.Puzzle;

import java.util.Arrays;

/*
     2 -> 0
     7 -> 1
*/
public class TwoSum implements Puzzle {
    /*暴力解法*/
    //time: O(n^2)
    //space: O(1)
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    /*双指针*/
    //time: nlogn + n = O(nlogn)
    //space: O(logn)
    public int[] twoSum2(int[] nums, int target) {
        //time: O(nlogn)
        //space: O(logn)
        Arrays.sort(nums);
        //time: O(n)
        //space: O(1)
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] > target) {
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                return new int[]{i, j};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] arr = solution.twoSum2(new int[]{1, 4, 3, 5}, 5);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public String run() {
        TwoSum solution = new TwoSum();
        int[] arr = solution.twoSum2(new int[]{1, 4, 3, 5}, 5);
        return Arrays.toString(arr);
    }
}
