class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1_000_000_007;
        int M = 200;
        int[][] gcd = new int[M + 1][M + 1];
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == 0) gcd[i][j] = j;
                else if (j == 0) gcd[i][j] = i;
                else {
                    int a = i, b = j;
                    while (b != 0) {
                        int t = a % b;
                        a = b;
                        b = t;
                    }
                    gcd[i][j] = a;
                }
            }
        }

        long[][] dp = new long[M + 1][M + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            long[][] ndp = new long[M + 1][M + 1];
            for (int g1 = 0; g1 <= M; g1++) {
                for (int g2 = 0; g2 <= M; g2++) {
                    long cur = dp[g1][g2];
                    if (cur == 0) continue;

                    ndp[g1][g2] = (ndp[g1][g2] + cur) % MOD;

                    int ng1 = gcd[g1][x];
                    ndp[ng1][g2] = (ndp[ng1][g2] + cur) % MOD;

                    int ng2 = gcd[g2][x];
                    ndp[g1][ng2] = (ndp[g1][ng2] + cur) % MOD;
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for (int g = 1; g <= M; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }
}