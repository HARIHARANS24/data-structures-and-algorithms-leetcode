class Solution {
    private char[] s;
    private long[][][][][][] cntMemo;
    private long[][][][][][] sumMemo;
    private boolean[][][][][][] vis;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) return 0;
        s = Long.toString(n).toCharArray();

        int m = s.length;
        cntMemo = new long[m + 1][2][2][3][11][11];
        sumMemo = new long[m + 1][2][2][3][11][11];
        vis = new boolean[m + 1][2][2][3][11][11];

        return dfs(0, 1, 0, 0, 10, 10)[1];
    }

    private long[] dfs(int pos, int tight, int started, int state, int prev2, int prev1) {
        if (pos == s.length) {
            return new long[]{1L, 0L};
        }

        if (vis[pos][tight][started][state][prev2][prev1]) {
            return new long[]{
                cntMemo[pos][tight][started][state][prev2][prev1],
                sumMemo[pos][tight][started][state][prev2][prev1]
            };
        }

        long totalCnt = 0;
        long totalSum = 0;

        int limit = tight == 1 ? s[pos] - '0' : 9;

        for (int d = 0; d <= limit; d++) {
            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {
                long[] nxt = dfs(pos + 1, ntight, 0, 0, 10, 10);
                totalCnt += nxt[0];
                totalSum += nxt[1];
                continue;
            }

            if (started == 0) {
                long[] nxt = dfs(pos + 1, ntight, 1, 1, 10, d);
                totalCnt += nxt[0];
                totalSum += nxt[1];
            } else if (state == 1) {
                long[] nxt = dfs(pos + 1, ntight, 1, 2, prev1, d);
                totalCnt += nxt[0];
                totalSum += nxt[1];
            } else {
                int add = ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d)) ? 1 : 0;

                long[] nxt = dfs(pos + 1, ntight, 1, 2, prev1, d);
                totalCnt += nxt[0];
                totalSum += nxt[1] + nxt[0] * add;
            }
        }

        vis[pos][tight][started][state][prev2][prev1] = true;
        cntMemo[pos][tight][started][state][prev2][prev1] = totalCnt;
        sumMemo[pos][tight][started][state][prev2][prev1] = totalSum;

        return new long[]{totalCnt, totalSum};
    }
}