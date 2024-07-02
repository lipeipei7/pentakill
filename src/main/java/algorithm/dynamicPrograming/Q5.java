package algorithm.dynamicPrograming;

import java.util.Arrays;

public class Q5 {


    public String longestPalindromeDP(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        Boolean[][] dp = new Boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //even will look up for previous even, likewise for odd
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
                System.out.println(Arrays.deepToString(dp).replaceAll("], ", "],\n "));
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindromeDP2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLen = 1, startIdx = 0, n = s.length();
        Boolean[][] dp = new Boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = 0; i < n; i++) {
                j = i + len - 1;
                if (j >= n) {
                    break;
                }

                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }

                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    startIdx = i;
                }
            }

        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    public static void main(String[] args) {
        Q5 solution = new Q5();
        System.out.println(solution.longestPalindromeDP("abba"));
    }
}
