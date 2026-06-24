class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] u = new long[m];
        for (int i = 0; i < m; i++) {
            u[i] = i;
        }

        long[][] M = new long[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                M[i][j] = Math.min(i, j);
            }
        }

        long q = (n - 2L) / 2L;
        long[] x = powerApply(M, q, u);

        long ans = 0;
        if (((n - 2) & 1) == 0) {
            for (int i = 0; i < m; i++) {
                ans = (ans + x[i]) % MOD;
            }
        } else {
            for (int i = 0; i < m; i++) {
                ans = (ans + (long) i * x[i]) % MOD;
            }
        }

        ans = (ans * 2) % MOD;
        return (int) ans;
    }

    private long[] powerApply(long[][] base, long exp, long[] vec) {
        int n = base.length;

        long[][] cur = base;
        long[] res = vec.clone();

        boolean started = false;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                if (!started) {
                    res = multiply(cur, res);
                    started = true;
                } else {
                    res = multiply(cur, res);
                }
            }
            cur = multiply(cur, cur);
            exp >>= 1;
        }

        return started ? res : vec;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                long aik = a[i][k];
                for (int j = 0; j < n; j++) {
                    res[i][j] = (res[i][j] + aik * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}