import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Twosum {
    /*
 2 -> 0
 7 -> 1
 */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j };
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Twosum solution = new Twosum();
        int[] arr = solution.twoSum(new int[]{1,4, 1, 5}, 6);
        System.out.println(Arrays.toString(arr));
    }
}
