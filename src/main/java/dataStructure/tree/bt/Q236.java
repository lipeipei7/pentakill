package dataStructure.tree.bt;

import dataStructure.tree.BST.TreeNode;

/*
* 236. Lowest Common Ancestor of a Binary Tree
* https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
*
* Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
*
* According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
* as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
*/
public class Q236 {
    TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorRecursive(root, p, q);
        return res;
    }

    private boolean lowestCommonAncestorRecursive(TreeNode curr, TreeNode p, TreeNode q) {
        if(curr == null) {
            return false;
        }

        boolean left = lowestCommonAncestorRecursive(curr.left, p, q);
        boolean right = lowestCommonAncestorRecursive(curr.right, p, q);
        boolean self = curr.equals(p) || curr.equals(q);

        if ((left && right) || (left && self) || (right && self)) {
            res = curr;
        }

        return left || right || self;
    }
}
