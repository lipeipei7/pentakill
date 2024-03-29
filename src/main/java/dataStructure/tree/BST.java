package dataStructure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Data
public abstract class BST {

    private TreeNode root;

    public enum TreeConst {
        Null,
        Balanced,
        Simple;

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

    public static<T> T parseArray(int[] input, Class<T> clazz) {
        return parseArray(input, clazz, TreeConst.Simple);
    }

    public static<T> T parseArray(int[] input, Class<T> clazz, TreeConst type) {
        TreeNode root;
        if (type == TreeConst.Balanced) {
            root = parseArrayRecursive(input, 0, input.length - 1);
        } else {
            root = parseArrayRecursive(input, 0);
        }

        try {
            T newInstance = clazz.getDeclaredConstructor().newInstance();
            newInstance.getClass().getMethod("setRoot", TreeNode.class).invoke(newInstance, root);
            return newInstance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    // Simpler array-based tree parser, root at index 0
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

    // Balanced array-based tree parser, root at middle
    private static TreeNode parseArrayRecursive(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        if (arr[mid] == TreeConst.Null.getCode()) {
            return null;
        }
        TreeNode node = new TreeNode(arr[mid]);

        node.left = parseArrayRecursive(arr, start, mid - 1);
        node.right = parseArrayRecursive(arr, mid + 1, end);

        return node;
    }

    public TreeNode insert(int val) {
        return insertRecursive(this.root, val);
    }

    private TreeNode insertRecursive(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insertRecursive(root.left, val);
        } else if (val > root.val) {
            root.right = insertRecursive(root.right, val);
        } else {
            return root;
        }

        return root;
    }

    public TreeNode find(int val) {
        return findRecursive(this.root, val);
    }

    private TreeNode findRecursive(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            return findRecursive(root.left, val);
        } else if (val > root.val) {
            return findRecursive(root.right, val);
        } else {
            return root;
        }
    }

    public TreeNode delete(int val) {
        return deleteRecursive(this.root, val);
    }

    private TreeNode deleteRecursive(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (val < root.val) {
            root.left = deleteRecursive(root.left, val);
        } else if (val > root.val) {
            root.right = deleteRecursive(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val = findMinRecursive(root.right);
            root.right = deleteRecursive(root.right, root.val);
        }

        return root;
    }

    private int findMinRecursive(TreeNode root) {
        return root.left == null ?
                root.val :
                findMinRecursive(root.left);
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
