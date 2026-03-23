class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long MOD = 1_000_000_007;
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];

        maxDp[0][0] = minDp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++)
            maxDp[i][0] = minDp[i][0] = maxDp[i-1][0] * grid[i][0];
        for (int j = 1; j < n; j++)
            maxDp[0][j] = minDp[0][j] = maxDp[0][j-1] * grid[0][j];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long v = grid[i][j];
                if (v >= 0) {
                    maxDp[i][j] = Math.max(maxDp[i-1][j], maxDp[i][j-1]) * v;
                    minDp[i][j] = Math.min(minDp[i-1][j], minDp[i][j-1]) * v;
                } else {
                    maxDp[i][j] = Math.min(minDp[i-1][j], minDp[i][j-1]) * v;
                    minDp[i][j] = Math.max(maxDp[i-1][j], maxDp[i][j-1]) * v;
                }
            }
        }

        long result = maxDp[m-1][n-1];
        return result < 0 ? -1 : (int)(result % MOD);
    }
}