package algorithm.dynamicPrograming;

import java.util.Arrays;

/*
72. Edit Distance
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
* */
public class Q72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(),
                n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
                //print dp
                System.out.println(Arrays.deepToString(dp));


            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        Q72 solution = new Q72();
        System.out.println(solution.minDistance("Zhac", "Zach"));

//        System.out.println(solution.minDistance("Zoom", "Zoom"));
    }
}

/*
[[0, 1, 2, 3, 4],
 [1, 0, 0, 0, 0],
 [2, 0, 0, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 0, 0],
 [2, 0, 0, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 0],
 [2, 0, 0, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 0, 0, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 0, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 0, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 0],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 0, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 0, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 0, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 0],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 3],
 [4, 0, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 3],
 [4, 3, 0, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 3],
 [4, 3, 2, 0, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 3],
 [4, 3, 2, 1, 0]]
[[0, 1, 2, 3, 4],
 [1, 0, 1, 2, 3],
 [2, 1, 1, 2, 2],
 [3, 2, 1, 2, 3],
 [4, 3, 2, 1, 2]]
 */
