package algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
    231. Combination Sum II
    Given a collection of candidate numbers (C) and a target number (T),
    find all unique combinations in C where the candidate numbers sums to T.
    Each number in C may only be used once in the combination.

    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order.
    The solution set must not contain duplicate combinations.

    Example
    given candidate set 10,1,2,7,6,1,5 and target 8,
     A solution set is:
    [1, 7]
    [1, 2, 5]
    [2, 6]
    [1, 1, 6]
* */
public class LAI231 {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(num);
        dfs(0, target, num, new ArrayList<>(), res);
        return new ArrayList<>(new HashSet<>(res));
    }

    private void dfs(int index, int target, int[] num, ArrayList<Integer> current, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> ret = new ArrayList<>(current);
            Collections.sort(ret);
            res.add(ret);
            return;
        }

        for (int i = index; i < num.length; i++) {
            if (i > index && num[i] == num[i - 1]) {
                continue;
            }

            if (num[i] > target) {
                break;
            }

            current.add(num[i]);
            dfs(index + 1, target - num[i], num, current, res);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        LAI231 solution = new LAI231();
//        System.out.println(solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(solution.combinationSum2(new int[]{1,5,1,7,2,4,5,9,5,6,7,7,8,11,14}, 18));
    }


}
