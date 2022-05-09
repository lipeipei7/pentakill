package dataStructure.tree.bt;

import dataStructure.tree.BST.TreeNode;

/*
* 543. Diameter of Binary Tree
* https://leetcode.com/problems/diameter-of-binary-tree/
*
* Given the root of a binary tree, return the length of the diameter of the tree.
*
* The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
* This path may or may not pass through the root.
* The length of a path between two nodes is represented by the number of edges between them.
*/
public class Q543 {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeRecursive(root);
        return res;
    }

    private int diameterOfBinaryTreeRecursive(TreeNode curr) {
        if (curr == null) {
            return 0;
        }

        int left = diameterOfBinaryTreeRecursive(curr.left);
        int right = diameterOfBinaryTreeRecursive(curr.right);

        res = Math.max(res, left + right);

        return Math.max(left, right) + 1;
    }
}
