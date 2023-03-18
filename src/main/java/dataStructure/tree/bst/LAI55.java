package dataStructure.tree.bst;

import dataStructure.tree.BST;

import java.util.ArrayList;
import java.util.List;

/*
 * https://app.laicode.io/app/problem/55
 * Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.
 * get the keys in [2, 5] in ascending order, result is  [3, 4, 5]
 *
 * Corner Cases
 * What if there are no keys in the given range? Return an empty list in this case.
 * How is the binary tree represented?
 * We use the level order traversal sequence with a special symbol "#" denoting the null node.
 */
public class LAI55 extends BST {

    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        getRangeRecursive(root, min, max, result);
        return result;
    }

    private void getRangeRecursive(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) {
            return;
        }

        if (root.val > min) {
            getRangeRecursive(root.left, min, max, result);
        }

        if (root.val >= min && root.val <= max) {
            result.add(root.val);
        }

        if (root.val < max) {
            getRangeRecursive(root.right, min, max, result);
        }
    }

    public static void main(String[] args) {
        LAI55 solution;
        //Input: root = [1,2,3,null,null,4], min = 2, max = 3
        //Output: [2,3]
        solution = BST.parseArray(new int[]{1,2,3, TreeConst.Null.getCode(), TreeConst.Null.getCode(),4}, LAI55.class, TreeConst.Balanced);
        System.out.println(solution.getRange(solution.getRoot(), 2, 3));

        //Input: root = [7,3,16,2,5,11,18,1,null,4,6,null,12,null,20], min = -5, max = -2
        //Output: []
        solution = BST.parseArray(new int[]{7,3,16,2,5,11,18,1, TreeConst.Null.getCode(),4,6, TreeConst.Null.getCode(),12, TreeConst.Null.getCode(),20}, LAI55.class, TreeConst.Balanced);
        System.out.println(solution.getRange(solution.getRoot(), -5, -2));
    }
}
