package dataStructure.tree.bst;

import dataStructure.tree.BST;

/*
* https://leetcode.com/problems/validate-binary-search-tree/
* Given the root of a binary tree, determine if it is a valid binary search tree (BST).
*
* A valid BST is defined as follows:
*  - The left subtree of a node contains only nodes with keys less than the node's key.
*  - The right subtree of a node contains only nodes with keys greater than the node's key.
*  - Both the left and right subtrees must also be binary search trees.
*/
public class Q98 extends BST {

    @Override
    public boolean isValidBST(BST.TreeNode root) {
        return isValidBSTRecursive(root, null, null);
    }

    private boolean isValidBSTRecursive(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return isValidBSTRecursive(root.left, low, root.val) && isValidBSTRecursive(root.right, root.val, high);
    }

    public static void main(String[] args) {
        Q98 solution = new Q98();
        //Input: root = [5,1,4,null,null,3,6]
        //Output: false
        TreeNode root = BST.parseArray(new int[]{5,1,4, TreeConst.Null.getCode(), TreeConst.Null.getCode(),3,6});
        System.out.println(solution.isValidBST(root));

        //Input: root = [2,1,3]
        //Output: true
        root = BST.parseArray(new int[]{2,1,3});
        System.out.println(solution.isValidBST(root));
    }
}
