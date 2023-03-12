package dataStructure.tree.bst;

import dataStructure.tree.BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
* https://leetcode.com/problems/binary-tree-level-order-traversal/
* Given the root of a binary tree,
* return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
*/
public class Q102 extends BST {

    @Override
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        levelOrderTraversalRecursive(root, 0, levels);
        return levels;
    }

    private void levelOrderTraversalRecursive(TreeNode node, int level, List<List<Integer>> levels) {
        if (node == null) {
            return;
        }

        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }

        levels.get(level).add(node.val);
        levelOrderTraversalRecursive(node.left, level + 1, levels);
        levelOrderTraversalRecursive(node.right, level + 1, levels);
    }

    private List<List<Integer>> levelOrderTraversalIterative(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
            // start the current level
            levels.add(new ArrayList<>());

            // number of elements in the current level
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }

    public static void main(String[] args) {
        Q102 solution;
        //Input: root = [3,9,20,null,null,15,7]
        //Output: [[3],[9,20],[15,7]]
        solution = BST.parseArray(new int[]{3,9,20, TreeConst.Null.getCode(), TreeConst.Null.getCode(), 15, 7}, Q102.class);
        System.out.println(solution.levelOrder(solution.getRoot()));
    }
}
