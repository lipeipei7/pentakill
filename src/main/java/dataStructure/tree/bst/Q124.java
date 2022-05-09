package dataStructure.tree.bst;

import dataStructure.tree.BST.TreeNode;

/*
* 124. Binary Tree Maximum Path Sum [Hard]
* https://leetcode.com/problems/binary-tree-maximum-path-sum/
*
* A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
* A node can only appear in the sequence at most once.
* Note that the path does not need to pass through the root.
*
* The path sum of a path is the sum of the node's values in the path.
*
* Given the root of a binary tree, return the maximum path sum of any non-empty path.
*/
public class Q124 {
    private int maxPathSumRec(TreeNode curr, int[] max) {
        if (curr == null) {
            return 0;
        }

        int leftGain = Math.max(maxPathSumRec(curr.left, max), 0);
        int rightGain = Math.max(maxPathSumRec(curr.right, max), 0);

        max[0] = Math.max(max[0], curr.val + leftGain + rightGain);

        return curr.val + Math.max(leftGain, rightGain);
    }

    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{Integer.MIN_VALUE};
        maxPathSumRec(root, res);
        return res[0];
    }
}
