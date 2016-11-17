import java.util.HashSet;
import java.util.Set;


public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public int minDepth(TreeNode root) {
        Set<TreeNode> current = new HashSet<>();
        if (root != null) {
            current.add(root);
        }
        int depth = 0;

        while (!current.isEmpty()) {
            depth += 1;
            Set<TreeNode> next = new HashSet<>();
            for (TreeNode node : current) {
                if (node != null && node.left != null) {
                    next.add(node.left);
                }
                if (node != null && node.right != null) {
                    next.add(node.right);
                }
            }
            current = next;
        }
        return depth;
    }
}