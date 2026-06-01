class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);

        int ans = 0;
        int n = cost.length;

        for (int i = n - 1, cnt = 0; i >= 0; i--, cnt++) {
            if (cnt % 3 == 2) continue;
            ans += cost[i];
        }

        return ans;
    }
}