package algorithm.dynamicPrograming;

/*
* Number of max consecutive 1s
* GIven a binary number, get the number of max consecutive 1s
*
* example
* arr = [1, 1, 0, 1, 1, 1] res = 3
*
* */
public class Q485 {
    public int getMaxConsecutiveOnes(int[] bin) {
        if (bin == null || bin.length == 0) {
            return 0;
        }

        int max = bin[0];
        int[] dp = new int[bin.length];
        dp[0] = bin[0];

        for (int i = 1; i < bin.length; i++) {
            if (bin[i] == 0) {
                dp[i] = 0;
                continue;
            }
            dp[i] = dp[i - 1] + 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        Q485 solution = new Q485();
        System.out.println(solution.getMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        System.out.println(solution.getMaxConsecutiveOnes(new int[]{0, 1, 0, 1, 1}));
        System.out.println(solution.getMaxConsecutiveOnes(new int[]{0}));
        System.out.println(solution.getMaxConsecutiveOnes(new int[]{1}));
    }
}
