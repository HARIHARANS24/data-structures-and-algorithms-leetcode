class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];

        int[] fa = new int[n + 1];
        int[] fb = new int[n + 1];

        for (int i = 0; i < n; i++) {
            fa[A[i]]++;
            fb[B[i]]++;

            for (int j = 1; j <= n; j++) {
                res[i] += Math.min(fa[j], fb[j]);
            }
        }

        return res;
    }
}