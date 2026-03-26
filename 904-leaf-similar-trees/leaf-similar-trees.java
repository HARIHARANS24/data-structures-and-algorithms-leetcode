import java.util.*;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        dfs(root1, l1);
        dfs(root2, l2);
        return l1.equals(l2);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        dfs(node.left, list);
        dfs(node.right, list);
    }
}