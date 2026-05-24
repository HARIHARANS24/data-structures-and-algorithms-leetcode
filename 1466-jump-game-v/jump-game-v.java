class Solution {
    int[] dp;
    int[] arr;
    int d, n;

    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.d = d;
        n = arr.length;

        dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i));
        }

        return ans;
    }

    int dfs(int i) {
        if (dp[i] != 0) return dp[i];

        int best = 1;

        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(j));
        }

        for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(j));
        }

        return dp[i] = best;
    }
}