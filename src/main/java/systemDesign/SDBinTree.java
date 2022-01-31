package systemDesign;


import java.util.*;

public class SDBinTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                q.offer(curr.left);
                q.offer(curr.right);
                sb.append(curr.val)
                        .append(",");
            } else {
                sb.append("null")
                        .append(",");
            }
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] splitData = data.split(",");

        if (splitData.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(splitData[0]));
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offerFirst(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.pollLast();
            if (!"null".equals(splitData[i])) {
                node.left = new TreeNode(Integer.parseInt(splitData[i]));
                q.offerFirst(node.left);
            }
            if (!"null".equals(splitData[i + 1])) {
                node.right = new TreeNode(Integer.parseInt(splitData[i + 1]));
                q.offerFirst(node.right);
            }
            i += 2;
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
