class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int a = 0, b = 0;
        for (int i = 2; i <= n; i++) {
            int c = Math.min(b + cost[i - 1], a + cost[i - 2]);
            a = b;
            b = c;
        }
        return b;
    }
}