package algorithm.dynamicPrograming;

import java.util.Arrays;

public class Q2370 {
    public int longestIdealString(String s, int k) {
        int[] dp = new int[26];
        int ans = 1;
        for (int i = 0; i < s.length(); i++) {
            int ele = s.charAt(i) - 'a';
            for (int j = ele; j >= 0 && j >= ele - k; j--) {
                dp[ele] = Math.max(dp[ele], dp[j] + 1);
            }
            System.out.println(Arrays.toString(dp));
            for (int j = ele + 1; j < 26 && j <= ele + k; j++) {
                dp[ele] = Math.max(dp[ele], dp[j] + 1);
            }
            System.out.println(Arrays.toString(dp));
            ans = Math.max(ans, dp[ele]);
        }
        System.out.println(Arrays.toString(dp));
        return ans;
    }

    public static void main(String[] args) {
        Q2370 solution = new Q2370();
        System.out.println(solution.longestIdealString("acfgbd", 2));
    }
}
