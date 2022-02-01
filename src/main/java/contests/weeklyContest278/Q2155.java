package contests.weeklyContest278;

import java.util.ArrayList;
import java.util.List;

/*
* https://leetcode.com/contest/weekly-contest-278/problems/all-divisions-with-the-highest-score-of-a-binary-array/
*You are given a 0-indexed binary array nums of length n.
* nums can be divided at index i (where 0 <= i <= n) into two arrays (possibly empty) numsleft and numsright:
*
* numsleft has all the elements of nums between index 0 and i - 1 (inclusive),
* while numsright has all the elements of nums between index i and n - 1 (inclusive).
* If i == 0, numsleft is empty, while numsright has all the elements of nums.
* If i == n, numsleft has all the elements of nums, while numsright is empty.
* The division score of an index i is the sum of the number of 0's in numsleft and the number of 1's in numsright.
*
* Return all distinct indices that have the highest possible division score. You may return the answer in any order.
*/
public class Q2155 {
    public List<Integer> maxScoreIndices(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int maxScore = -1;
        int onesToRight, zeroesToLeft, currScore;

        for (int i = 0; i < nums.length + 1; i++) {
            onesToRight = prefix[nums.length] - prefix[i];
            zeroesToLeft = i - prefix[i];
            currScore = onesToRight + zeroesToLeft;

            if (currScore > maxScore) {
                res.clear();
                maxScore = currScore;
            }

            if (currScore == maxScore) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q2155 solution = new Q2155();
        //nums = [0,0,1,0] Output: [2,4]
        System.out.println(solution.maxScoreIndices(new int[]{0,0,1,0}));
        //nums = [0,0,0] Output: [3]
        System.out.println(solution.maxScoreIndices(new int[]{0,0,0}));
        //nums = [1,1] Output: [0]
        System.out.println(solution.maxScoreIndices(new int[]{1,1}));

    }
}
