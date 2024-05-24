package algorithm.arrays;

/*
42. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
*/
public class Q42 {
    public int trap(int[] height) {
        return 0;

    }

    //return start and end index of ascending array
    private int[] getAscending(int[] height, int start) {
        while (height[start] == height[start + 1]) {
            start++;
        }

        for (int i = start + 1; i < height.length; i++) {
            if (height[i] < height[i - 1]) {
                return new int[]{start, i - 1};
            }
        }
        return new int[]{start, start};
    }

    //return start and end index of descending array
    private int[] getDescending(int[] height, int start) {
        while (height[start] == height[start + 1]) {
            start++;
        }

        for (int i = start + 1; i < height.length; i++) {
            if (height[i] > height[i - 1]) {
                return new int[]{start, i - 1};
            }
        }
        return new int[]{start, start};
    }

    //calculate the water trapped between start and end index
    private int calculateWater(int[] height, int start, int end) {
        int min = Math.min(height[start], height[end]);
        int water = 0;
        for (int i = start + 1; i < end; i++) {
            water += min - height[i];
        }
        return water;
    }
}
