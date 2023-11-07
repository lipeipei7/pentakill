package algorithm.binarySearch;
/*
    153. Find Minimum in Rotated Sorted Array
    33. Search in Rotated Sorted Array

    Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
    For example, the array nums = [0,1,2,4,5,6,7] might become:
        [4,5,6,7,0,1,2] if it was rotated 4 times.
        [0,1,2,4,5,6,7] if it was rotated 7 times.
    Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results
    in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

    Given the sorted rotated array nums of unique elements, return the minimum element of this array.
    You must write an algorithm that runs in O(log n) time.

    Example 1:
    Input: nums = [3,4,5,1,2]
    Output: 1
    Explanation: The original array was [1,2,3,4,5] rotated 3 times.

    Example 2:
    Input: nums = [4,5,6,7,0,1,2]
    Output: 0
    Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

    Example 3:
    Input: nums = [11,13,15,17]
    Output: 11
    Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
*/
public class Q153 {
    public int findMinIndex(int[] nums) {
        int i = 0;
        for (int j = nums.length - 1, mid = i + (j - i) / 2; i < j; mid = i + (j - i) / 2) {
            if (nums[mid] < nums[j]) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    public int search(int[] nums, int target) {
        int minIndex = findMinIndex(nums);
        return Math.max(helper(nums, target, 0, minIndex - 1),
                helper(nums, target, minIndex, nums.length - 1));
    }

    private int helper(int[] nums, int target, int start, int end) {
        for (int i = start, j = end, mid = i + (j - i) / 2; i <= j; mid = i + (j - i) / 2) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }
}
