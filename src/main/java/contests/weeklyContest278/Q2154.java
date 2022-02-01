package contests.weeklyContest278;

import java.util.HashSet;
import java.util.Set;

/*
* https://leetcode.com/contest/weekly-contest-278/problems/keep-multiplying-found-values-by-two/
* You are given an array of integers nums.
* You are also given an integer original which is the first number that needs to be searched for in nums.
*
* You then do the following steps:
* - If original is found in nums, multiply it by two (i.e., set original = 2 * original).
* - Otherwise, stop the process.
* - Repeat this process with the new number as long as you keep finding the number.
* - Return the final value of original.
*/
public class Q2154 {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> numsSet = new HashSet<>();
        for (int n: nums) {
            numsSet.add(n);
        }
        while (numsSet.contains(original)) {
            original *= 2;
        }

        return original;
    }


    public static void main(String[] args) {
        Q2154 solution = new Q2154();
        //nums = [5,3,6,1,12], original = 3
        System.out.println(solution.findFinalValue(new int[]{5, 3, 6, 1, 12}, 3));
        //nums = [2,7,9], original = 4
        System.out.println(solution.findFinalValue(new int[]{2,7,9}, 4));
    }
}
