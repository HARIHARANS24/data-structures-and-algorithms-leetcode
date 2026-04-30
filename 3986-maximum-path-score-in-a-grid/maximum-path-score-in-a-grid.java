class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][k + 1];
        
        for (int j = 0; j < n; j++) {
            for (int c = 0; c <= k; c++) {
                dp[j][c] = -1;
            }
        }
        
        dp[0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            int[][] next = new int[n][k + 1];
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    next[j][c] = -1;
                }
            }
            
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                int cost = val == 0 ? 0 : 1;
                int score = val;
                
                for (int c = 0; c <= k; c++) {
                    if (dp[j][c] == -1) continue;
                    
                    if (j == 0 && i == 0) {
                        next[j][c] = Math.max(next[j][c], dp[j][c]);
                        continue;
                    }
                    
                    if (c + cost <= k) {
                        next[j][c + cost] = Math.max(next[j][c + cost], dp[j][c] + score);
                    }
                }
                
                if (j > 0) {
                    for (int c = 0; c <= k; c++) {
                        if (next[j - 1][c] == -1) continue;
                        if (c + cost <= k) {
                            next[j][c + cost] = Math.max(next[j][c + cost], next[j - 1][c] + score);
                        }
                    }
                }
            }
            
            dp = next;
        }
        
        int res = -1;
        for (int c = 0; c <= k; c++) {
            res = Math.max(res, dp[n - 1][c]);
        }
        
        return res;
    }
}