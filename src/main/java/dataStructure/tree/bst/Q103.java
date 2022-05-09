package dataStructure.tree.bst;

import dataStructure.tree.BST.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
* https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
* Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
* (i.e., from left to right, then right to left for the next level and alternate between).
*/
public class Q103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> resLevel = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode levelNode = new TreeNode(-1);
        boolean fromLeft = true;

        if (root == null) {
            return res;
        }

        q.offerLast(root);
        q.offerLast(levelNode);
        while(q.size() > 0) {
            TreeNode curr = q.pollFirst();
            if (curr.equals(levelNode)) {
                res.add(resLevel);
                resLevel = new ArrayList<>();
                fromLeft = !fromLeft;
                if (q.size() > 0) {
                    q.offerLast(levelNode);
                }
                continue;
            }

            if (fromLeft) {
                resLevel.add(curr.val);
            } else {
                resLevel.add(0, curr.val);
            }

            if (curr.left != null) {
                q.offerLast(curr.left);
            }
            if (curr.right != null) {
                q.offerLast(curr.right);
            }
        }

        return res;
    }
}
