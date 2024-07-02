package algorithm.dynamicPrograming;

/*
647. Palindromic Substrings
Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/
public class Q647 {
    //2D DP
    public int countSubstrings(String s) {
        int n = s.length(),
            count = 0;
        boolean[][] dp = new boolean[n][n];
        for (int k = n - 1; k >= 0; k--) {
            for (int j = k; j < n; j++) {
                if (s.charAt(j) == s.charAt(k) && (j - k < 2 || dp[k + 1][j - 1])) {
                    dp[k][j] = true;
                    count++;
                }
            }
        }

        return count;
    }


    public int countSubstrings2(String s) {
        int n = s.length(), count = s.length();
        boolean[][] dp = new boolean[n][n];
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
                    count++;
                }
            }

        }

        return count;
    }


    public static void main(String[] args) {
        Q647 solution = new Q647();
        int res = solution.countSubstrings2("fdsklf");
        assert res == 6;

        res = solution.countSubstrings2("aaa");
        assert res == 6;
    }
}
