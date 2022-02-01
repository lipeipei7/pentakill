package dataStructures.tree.bst;

import dataStructures.tree.BST;

import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode.com/problems/symmetric-tree/
* Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
*/
public class Q101 extends BST {

    @Override
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursive(root, root);
    }

    //O(n) O(n)
    private boolean isSymmetricRecursive(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) &&
                isSymmetricRecursive(t1.right, t2.left) &&
                isSymmetricRecursive(t1.left, t2.right);
    }

    //O(n) O(n)
    private boolean isSymmetricBFS(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        Q101 solution = new Q101();
        //Input: root = [1,2,2,3,4,4,3]
        //Output: true
        TreeNode root = BST.parseArray(new int[]{1,2,2,3,4,4,3});
        System.out.println(solution.isSymmetric(root));

        //Input: root = [1,2,2,null,3,null,3]
        //Output: false
        root = BST.parseArray(new int[]{1,2,2,TreeConst.Null.getCode(),3,TreeConst.Null.getCode(),3});
        System.out.println(solution.isSymmetric(root));
    }

}
