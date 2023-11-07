package algorithm.arrays;

import java.util.HashSet;
import java.util.Set;

/*
    128. Longest Consecutive Sequence
    Given an unsorted array of integers nums,
    return the length of the longest consecutive elements sequence.
    You must write an algorithm that runs in O(n) time.

    Example 1:
    Input: nums = [100,4,200,1,3,2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    Example 2:
    Input: nums = [0,3,7,2,5,8,4,6,0,1]
    Output: 9

*/
public class Q128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int n: nums) {
            numSet.add(n);
        }

        int longest = Integer.MIN_VALUE;
        for (int n: nums) {
            if (numSet.contains(n - 1)) {
                continue;
            }
            for (int count = 0; numSet.contains(n + count); count++) {
                longest = Math.max(longest, count + 1);
            }
        }

        return longest;
    }
}
