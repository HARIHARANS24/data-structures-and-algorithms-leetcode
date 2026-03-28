class Solution {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);
        dfs(root, false, 0);
        return max;
    }

    private void dfs(TreeNode node, boolean left, int length) {
        if (node == null) return;
        max = Math.max(max, length);
        if (left) {
            dfs(node.left, false, length + 1);
            dfs(node.right, true, 1);
        } else {
            dfs(node.right, true, length + 1);
            dfs(node.left, false, 1);
        }
    }
}