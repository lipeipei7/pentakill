package algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/*
    232. Combination Sum
    Given a collection of candidate numbers (C) and a target number (T),
    find all unique combinations in C where the candidate numbers sums to T.
    The same repeated number may be chosen from C unlimited number of times.
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order.
    The solution set must not contain duplicate combinations.

    Example
    given candidate set 2,3,6,7 and target 7,
    A solution set is:
     [7]
     [2, 2, 3]
*/
public class LAI232 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, ArrayList<Integer> current, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        LAI232 solution = new LAI232();
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
