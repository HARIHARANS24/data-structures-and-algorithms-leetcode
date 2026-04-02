class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int[][][] dp = new int[m][n][3];
        
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = 0;
        dp[0][0][2] = 0;
        
        for (int i = 1; i < m; i++) {
            for (int k = 0; k < 3; k++) {
                dp[i][0][k] = Integer.MIN_VALUE;
                if (coins[i][0] >= 0) {
                    dp[i][0][k] = dp[i-1][0][k] + coins[i][0];
                } else {
                    if (k > 0)
                        dp[i][0][k] = Math.max(dp[i][0][k], dp[i-1][0][k-1]);
                    dp[i][0][k] = Math.max(dp[i][0][k], dp[i-1][0][k] + coins[i][0]);
                }
            }
        }
        
        for (int j = 1; j < n; j++) {
            for (int k = 0; k < 3; k++) {
                dp[0][j][k] = Integer.MIN_VALUE;
                if (coins[0][j] >= 0) {
                    dp[0][j][k] = dp[0][j-1][k] + coins[0][j];
                } else {
                    if (k > 0)
                        dp[0][j][k] = Math.max(dp[0][j][k], dp[0][j-1][k-1]);
                    dp[0][j][k] = Math.max(dp[0][j][k], dp[0][j-1][k] + coins[0][j]);
                }
            }
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                    
                    if (coins[i][j] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k] + coins[i][j]);
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k] + coins[i][j]);
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k] + coins[i][j]);
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k] + coins[i][j]);
                        
                        if (k > 0) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k-1]);
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][j-1][k-1]);
                        }
                    }
                }
            }
        }
        
        return Math.max(dp[m-1][n-1][0], Math.max(dp[m-1][n-1][1], dp[m-1][n-1][2]));
    }
}