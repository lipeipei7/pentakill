package dataStructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class BST {

    public enum TreeConst {
        Null;

        public int getCode() {
            if (this == TreeConst.Null) {
                return 0xdead_beef;
            }
            return this.ordinal();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    public static TreeNode parseArray(int[] input) {
        return parseArrayRecursive(input, 0);
    }

    private static TreeNode parseArrayRecursive(int[] input, int i) {
        if (i >= input.length) {
            return null;
        }

        return input[i] == TreeConst.Null.getCode() ?
                null :
                new TreeNode(input[i],
                        parseArrayRecursive(input, 2 * i + 1),
                        parseArrayRecursive(input, 2 * i + 2));
    }

    public boolean isValidBST(TreeNode root) {
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return true;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        return null;
    }
}
