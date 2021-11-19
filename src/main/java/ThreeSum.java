import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Input: nums = [-4, -1, -1, 0, 1, 2], target = 0
* [[-1, -1, 2], [-1, 0, 1], [-1, 0, 1]]
* Output: [[-1,-1,2],[-1,0,1]]
* */
public class ThreeSum implements InterfA {
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
    public List<List<Integer>> threeSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (nums[j] + nums[k] > target - nums[i]) {
                    k--;
                } else if (nums[j] + nums[k] < target - nums[i]) {
                    j++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        List<List<Integer>> p = solution.threeSum(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        List<List<Integer>> p2 = solution.threeSum2(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        System.out.println(p2);
    }

    @Override
    public void run() {
        ThreeSum solution = new ThreeSum();
        List<List<Integer>> p = solution.threeSum2(new int[]{-4, -1, -1, 0, 1, 2}, 0);
        System.out.println(p);
    }
}
