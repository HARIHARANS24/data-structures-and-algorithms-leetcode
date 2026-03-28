class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum, new java.util.HashMap<Long, Integer>(), 0);
    }

    private int dfs(TreeNode node, int target, java.util.Map<Long, Integer> map, long sum) {
        if (node == null) return 0;
        sum += node.val;
        int count = 0;
        if (sum == target) count++;
        count += map.getOrDefault(sum - target, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += dfs(node.left, target, map, sum);
        count += dfs(node.right, target, map, sum);
        map.put(sum, map.get(sum) - 1);
        return count;
    }
}