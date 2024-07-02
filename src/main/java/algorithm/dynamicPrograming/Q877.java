package algorithm.dynamicPrograming;

/*
877. Stone Game
Alice and Bob play a game with piles of stones.
There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones.
The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first.
Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row.
This continues until there are no more piles left, at which point the person with the most stones wins.
Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

Example 1:
Input: piles = [5,3,4,5]
Output: true
Explanation:
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.

Example 2:
Input: piles = [3,7,2,3]
Output: true
* */
public class Q877 {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n]; // DP table to store score differences

        // Base case: When there's only one pile, the difference is the number of stones in that pile.
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Fill the DP table. The gap represents the distance between two pointers.
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                // The current player chooses the pile that gives them the maximum difference.
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
            Q62.log.printDP(dp);
        }

        // The value at the top right corner of the DP table indicates the score difference.
        // If it's positive, the first player (Alice) wins; otherwise, the second player (Bob) wins.
        return dp[0][n - 1] > 0;
    }

    public static void main(String[] args) {
        Q877 solution = new Q877();
        System.out.println(solution.stoneGame(new int[]{1, 2, 3, 4}));
    }
}
