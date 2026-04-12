class Solution {
    int prev = -1;
    int ans = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return ans;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != -1) {
            ans = Math.min(ans, node.val - prev);
        }
        prev = node.val;

        inorder(node.right);
    }
}