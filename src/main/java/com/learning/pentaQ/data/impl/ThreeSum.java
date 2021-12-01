package com.learning.pentaQ.data.impl;

import com.learning.pentaQ.data.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Input: nums = [-4, -1, -1, 0, 1, 2], target = 0
* [[-1, -1, 2], [-1, 0, 1], [-1, 0, 1]]
* Output: [[-1,-1,2],[-1,0,1]]
* */
public class ThreeSum implements Puzzle {
    /*暴力解法*/
    //time: O(n^3)
    //space: O(1)
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }

    /*双指针*/
    //time: nlogn + n^2 = O(n^2)
    //space: O(logn)
    /*
        创建一个(公开的)函数threeSum, 给他输入两个参数，
        第一个是一个数组nums，含有我们用来找3和的整数，
        另一个是一个整数target, 他是三和的目标，
        让这个函数返回一个列表的列表
    */
    List<List<Integer>> threeSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>(); //创建一个新的列表，用来当做返回值
        Arrays.sort(nums); //从小到大排序参数数组nums

        //初始化一个序号i等于0，用来指向第一个数字，i<nums的长度-2因为我们需要至少三个数字来求3和，每次循环i++
        for (int i = 0; i < nums.length - 2; i++) {
            //这一段用来去掉重复的答案，当i>0并且i所在的数字和i左边的数字相等时，跳过循环
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //这一段是用来做剩下两个数字的2和，初始化序号j到i的右边一个，初始化k到nums的最后一个位置，每当j<k就进入这个循环
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                //当j和k所在的数字相加大于目标数减去i所在的数字时
                if (nums[j] + nums[k] > target - nums[i]) {
                    k--;
                } else if (nums[j] + nums[k] < target - nums[i]) {
                    j++;
                } else {
                    //当我们找到3和时，添加这些数字进res，然后j往右，k往左移动一位
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    //这一段也是用来去重，当j和k更新后，检查一下j<k满不满足，如果满足并且j和j左边所指向的数字相同，j继续往右移动一位
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }

        return res; // 返回res列表
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        //List<List<Integer>> p = solution.threeSum(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        List<List<Integer>> p2 = solution.threeSum2(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        System.out.println(p2);
    }

    @Override
    public String run() {
        ThreeSum solution = new ThreeSum();
        List<List<Integer>> p = solution.threeSum2(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        return p.toString();
    }
}
